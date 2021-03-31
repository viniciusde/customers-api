package com.denarde.api.customers.service;

import com.denarde.api.customers.exception.CustomerAlreadyExistsException;
import com.denarde.api.customers.exception.CustomerNotFoundException;
import com.denarde.api.customers.model.Customer;
import com.denarde.api.customers.repository.CustomerRepository;
import com.denarde.api.customers.resource.v1.dto.CustomerDTO;
import com.denarde.api.customers.resource.v1.dto.CustomerResponseDTO;
import com.denarde.api.customers.resource.v1.dto.CustomerUpdateDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService service;
    @Mock
    private CustomerRepository repository;

    private static final String ID = "6063c4639a33c506da573c71";

    @Test(expected = CustomerNotFoundException.class)
    public void deveRetornarErro__aoBuscarPorId__clienteNaoExiste(){
        when(repository.findById(any())).thenReturn(Optional.empty());
        service.findById(ID);
    }

    @Test
    public void deveRetornarPorId(){
        when(repository.findById(any())).thenReturn(mockCustomer());
        CustomerResponseDTO dto = service.findById(ID);
        Assert.assertEquals(ID, dto.getId());
    }

    @Test(expected = CustomerNotFoundException.class)
    public void deveRetornarErro__aoRemover__clienteNaoExiste(){
        when(repository.findById(any())).thenReturn(Optional.empty());
        service.remove(ID);
    }

    @Test
    public void deveRemoverPorId(){
        when(repository.findById(any())).thenReturn(mockCustomer());
        service.remove(ID);
    }

    @Test(expected = CustomerNotFoundException.class)
    public void deveRetornarErro__aoAtualizar__clienteNaoExiste(){
        when(repository.findById(any())).thenReturn(Optional.empty());
        service.update(ID, CustomerDTO.builder().build());
    }

    @Test
    public void deveAtualizarCompleto(){
        when(repository.findById(any())).thenReturn(mockCustomer());
        service.update(ID, CustomerDTO.builder().build());
    }

    @Test(expected = CustomerNotFoundException.class)
    public void deveRetornarErro__aoAtualizarParcialmente__clienteNaoExiste(){
        when(repository.findById(any())).thenReturn(Optional.empty());
        service.update(ID, CustomerUpdateDTO.builder().build());
    }

    @Test
    public void deveAtualizarParcial(){
        when(repository.findById(any())).thenReturn(mockCustomer());
        service.update(ID, CustomerUpdateDTO.builder().build());
    }

    @Test(expected = CustomerAlreadyExistsException.class)
    public void deveRetornarErro__aoSalvar__clienteJaExiste() throws CustomerAlreadyExistsException {
        when(repository.findByCpf(any())).thenReturn(mockCustomer());
        service.save(CustomerDTO.builder().build());
    }

    @Test
    public void deveSalvar() throws CustomerAlreadyExistsException {
        when(repository.findByCpf(any())).thenReturn(Optional.empty());
        when(repository.save(any())).thenReturn(mockCustomer().get());
        CustomerResponseDTO responseDTO = service.save(CustomerDTO.builder().build());
        Assert.assertEquals(30, responseDTO.getAge());
    }


    private Optional<Customer> mockCustomer(){
        return Optional.of(Customer.builder()
                .id(ID)
                .firstName("First name")
                .lastName("last name")
                .cpf("01234567890")
                .birthDate(LocalDate.now().minusYears(30)).build());
    }
}
