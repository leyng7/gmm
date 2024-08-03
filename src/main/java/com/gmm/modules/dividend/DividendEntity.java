package com.gmm.modules.dividend;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DividendEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dividend_id")
    private Long id;

    private String ticker;

    private BigDecimal amount;

    public DividendEntity(String ticker, BigDecimal amount) {
        this.ticker = ticker;
        this.amount = amount;
    }

}
