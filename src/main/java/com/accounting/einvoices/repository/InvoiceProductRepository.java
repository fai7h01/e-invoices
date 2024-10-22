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

}
