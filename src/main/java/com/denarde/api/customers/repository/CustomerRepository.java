package com.denarde.api.customers.repository;

import com.denarde.api.customers.model.Customer;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, String> {

    Page<Customer> findAll(Example example, Pageable pageable);

    Optional<Customer> findByCpf(String cpf);

}
