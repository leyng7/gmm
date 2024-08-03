package com.gmm.modules.dividend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DividendAppender {

    private final DividendRepository dividendRepository;

    public Long add(Dividend dividend) {
        Long dividendId = dividendRepository.save(dividend);
        return dividendId;
    }

}
