package com.fxdealswarehouse.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Deal {
    @Id
    @Column(nullable = false)
    private String dealId;


    @Column(nullable = false)
    private String fromCurrencyIsoCode;

    @Column(nullable = false)
    private String toCurrencyIsoCode;

    @Column(nullable = false)
    private LocalDateTime dealTimestamp;

    @Column(nullable = false)
    private double dealAmount;
}
