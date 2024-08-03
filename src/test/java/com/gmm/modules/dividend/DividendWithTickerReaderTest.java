package com.gmm.modules.dividend;

import com.gmm.modules.ticker.Ticker;
import com.gmm.modules.ticker.TickerRepository;
import com.gmm.persistence.dividend.DividendJpaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class DividendWithTickerReaderTest {

    @Autowired
    private TickerRepository tickerRepository;

    @Autowired
    private DividendJpaRepository dividendJpaRepository;

    @Autowired
    private DividendReader dividendReader;

    @Test
    @DisplayName("주식 DIVIDEND READ")
    void readAll() {
        Ticker ticker = new Ticker("VOO", "인덱스");
        tickerRepository.save(ticker);

        DividendEntity dividendEntity = new DividendEntity("VOO", BigDecimal.TEN);
        dividendJpaRepository.save(dividendEntity);

        var dividends = dividendReader.readAll();;

        Assertions.assertEquals(1, dividends.size());
    }

}
