package com.gmm.modules.dividend;

import com.gmm.modules.ticker.Ticker;
import com.gmm.modules.ticker.TickerRepository;
import com.gmm.persistence.dividend.DividendJpaRepository;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.gmm.modules.dividend.QDividendEntity.dividendEntity;
import static com.gmm.modules.ticker.QTicker.ticker;

@Component
@RequiredArgsConstructor
public class DividendReader {

    private final TickerRepository tickerRepository;
    private final DividendJpaRepository dividendJpaRepository;
    private final JPAQueryFactory jpaQueryFactory;

    public DividendWithTicker read(Long dividendId) {
        var entity = dividendJpaRepository.findById(dividendId).orElseThrow();
        var ticker = tickerRepository.findBySymbol(entity.getTicker());
        return new DividendWithTicker(ticker, entity.getAmount());
    }

    public List<DividendWithTicker> readAll() {
        List<Tuple> tuples = jpaQueryFactory
                .select(
                        dividendEntity.id,
                        dividendEntity.amount,
                        ticker.id,
                        ticker.symbol,
                        ticker.type
                )
                .from(dividendEntity)
                .join(ticker).on(dividendEntity.ticker.eq(ticker.symbol))
                .fetch();

        return tuples.stream().map(tuple ->
                new DividendWithTicker(
                        tuple.get(dividendEntity.id),
                        new Ticker(
                                tuple.get(ticker.id),
                                tuple.get(ticker.symbol),
                                tuple.get(ticker.type)
                        ),
                        tuple.get(dividendEntity.amount)
                )).collect(Collectors.toList());
    }

    public Page<Dividend> page(Pageable pageable) {
        Page<DividendEntity> page = dividendJpaRepository.findAll(pageable);
        return page.map(entity -> new Dividend(
                entity.getId(),
                entity.getTicker(),
                entity.getAmount()
        ));
    }
}
