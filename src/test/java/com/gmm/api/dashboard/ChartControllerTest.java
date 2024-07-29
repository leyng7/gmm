package com.gmm.api.dashboard;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmm.domain.StockFixture;
import com.gmm.modules.stock.Stock;
import com.gmm.modules.stock.StockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class ChartControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StockRepository stockRepository;

    @BeforeEach
    void clean() {
        stockRepository.deleteAll();
    }

    @Test
    void getChartOfStock() throws Exception {
        // given
        List<Stock> stocks = IntStream.range(0, 10)
                .mapToObj(i -> StockFixture.anStock().build())
                .collect(Collectors.toList());

        stockRepository.saveAll(stocks);

        BigDecimal sum = stocks.stream()
                .map(Stock::getOrderPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // when
        ResultActions perform = mockMvc.perform(get("/dashboard/stocks")
                .contentType(APPLICATION_JSON_VALUE)
        );

        // then

        perform
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("O"))
                .andExpect(jsonPath("$[0].value").value(sum.doubleValue()))
                .andDo(print());
    }
}
