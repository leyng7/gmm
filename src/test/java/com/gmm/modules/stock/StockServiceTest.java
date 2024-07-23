package com.gmm.modules.stock;

import com.gmm.domain.StockFixture;
import com.gmm.infra.model.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StockServiceTest {

    @Autowired
    private StockService stockService;

    @Autowired
    private StockRepository stockRepository;

    @BeforeEach
    void clean() {
        stockRepository.deleteAll();
    }

    @Test
    void getStock() {
        // Given
        Stock stock = StockFixture.anStock().build();
        stockRepository.save(stock);

        // When
        Stock findStock = stockService.getStock(stock.getId());

        // Then
        assertEquals(stock.getId(), findStock.getId());
        assertEquals(stock.getTicker(), findStock.getTicker());
        assertEquals(stock.getQuantity(), findStock.getQuantity());
        assertEquals(stock.getOrderPrice(), findStock.getOrderPrice());
        assertEquals(stock.getOrderDate(), findStock.getOrderDate());
    }

    @Test
    void getPageOfStock() {
        // Given

        // When

        // Then
    }

    @Test
    void addStock() {
        // Given
        StockCreate stockCreate = StockFixture.anStockCreate().build();

        // When
        Long stockId = stockService.addStock(stockCreate);
        Stock findStock = stockRepository.getStock(stockId);

        // Then
        assertEquals(1L, stockRepository.count());
        assertEquals(stockId, findStock.getId());
        assertEquals(stockCreate.ticker(), findStock.getTicker());
        assertEquals(stockCreate.quantity(), findStock.getQuantity());
        assertEquals(stockCreate.orderPrice(), findStock.getOrderPrice().getValue());
        assertEquals(stockCreate.orderDate(), findStock.getOrderDate());
    }

    @Test
    void modifyStock() {
        // Given
        Stock stock = StockFixture.anStock().build();
        stockRepository.save(stock);

        // When
        final int newQuantity = 3;

        StockEdit stockEdit = StockEdit
                .builder()
                .ticker(stock.getTicker())
                .quantity(newQuantity)
                .orderPrice(stock.getOrderPrice().getValue())
                .orderDate(stock.getOrderDate())
                .build();

        stockService.modifyStock(stock.getId(), stockEdit);
        Stock findStock = stockRepository.getStock(stock.getId());

        // Then
        assertEquals(newQuantity, findStock.getQuantity());
    }

    @Test
    void deleteStock() {
        // Given
        Stock stock = StockFixture.anStock().build();
        stockRepository.save(stock);

        // When
        stockService.deleteStock(stock.getId());

        // Then
        assertEquals(0, stockRepository.count());
    }

    @Test
    void deleteStockFail() {
        // Given
        Stock stock = StockFixture.anStock().build();
        stockRepository.save(stock);

        // When
        stockService.deleteStock(stock.getId());

        // Then
        assertNotEquals(1, stockRepository.count());
    }
}
