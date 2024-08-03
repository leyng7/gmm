package com.gmm.modules.ticker;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TickerRepository extends JpaRepository<Ticker, Long> {

    default Ticker getTicker(Long tickerId) {
        return findById(tickerId).orElseThrow();
    }

    public Ticker findBySymbol(String symbol);

}
