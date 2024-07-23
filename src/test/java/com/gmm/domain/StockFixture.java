package com.gmm.domain;

import com.gmm.infra.model.Money;
import com.gmm.modules.stock.Stock;
import com.gmm.modules.stock.StockCreate;

import java.math.BigDecimal;
import java.time.LocalDate;

public class StockFixture {

    private final String ticker = "O";
    private final int quantity = 2;
    private final BigDecimal orderPrice = BigDecimal.valueOf(57.48);
    private final LocalDate orderDate = LocalDate.of(2024, 7, 23);

    public static Stock.StockBuilder anStock() {
        return new StockFixture().stockBuilder();
    }

    public static StockCreate.StockCreateBuilder anStockCreate() {
        return new StockFixture().stockCreateBuilder();
    }

    public Stock.StockBuilder stockBuilder() {
        return Stock.builder()
                .ticker(ticker)
                .quantity(quantity)
                .orderPrice(Money.of(orderPrice))
                .orderDate(orderDate);
    }

    public StockCreate.StockCreateBuilder stockCreateBuilder() {
        return StockCreate.builder()
                .ticker(ticker)
                .quantity(quantity)
                .orderPrice(orderPrice)
                .orderDate(orderDate);
    }

}
