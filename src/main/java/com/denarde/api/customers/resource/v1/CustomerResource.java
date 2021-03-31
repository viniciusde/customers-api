package com.denarde.api.customers.resource.v1;

import com.denarde.api.customers.exception.CustomerAlreadyExistsException;
import com.denarde.api.customers.resource.v1.dto.CustomerDTO;
import com.denarde.api.customers.resource.v1.dto.CustomerResponseDTO;
import com.denarde.api.customers.resource.v1.dto.CustomerUpdateDTO;
import com.denarde.api.customers.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.Optional;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/customers")
public class CustomerResource {

    private final CustomerService service;

    @GetMapping
    public Page getCustomers(
            @RequestParam Optional<String> firstName,
            @RequestParam Optional<String> lastName,
            @RequestParam Optional<String> cpf,
            @RequestParam @DateTimeFormat(iso = DATE) Optional<LocalDate> birthDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") @Min(5) @Max(25) Integer size) {
        return service.query(firstName, lastName, cpf, birthDate, PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public CustomerResponseDTO getCustomerById(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public CustomerResponseDTO addNewCustomer(@Valid @RequestBody CustomerDTO customerDTO) throws CustomerAlreadyExistsException {
        return service.save(customerDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateCustomer(@PathVariable String id, @Valid @RequestBody CustomerDTO customerDTO){
        service.update(id, customerDTO);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updatePartialCustomer(@PathVariable String id, @Valid @RequestBody CustomerUpdateDTO customerUpdateDTO){
        service.update(id, customerUpdateDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void removeCustomer(@PathVariable String id) {
        service.remove(id);
    }

}
