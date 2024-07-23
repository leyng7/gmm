package com.gmm.modules.stock;

import com.gmm.infra.model.Money;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StockService {

    private final StockRepository stockRepository;

    public Stock getStock(Long stockId) {
        return stockRepository.getStock(stockId);
    }

    public Page<Stock> getPageOfStock() {
        return null;
    }

    @Transactional
    public Long addStock(StockCreate stockCreate) {
        Stock stock = Stock.builder()
                .ticker(stockCreate.ticker())
                .quantity(stockCreate.quantity())
                .orderPrice(Money.of(stockCreate.orderPrice()))
                .orderDate(stockCreate.orderDate())
                .build();

        return stockRepository.save(stock).getId();
    }

    @Transactional
    public void modifyStock(Long stockId, StockEdit stockEdit) {
        Stock stock = stockRepository.getStock(stockId);
        stock.edit(
                stockEdit.ticker(),
                stockEdit.quantity(),
                Money.of(stockEdit.orderPrice()),
                stockEdit.orderDate()
        );
    }

    @Transactional
    public void deleteStock(Long stockId) {
        stockRepository.deleteById(stockId);
    }


}
