package com.gmm.api.dividend;

import com.gmm.modules.dividend.Dividend;

import java.math.BigDecimal;

public record DividendAddRequest(
    String tickerSymbol,
    BigDecimal amount
) {

    public Dividend toDividend() {
        return new Dividend(this.tickerSymbol, this.amount);
    }

}
