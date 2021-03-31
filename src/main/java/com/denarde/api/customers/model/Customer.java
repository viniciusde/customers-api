package com.denarde.api.customers.model;

import lombok.Builder;
import lombok.Data;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@With
@Document
@Builder
@Data
public class Customer {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String cpf;
    private LocalDate birthDate;
}
