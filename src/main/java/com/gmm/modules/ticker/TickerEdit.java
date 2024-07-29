package com.gmm.modules.ticker;

import lombok.Builder;

@Builder
public record TickerEdit(
        String symbol,
        String type
) {
    
}
