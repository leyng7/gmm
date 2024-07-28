package com.gmm.infra.config;

import com.gmm.modules.stock.Stock;
import com.gmm.modules.stock.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final StockRepository stockRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Stock> stocks = IntStream.range(1, 31)
                .mapToObj(i -> Stock.builder()
                        .ticker("O")
                        .quantity(i)
                        .orderPrice(BigDecimal.valueOf(55.33))
                        .orderDate(LocalDate.now())
                        .build())
                .collect(Collectors.toList());

        stockRepository.saveAll(stocks);
    }
}
