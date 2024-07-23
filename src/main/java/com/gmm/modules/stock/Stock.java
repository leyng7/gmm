package com.gmm.modules.stock;

import com.gmm.infra.model.Money;
import com.gmm.infra.model.MoneyConverter;
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

    @Convert(converter = MoneyConverter.class)
    private Money orderPrice;

    private LocalDate orderDate;

    @Builder
    private Stock(String ticker, int quantity, Money orderPrice, LocalDate orderDate) {
        this.ticker = ticker;
        this.quantity = quantity;
        this.orderPrice = orderPrice;
        this.orderDate = orderDate;
    }

    public void edit(String ticker, int quantity, Money orderPrice, LocalDate localDate) {
        this.ticker = ticker;
        this.quantity = quantity;
        this.orderPrice = orderPrice;
        this.orderDate = localDate;
    }

}

