package com.gmm.modules.stock;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record StockCreate(
        @NotBlank(message = "티커를 입력해주세요.")
        String ticker,
        int quantity,
        @NotNull(message = "주문 금액을 입력해주세요.")
        BigDecimal orderPrice,
        @NotNull(message = "주문일을 입력해주세요.")
        LocalDate orderDate
) {

    public void validate() {

    }
}
