package com.gmm.modules.stock;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {

    default Stock getStock(final Long stockId) {
        return findById(stockId).orElseThrow();
    }

}
