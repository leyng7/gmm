package com.gmm.modules.dividend;

import com.gmm.modules.ticker.Ticker;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class DividendWithTicker {

    private final Long id;
    private final Ticker ticker;
    private final BigDecimal amount;

    public DividendWithTicker(Long id, Ticker ticker, BigDecimal amount) {
        this.id = id;
        this.ticker = ticker;
        this.amount = amount;
    }

    public DividendWithTicker(Ticker ticker, BigDecimal amount) {
        this(null, ticker, amount);
    }

}
