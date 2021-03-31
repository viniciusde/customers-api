package com.denarde.api.customers.resource.v1.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;
import org.openapitools.jackson.nullable.JsonNullable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@With
@JsonDeserialize(builder = CustomerUpdateDTO.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
@Value
public class CustomerUpdateDTO {

    @NotEmpty @Length(min = 5, max = 30)
    @Builder.Default
    private JsonNullable<String> firstName = JsonNullable.undefined();
    @Length(max = 30)
    @Builder.Default
    private JsonNullable<String> lastName = JsonNullable.undefined();
    @CPF
    @Builder.Default
    private JsonNullable<String> cpf = JsonNullable.undefined();
    @NotNull
    @Builder.Default
    private JsonNullable<LocalDate> birthDate = JsonNullable.undefined();

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder{

    }
}
