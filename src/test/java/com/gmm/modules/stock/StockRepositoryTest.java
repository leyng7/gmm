package com.gmm.modules.stock;

import com.gmm.infra.model.Money;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class StockRepositoryTest {

    @Autowired
    private StockRepository stockRepository;

    @Test
    void findById() {
        final String ticker = "O";
        final int quantity = 2;
        final Money orderPrice = Money.of(57.48);
        final LocalDate orderDate = LocalDate.of(2024, 7, 23);

        Stock stock = Stock.builder()
                .ticker(ticker)
                .quantity(quantity)
                .orderPrice(orderPrice)
                .orderDate(orderDate)
                .build();

        stockRepository.save(stock);

        Stock findStock = stockRepository.findById(stock.getId()).orElseThrow();

        assertThat(findStock.getId()).isEqualTo(stock.getId());
        assertThat(findStock.getTicker()).isEqualTo(stock.getTicker());
        assertThat(findStock.getQuantity()).isEqualTo(stock.getQuantity());
        assertThat(findStock.getOrderPrice()).isEqualTo(stock.getOrderPrice());
        assertThat(findStock.getOrderDate()).isEqualTo(stock.getOrderDate());
    }

}
