package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.dto.CompanyDTO;
import com.accounting.einvoices.dto.InvoiceDTO;
import com.accounting.einvoices.dto.InvoiceProductDTO;
import com.accounting.einvoices.dto.ProductDTO;
import com.accounting.einvoices.entity.InvoiceProduct;
import com.accounting.einvoices.entity.Product;
import com.accounting.einvoices.exception.invoice.InvoiceProductNotFoundException;
import com.accounting.einvoices.exception.product.ProductLowLimitAlertException;
import com.accounting.einvoices.repository.InvoiceProductRepository;
import com.accounting.einvoices.service.CompanyService;
import com.accounting.einvoices.service.InvoiceProductService;
import com.accounting.einvoices.service.InvoiceService;
import com.accounting.einvoices.service.ProductService;
import com.accounting.einvoices.util.BigDecimalUtil;
import com.accounting.einvoices.util.MapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Slf4j
@Service
public class InvoiceProductServiceImpl implements InvoiceProductService {

    private final InvoiceProductRepository invoiceProductRepository;
    private final InvoiceService invoiceService;
    private final ProductService productService;
    private final CompanyService companyService;
    private final MapperUtil mapperUtil;

    public InvoiceProductServiceImpl(InvoiceProductRepository invoiceProductRepository, InvoiceService invoiceService,
                                     @Lazy ProductService productService, CompanyService companyService, MapperUtil mapperUtil) {
        this.invoiceProductRepository = invoiceProductRepository;
        this.invoiceService = invoiceService;
        this.productService = productService;
        this.companyService = companyService;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<InvoiceProductDTO> findAllByInvoiceId(Long id) {
        return invoiceProductRepository.findAllByInvoiceId(id).stream()
                .map(invoiceProduct -> {
                    InvoiceProductDTO invoiceProductDTO = mapperUtil.convert(invoiceProduct, new InvoiceProductDTO());
                    invoiceProductDTO.setTotal(BigDecimalUtil.format(getTotalWithTax(invoiceProductDTO)));
                    return invoiceProductDTO;
                }).toList();
    }

    @Override
    public List<InvoiceProductDTO> findAllByProductId(Long id) {
        return invoiceProductRepository.findAllByProductId(id).stream()
                .map(invoiceProduct -> {
                    InvoiceProductDTO invoiceProductDTO = mapperUtil.convert(invoiceProduct, new InvoiceProductDTO());
                    invoiceProductDTO.setTotal(BigDecimalUtil.format(getTotalWithTax(invoiceProductDTO)));
                    return invoiceProductDTO;
                }).toList();
    }

    @Override
    public InvoiceProductDTO save(Long invoiceId, InvoiceProductDTO invoiceProduct) {
        InvoiceDTO foundInvoice = invoiceService.findById(invoiceId);
        ProductDTO foundProduct = productService.findByName(invoiceProduct.getProduct().getName());
        invoiceProduct.setInvoice(foundInvoice);
        invoiceProduct.setProduct(foundProduct);
        invoiceProduct.setCurrency(foundInvoice.getCurrency());
        InvoiceProduct saved = invoiceProductRepository.save(mapperUtil.convert(invoiceProduct, new InvoiceProduct()));
        return mapperUtil.convert(saved, new InvoiceProductDTO());
    }

    @Override
    public void delete(Long id) {
        InvoiceProduct foundInvoiceProduct = invoiceProductRepository.findById(id)
                .orElseThrow(() -> new InvoiceProductNotFoundException("Invoice product not found."));
        foundInvoiceProduct.setIsDeleted(true);
        invoiceProductRepository.save(foundInvoiceProduct);
    }

    @Override
    public void deleteAllByInvoice(Long invoiceId) {
        List<InvoiceProduct> invoiceProducts = invoiceProductRepository.findAllByInvoiceId(invoiceId);
        if (!invoiceProducts.isEmpty()) {
            invoiceProducts.forEach(invoiceProduct -> {
                invoiceProduct.setIsDeleted(true);
                invoiceProductRepository.save(invoiceProduct);
            });
        }
    }

    @Override
    public BigDecimal getTotalWithTax(InvoiceProductDTO invoiceProduct) {
        BigDecimal totalWithoutTax = getTotalWithoutTax(invoiceProduct);
        BigDecimal tax = (totalWithoutTax.multiply(invoiceProduct.getTax()).divide(BigDecimal.valueOf(100), RoundingMode.DOWN));
        return totalWithoutTax.add(tax);
    }

    @Override
    public BigDecimal getTotalWithoutTax(InvoiceProductDTO invoiceProduct) {
        log.info("InvoiceProduct unit price: {}, based on quantity: {}", invoiceProduct.getPrice(), invoiceProduct.getPrice().multiply(BigDecimal.valueOf(invoiceProduct.getQuantity())));
        return invoiceProduct.getPrice().multiply(BigDecimal.valueOf(invoiceProduct.getQuantity()));
    }


    @Override
    public void updateQuantityInStock(Long id) {
        List<Product> products = invoiceProductRepository.findProductsByInvoiceId(id);
        products.forEach(product -> {
            ProductDTO productDTO = mapperUtil.convert(product, new ProductDTO());
            int sumQuantityOfProducts = invoiceProductRepository.sumQuantityOfProducts(id, product.getId());
            productDTO.setQuantityInStock(product.getQuantityInStock() - sumQuantityOfProducts);
            productService.update(productDTO.getId(), productDTO);
        });
    }

    @Override
    public void calculateProfitLoss(Long id) {
        List<InvoiceProductDTO> invoiceProducts = findAllByInvoiceId(id);
        for (InvoiceProductDTO each : invoiceProducts) {
            Long productId = each.getProduct().getId();
            BigDecimal profitLoss = getTotalWithTax(each)
                    .subtract(calculateCost(productId, each.getQuantity()));
            each.setProfitLoss(profitLoss);
            save(id, each);
        }
    }

    private BigDecimal calculateCost(Long productId, int salesQuantity) {
        CompanyDTO loggedInCompany = companyService.getByLoggedInUser();
        List<InvoiceProduct> invoiceProducts = invoiceProductRepository.findByApprovedInvoices(productId, loggedInCompany.getId());
        BigDecimal totalCost = BigDecimal.ZERO;
        for (InvoiceProduct invoiceProduct : invoiceProducts) {
            int remainingQuantity = invoiceProduct.getRemainingQuantity() - salesQuantity;
            BigDecimal cost = invoiceProduct.getProduct().getPrice().multiply(BigDecimal.valueOf(salesQuantity));
            if (remainingQuantity <= 0) {
                salesQuantity -= invoiceProduct.getRemainingQuantity();
                invoiceProduct.setRemainingQuantity(0);
                totalCost = totalCost.add(cost);
                invoiceProductRepository.save(invoiceProduct);
                if (remainingQuantity == 0) break;
            } else {
                invoiceProduct.setRemainingQuantity(remainingQuantity);
                totalCost = totalCost.add(cost);
                invoiceProductRepository.save(invoiceProduct);
                break;
            }
        }
        return totalCost;
    }


    @Override
    public void lowQuantityAlert(Long id) {
        List<Product> products = invoiceProductRepository.findProductsByInvoiceId(id);
        for (Product each : products) {
            int stock = each.getQuantityInStock();
            if (stock < each.getLowLimitAlert()){
                throw new ProductLowLimitAlertException("Stock of " + each.getName() + " decreased below low limit!");
            }
        }
    }

    @Override
    public List<InvoiceProductDTO> findAllByInvoiceIdAndCalculateTotalPrice(Long id) {
        return invoiceProductRepository.findAllByInvoiceId(id).stream()
                .map(invoiceProduct -> {
                    InvoiceProductDTO dto = mapperUtil.convert(invoiceProduct, new InvoiceProductDTO());
                    dto.setTotal(BigDecimalUtil.format(getTotalWithTax(dto)));
                    return dto;
                }).toList();
    }


    //if product is used in invoice, its invoice product and product can not be deleted if there is invoice product
    @Override
    public boolean checkIfCanBeDeleted(Long id) {
        List<InvoiceProduct> invoiceProducts = invoiceProductRepository.findAllByProductId(id);
        return !invoiceProducts.isEmpty();
    }

    @Override
    public Integer sumQuantityOfSoldProducts() {
        return invoiceProductRepository.sumQuantityOfSoldProducts(companyService.getByLoggedInUser().getId());
    }

}
