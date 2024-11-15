package com.accounting.einvoices.repository;

import com.accounting.einvoices.entity.InvoiceProduct;
import com.accounting.einvoices.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InvoiceProductRepository extends JpaRepository<InvoiceProduct, Long> {

    List<InvoiceProduct> findAllByInvoiceId(Long id);

    @Query("SELECT DISTINCT ip.product FROM InvoiceProduct ip WHERE ip.invoice.id = ?1")
    List<Product> findProductsByInvoiceId(Long id);

    @Query("SELECT sum(ip.quantity) FROM InvoiceProduct ip WHERE ip.invoice.id = ?1 AND ip.product.id = ?2")
    int sumQuantityOfProducts(Long invoiceId, Long productId);

    @Query("SELECT ip FROM InvoiceProduct ip WHERE ip.product.id = ?1 " +
            "AND ip.remainingQuantity >= 0 AND ip.invoice.invoiceStatus = 'APPROVED' " +
            "AND ip.invoice.company.id = ?2 ORDER BY ip.id asc")
    List<InvoiceProduct> findByApprovedInvoices(Long productId, Long companyId);

    @Query("SELECT sum(ip.quantity) FROM InvoiceProduct ip WHERE ip.invoice.invoiceStatus = 'APPROVED' " +
            "AND ip.invoice.company.id = ?1")
    int sumQuantityOfSoldProducts(Long companyId);

    List<InvoiceProduct> findAllByProductId(Long id);
}
