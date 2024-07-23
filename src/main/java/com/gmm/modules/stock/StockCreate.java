package com.gmm.modules.stock;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record StockCreate(
        String ticker,
        int quantity,
        BigDecimal orderPrice,
        LocalDate orderDate
) {

}
