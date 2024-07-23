package com.gmm.modules.stock;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record StockEdit(
        String ticker,
        int quantity,
        BigDecimal orderPrice,
        LocalDate orderDate
) {

}
