package com.gmm.api.dashboard;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.gmm.modules.stock.QStock.stock;

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

}
