package com.gmm.api.stock;

import com.gmm.modules.stock.Stock;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class StockResponse {

    private final Long id;
    private final String ticker;
    private final int quantity;
    private final BigDecimal orderPrice;
    private final String orderDate;

    public StockResponse(Stock stock) {
        this.id = stock.getId();
        this.ticker = stock.getTicker();
        this.quantity = stock.getQuantity();
        this.orderPrice = stock.getOrderPrice();
        this.orderDate = stock.getOrderDate().toString();
    }

}
