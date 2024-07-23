package com.gmm.modules.stock;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record StockCreate(
        @NotBlank(message = "티커를 입력해주세요.")
        String ticker,
        int quantity,
        BigDecimal orderPrice,
        LocalDate orderDate
) {

    public void validate() {
        if (ticker == null || ticker.isEmpty()) {
            throw new IllegalArgumentException("ticker");
        }
        if (orderPrice == null) {
            throw new IllegalArgumentException("orderPrice");
        }
        if (orderDate == null) {
            throw new IllegalArgumentException("orderDate");
        }
    }
}
