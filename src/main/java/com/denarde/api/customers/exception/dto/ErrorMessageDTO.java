package com.denarde.api.customers.exception.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.List;

@Value
@With
@JsonDeserialize(builder = ErrorMessageDTO.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
@AllArgsConstructor
public class ErrorMessageDTO {

    String message;
    List<String> details;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {

    }
}
