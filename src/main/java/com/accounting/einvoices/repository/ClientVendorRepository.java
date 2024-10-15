package com.accounting.einvoices.repository;

import com.accounting.einvoices.entity.ClientVendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientVendorRepository extends JpaRepository<ClientVendor, Long> {

    List<ClientVendor> findAllByCompanyId(Long id);

    Optional<ClientVendor> findByName(String name);

}
