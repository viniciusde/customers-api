package com.denarde.api.customers.resource.v1.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@With
@JsonDeserialize(builder = CustomerDTO.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
@Value
public class CustomerDTO {

    @NotEmpty @Length(min = 5, max = 30)
    private String firstName;
    @Length(max = 30)
    private String lastName;
    @CPF
    private String cpf;
    @NotNull
    private LocalDate birthDate;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder{

    }
}
