package com.denarde.api.customers.service;

import com.denarde.api.customers.exception.CustomerAlreadyExistsException;
import com.denarde.api.customers.exception.CustomerNotFoundException;
import com.denarde.api.customers.model.Customer;
import com.denarde.api.customers.repository.CustomerRepository;
import com.denarde.api.customers.resource.v1.dto.CustomerDTO;
import com.denarde.api.customers.resource.v1.dto.CustomerResponseDTO;
import com.denarde.api.customers.resource.v1.dto.CustomerUpdateDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

import static com.denarde.api.customers.helper.BuilderDTOHelper.buildCustomer;
import static com.denarde.api.customers.helper.BuilderDTOHelper.buildCustomerResponseDTO;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;

    public Page<CustomerResponseDTO> query(Optional<String> firstNameFilter, Optional<String> lastNameFilter,
                                Optional<String> cpfFilter, Optional<LocalDate> birthDateFilter, Pageable pageable){
        Example example = Example.of(buildCustomer(firstNameFilter, lastNameFilter, cpfFilter, birthDateFilter));
        return repository.findAll(example, pageable).map(customer -> buildCustomerResponseDTO(customer));
    }

    public CustomerResponseDTO findById(String id){
        return buildCustomerResponseDTO(repository.findById(id).orElseThrow(()-> new CustomerNotFoundException()));
    }

    public CustomerResponseDTO save(CustomerDTO customerDTO) throws CustomerAlreadyExistsException {
        if(checkIfAlreadyExistWithCpf(customerDTO.getCpf())) {
            throw new CustomerAlreadyExistsException();
        }
        return buildCustomerResponseDTO(repository.save(buildCustomer(customerDTO)));
    }

    public void update(String id, CustomerDTO customerDTO) {
        repository.findById(id).orElseThrow(()-> new CustomerNotFoundException());
        repository.save(buildCustomer(customerDTO).withId(id));
    }

    public void update(String id, CustomerUpdateDTO customerUpdateDTO) {
        Customer customer = repository.findById(id).orElseThrow(()-> new CustomerNotFoundException());
        repository.save(buildCustomer(customer, customerUpdateDTO));
    }

    public void remove(String id){
        Customer customer = repository.findById(id).orElseThrow(()-> new CustomerNotFoundException());
        repository.delete(customer);
    }

    private boolean checkIfAlreadyExistWithCpf(String cpf) {
        return repository.findByCpf(cpf).isPresent();
    }

}
