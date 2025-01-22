package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.dto.ClientVendorDTO;
import com.accounting.einvoices.dto.InvoiceDTO;
import com.accounting.einvoices.dto.InvoiceProductDTO;
import com.accounting.einvoices.dto.ProductDTO;
import com.accounting.einvoices.dto.ai_analysis.ClientAnalysisDTO;
import com.accounting.einvoices.dto.ai_analysis.InvoiceAnalysisDTO;
import com.accounting.einvoices.dto.ai_analysis.InvoiceProductAnalysisDTO;
import com.accounting.einvoices.dto.ai_analysis.ProductAnalysisDTO;
import com.accounting.einvoices.enums.InvoiceStatus;
import com.accounting.einvoices.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AIReportingServiceImpl implements AIReportingService {

    private final InvoiceService invoiceService;
    private final ProductService productService;
    private final InvoiceProductService invoiceProductService;
    private final ClientVendorService clientVendorService;

    public AIReportingServiceImpl(InvoiceService invoiceService, ProductService productService, InvoiceProductService invoiceProductService,
                                  ClientVendorService clientVendorService) {
        this.invoiceService = invoiceService;
        this.productService = productService;
        this.invoiceProductService = invoiceProductService;
        this.clientVendorService = clientVendorService;
    }

    @Override
    public List<InvoiceAnalysisDTO> getInvoiceAnalysis() {
        List<InvoiceDTO> approvedInvoices = invoiceService.findAllByStatus(InvoiceStatus.APPROVED);
        return approvedInvoices.stream()
                .map(this::convertToAnalysisDTO)
                .toList();
    }

    @Override
    public List<ProductAnalysisDTO> getProductsAnalysis() {
        List<ProductDTO> allProduct = productService.findAll();
        return allProduct.stream()
                .map(this::convertToAnalysisDTO)
                .toList();
    }

    @Override
    public List<InvoiceProductAnalysisDTO> getInvoiceProductAnalysis() {
        List<InvoiceProductDTO> soldInvoiceProducts = invoiceProductService.findAllByApprovedInvoices();
        return soldInvoiceProducts.stream()
                .map(this::convertToAnalysisDTO)
                .toList();
    }

    @Override
    public List<ClientAnalysisDTO> getClientAnalysis() {
        List<ClientVendorDTO> allClient = clientVendorService.findAll();
        return allClient.stream()
                .map(this::convertToAnalysisDTO)
                .toList();
    }

    private ClientAnalysisDTO convertToAnalysisDTO(ClientVendorDTO client) {
        ClientAnalysisDTO clientAnalysisDTO = new ClientAnalysisDTO();
        clientAnalysisDTO.setName(client.getName());
        clientAnalysisDTO.setPhone(client.getPhone());
        clientAnalysisDTO.setEmail(client.getEmail());
        clientAnalysisDTO.setAddressSummary(client.getAddress().getCountry() + ", " +
                client.getAddress().getCity() + ", " + client.getAddress().getAddressLine1());
        return clientAnalysisDTO;
    }

    private InvoiceProductAnalysisDTO convertToAnalysisDTO(InvoiceProductDTO invoiceProduct) {
        InvoiceProductAnalysisDTO invoiceProductAnalysisDTO = new InvoiceProductAnalysisDTO();
        invoiceProductAnalysisDTO.setInvoiceNo(invoiceProduct.getInvoice().getInvoiceNo());
        invoiceProductAnalysisDTO.setProductName(invoiceProduct.getProduct().getName());
        invoiceProductAnalysisDTO.setDescription(invoiceProduct.getDescription());
        invoiceProductAnalysisDTO.setQuantity(invoiceProduct.getQuantity());
        invoiceProductAnalysisDTO.setPrice(invoiceProduct.getPrice());
        invoiceProductAnalysisDTO.setCurrency(invoiceProduct.getCurrency());
        invoiceProductAnalysisDTO.setTax(invoiceProduct.getTax());
        invoiceProductAnalysisDTO.setTotal(invoiceProduct.getTotal());
        invoiceProductAnalysisDTO.setProfitLoss(invoiceProduct.getProfitLoss());
        invoiceProductAnalysisDTO.setRemainingQuantity(invoiceProduct.getRemainingQuantity());
        return invoiceProductAnalysisDTO;
    }

    private ProductAnalysisDTO convertToAnalysisDTO(ProductDTO product) {
        ProductAnalysisDTO productAnalysisDTO = new ProductAnalysisDTO();
        productAnalysisDTO.setName(product.getName());
        productAnalysisDTO.setDescription(product.getDescription());
        productAnalysisDTO.setQuantityInStock(product.getQuantityInStock());
        productAnalysisDTO.setCreatedAt(product.getCreatedAt().toString());
        productAnalysisDTO.setPrice(product.getPrice());
        productAnalysisDTO.setCurrency(product.getCurrency());
        productAnalysisDTO.setProductUnit(product.getProductUnit());
        productAnalysisDTO.setCategoryName(product.getCategory().getDescription());
        return productAnalysisDTO;
    }

    private InvoiceAnalysisDTO convertToAnalysisDTO(InvoiceDTO invoice) {
        InvoiceAnalysisDTO invoiceAnalysisDTO = new InvoiceAnalysisDTO();
        invoiceAnalysisDTO.setInvoiceNo(invoice.getInvoiceNo());
        invoiceAnalysisDTO.setInvoiceStatus(invoice.getInvoiceStatus());
        invoiceAnalysisDTO.setDateOfIssue(invoice.getDateOfIssue().toString());
        invoiceAnalysisDTO.setDueDate(invoice.getDueDate().toString());
        invoiceAnalysisDTO.setAcceptDate(invoice.getAcceptDate().toString());
        invoiceAnalysisDTO.setCurrency(invoice.getCurrency());
        invoiceAnalysisDTO.setPaymentTerms(invoice.getPaymentTerms());
        invoiceAnalysisDTO.setNotes(invoiceAnalysisDTO.getNotes());
        invoiceAnalysisDTO.setClientName(invoice.getClientVendor().getName());
        return invoiceAnalysisDTO;
    }
}
