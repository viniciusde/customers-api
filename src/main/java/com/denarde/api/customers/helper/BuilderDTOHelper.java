package com.denarde.api.customers.helper;

import com.denarde.api.customers.model.Customer;
import com.denarde.api.customers.resource.v1.dto.CustomerDTO;
import com.denarde.api.customers.resource.v1.dto.CustomerResponseDTO;
import com.denarde.api.customers.resource.v1.dto.CustomerUpdateDTO;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import static com.denarde.api.customers.helper.JsonNullableHelper.changeIfPresent;

public class BuilderDTOHelper {

    public static CustomerResponseDTO buildCustomerResponseDTO(Customer customer) {
        return CustomerResponseDTO.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .cpf(customer.getCpf())
                .birthDate(customer.getBirthDate())
                .age(Period.between(customer.getBirthDate(), LocalDate.now()).getYears())
                .build();
    }

    public static Customer buildCustomer(CustomerDTO customerDTO) {
        return Customer.builder()
                .firstName(customerDTO.getFirstName())
                .lastName(customerDTO.getLastName())
                .cpf(customerDTO.getCpf())
                .birthDate(customerDTO.getBirthDate())
                .build();
    }

    public static  Customer buildCustomer(Customer customer, CustomerUpdateDTO customerUpdateDTO) {
        changeIfPresent(customerUpdateDTO.getFirstName(), customer::setFirstName);
        changeIfPresent(customerUpdateDTO.getLastName(), customer::setLastName);
        changeIfPresent(customerUpdateDTO.getCpf(), customer::setCpf);
        changeIfPresent(customerUpdateDTO.getBirthDate(), customer::setBirthDate);
        return customer;
    }

    public static Customer buildCustomer(Optional<String> firstNameFilter, Optional<String> lastNameFilter,
                                         Optional<String> cpfFilter, Optional<LocalDate> birthDateFilter){
        return Customer.builder()
                .firstName(firstNameFilter.orElse(null))
                .lastName(lastNameFilter.orElse(null))
                .cpf(cpfFilter.orElse(null))
                .birthDate(birthDateFilter.orElse(null))
                .build();
    }
}
