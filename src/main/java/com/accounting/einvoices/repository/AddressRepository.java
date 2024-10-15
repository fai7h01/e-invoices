package com.accounting.einvoices.repository;

import com.accounting.einvoices.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
