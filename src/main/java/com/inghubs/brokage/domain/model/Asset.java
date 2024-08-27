package com.inghubs.brokage.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Asset {

    @NotBlank(message = "Asset name is mandatory")
    private String assetName;

    @NotBlank(message = "Customer id is mandatory")
    private String customerId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer usableSize;

    @Min(value = 0, message = "Usable size should bigger than 0")
    private Integer size;
}
