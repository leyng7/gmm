package com.gmm.api.dashboard;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.gmm.modules.stock.QStock.stock;
import static com.gmm.modules.ticker.QTicker.ticker;

@Repository
@RequiredArgsConstructor
public class DashboardQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<ChartResponse> getChartOfStock() {
        return jpaQueryFactory
                .select(Projections.constructor(
                        ChartResponse.class,
                        stock.ticker,
                        stock.orderPrice.multiply(stock.quantity).sum()
                ))
                .from(stock)
                .groupBy(stock.ticker)
                .fetch();
    }

    public List<ChartResponse> getChartOfTicker() {
        return jpaQueryFactory
                .select(Projections.constructor(
                        ChartResponse.class,
                        ticker.type,
                        stock.orderPrice.multiply(stock.quantity).sum()
                ))
                .from(ticker)
                .leftJoin(stock).on(ticker.symbol.eq(stock.ticker))
                .groupBy(ticker.type)
                .fetch();
    }
}
