package com.gmm.modules.dividend;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class Dividend {

    private final Long id;
    private final String tickerSymbol;
    private final BigDecimal amount;

    public Dividend(Long id, String tickerSymbol, BigDecimal amount) {
        this.id = id;
        this.tickerSymbol = tickerSymbol;
        this.amount = amount;
    }

    public Dividend(String tickerSymbol, BigDecimal amount) {
        this(null, tickerSymbol, amount);
    }

}
