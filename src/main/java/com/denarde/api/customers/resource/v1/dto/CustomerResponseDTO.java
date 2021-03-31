package com.denarde.api.customers.resource.v1.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.time.LocalDate;

@With
@JsonDeserialize(builder = CustomerResponseDTO.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
@Value
public class CustomerResponseDTO {

    private String id;
    private String firstName;
    private String lastName;
    private String cpf;
    private LocalDate birthDate;
    private int age;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder{

    }
}
