package com.accounting.einvoices.repository;

import com.accounting.einvoices.entity.ClientVendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientVendorRepository extends JpaRepository<ClientVendor, Long> {

    List<ClientVendor> findAllByCompanyId(Long id);

}
