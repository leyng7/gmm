package com.gmm.modules.stock;

import com.gmm.domain.StockFixture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class StockRepositoryTest {

    @Autowired
    private StockRepository stockRepository;

    @Test
    void findById() {
        Stock stock = StockFixture.anStock().build();
        stockRepository.save(stock);

        Stock findStock = stockRepository.findById(stock.getId()).orElseThrow();

        assertThat(findStock.getId()).isEqualTo(stock.getId());
        assertThat(findStock.getTicker()).isEqualTo(stock.getTicker());
        assertThat(findStock.getQuantity()).isEqualTo(stock.getQuantity());
        assertThat(findStock.getOrderPrice()).isEqualTo(stock.getOrderPrice());
        assertThat(findStock.getOrderDate()).isEqualTo(stock.getOrderDate());
    }

}
