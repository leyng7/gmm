package com.gmm.modules.ticker;

import lombok.Builder;

@Builder
public record TickerAdd (
        String symbol,
        String type
) {

}
