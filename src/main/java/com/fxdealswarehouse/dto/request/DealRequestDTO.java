package com.fxdealswarehouse.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DealRequestDTO {

    @NotBlank(message = "Deal ID is required and cannot be blank")
    private String dealId;

    @NotBlank(message = "From Currency ISO Code is required and cannot be blank")
    private String fromCurrencyIsoCode;

    @NotBlank(message = "To Currency ISO Code is required and cannot be blank")
    private String toCurrencyIsoCode;

    @NotNull(message = "Deal timestamp is required")
    private LocalDateTime dealTimestamp;

    @NotNull(message = "Deal amount is required")
    @Positive(message = "Deal amount must be positive")
    private Double dealAmount;
}
