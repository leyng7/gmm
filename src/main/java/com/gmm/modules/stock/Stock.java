package com.gmm.modules.stock;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Stock {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id")
    private Long id;

    private String ticker;

    private int quantity;

    private BigDecimal orderPrice;

    private LocalDate orderDate;

    public BigDecimal getTotalOrderPrice() {
        return orderPrice.multiply(BigDecimal.valueOf(quantity));
    }

    @Builder
    private Stock(String ticker, int quantity, BigDecimal orderPrice, LocalDate orderDate) {
        this.ticker = ticker;
        this.quantity = quantity;
        this.orderPrice = orderPrice;
        this.orderDate = orderDate;
    }

    public void edit(String ticker, int quantity, BigDecimal orderPrice, LocalDate localDate) {
        this.ticker = ticker;
        this.quantity = quantity;
        this.orderPrice = orderPrice;
        this.orderDate = localDate;
    }

}

