package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.dto.CompanyDTO;
import com.accounting.einvoices.dto.InvoiceDTO;
import com.accounting.einvoices.dto.InvoiceProductDTO;
import com.accounting.einvoices.dto.ProductDTO;
import com.accounting.einvoices.entity.InvoiceProduct;
import com.accounting.einvoices.entity.Product;
import com.accounting.einvoices.exception.InvoiceProductNotFoundException;
import com.accounting.einvoices.exception.ProductLowLimitAlertException;
import com.accounting.einvoices.repository.InvoiceProductRepository;
import com.accounting.einvoices.service.CompanyService;
import com.accounting.einvoices.service.InvoiceProductService;
import com.accounting.einvoices.service.InvoiceService;
import com.accounting.einvoices.service.ProductService;
import com.accounting.einvoices.util.MapperUtil;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceProductServiceImpl implements InvoiceProductService {

    private final InvoiceProductRepository invoiceProductRepository;
    private final InvoiceService invoiceService;
    private final ProductService productService;
    private final CompanyService companyService;
    private final MapperUtil mapperUtil;

    public InvoiceProductServiceImpl(InvoiceProductRepository invoiceProductRepository, InvoiceService invoiceService, ProductService productService, CompanyService companyService, MapperUtil mapperUtil) {
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
                    invoiceProductDTO.setTotal(getTotalWithTax(invoiceProductDTO));
                    return invoiceProductDTO;
                }).collect(Collectors.toList());
    }

    @Override
    public InvoiceProductDTO save(Long invoiceId, InvoiceProductDTO invoiceProduct) {
        InvoiceDTO foundInvoice = invoiceService.findById(invoiceId);
        ProductDTO foundProduct = productService.findByName(invoiceProduct.getProduct().getName());
        invoiceProduct.setInvoice(foundInvoice);
        invoiceProduct.setProduct(foundProduct);
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
    public BigDecimal getTotalWithTax(InvoiceProductDTO invoiceProduct) {
        BigDecimal totalWithoutTax = getTotalWithoutTax(invoiceProduct);
        BigDecimal tax = (totalWithoutTax.multiply(invoiceProduct.getTax()).divide(BigDecimal.valueOf(100), RoundingMode.DOWN));
        return totalWithoutTax.add(tax);
    }

    @Override
    public BigDecimal getTotalWithoutTax(InvoiceProductDTO invoiceProduct) {
        return invoiceProduct.getPrice().multiply(BigDecimal.valueOf(invoiceProduct.getQuantity()));
    }


    @Override
    public void updateQuantityInStock(Long id) {
        List<Product> products = invoiceProductRepository.findProductsByInvoiceId(id);
        products.forEach(product -> {
            ProductDTO productDTO = mapperUtil.convert(product, new ProductDTO());
            int sumQuantityOfProducts = invoiceProductRepository.sumQuantityOfProducts(id, product.getId());
            productDTO.setQuantityInStock(product.getQuantityInStock() - sumQuantityOfProducts);
            productService.save(productDTO);
        });
    }

    @Override
    public void calculateProfitLoss(Long id) {
        //find ip based on approved invoice id
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
        // find ip based on approved invoices and product
        CompanyDTO loggedInCompany = companyService.getByLoggedInUser();
        List<InvoiceProduct> invoiceProducts = invoiceProductRepository.findByApprovedInvoices(productId, loggedInCompany.getId());
        BigDecimal totalCost = BigDecimal.ZERO;
        for (InvoiceProduct invoiceProduct : invoiceProducts) {
            int remainingQuantity = invoiceProduct.getRemainingQuantity() - salesQuantity;
            BigDecimal cost = invoiceProduct.getProduct().getPrice().multiply(BigDecimal.valueOf(salesQuantity));
//            BigDecimal tax = costWithoutTax.multiply(invoiceProduct.getTax()).divide(BigDecimal.valueOf(100), RoundingMode.DOWN);
            //BigDecimal costWithTax = costWithoutTax.add(tax);
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
                    dto.setTotal(getTotalWithTax(dto));
                    return dto;
                }).collect(Collectors.toList());
    }

}
