package com.gmm.api.stock;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmm.domain.StockFixture;
import com.gmm.modules.stock.Stock;
import com.gmm.modules.stock.StockCreate;
import com.gmm.modules.stock.StockEdit;
import com.gmm.modules.stock.StockRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class StockControllerTest {

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
    @DisplayName("주식 주문 등록")
    void addStock() throws Exception {
        // given
        StockCreate request = StockFixture.anStockCreate().build();
        String json = objectMapper.writeValueAsString(request);

        // when
        ResultActions perform = mockMvc.perform(post("/stocks")
                .contentType(APPLICATION_JSON_VALUE)
                .content(json)
        );

        // then
        perform
                .andExpect(status().isOk())
                .andDo(print());

        assertEquals(1L, stockRepository.count());
    }

    @Test
    @DisplayName("주식 주문 1개 등록 - 필수 값 확인")
    void addStock2() throws Exception {
        // given
        StockCreate request = StockFixture.anStockCreate()
                .ticker(null)
                .orderPrice(null)
                .orderDate(null)
                .build();
        String json = objectMapper.writeValueAsString(request);

        // when
        ResultActions perform = mockMvc.perform(post("/stocks")
                .contentType(APPLICATION_JSON_VALUE)
                .content(json)
        );

        // then
        perform
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                .andExpect(jsonPath("$.validation.ticker").value("티커를 입력해주세요."))
                .andExpect(jsonPath("$.validation.orderPrice").value("주문 금액을 입력해주세요."))
                .andExpect(jsonPath("$.validation.orderDate").value("주문일을 입력해주세요."))
                .andDo(print());
    }

    @Test
    void getPageOfStock() throws Exception {
        // given
        Stock stock = StockFixture.anStock().build();
        Stock stock2 = StockFixture.anStock()
                .ticker("VOO")
                .build();
        stockRepository.saveAll(List.of(stock, stock2));

        // when
        ResultActions perform = mockMvc.perform(get("/stocks?page=1&size=10")
                .contentType(APPLICATION_JSON_VALUE)
        );

        // then
        perform
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.page").value(1))
                .andExpect(jsonPath("$.size").value(10))
                .andExpect(jsonPath("$.totalCount").value(2))
                .andExpect(jsonPath("$.items").exists())
                .andDo(print());
    }

    @Test
    void getStock() throws Exception {
        // given
        Stock stock = StockFixture.anStock().build();
        stockRepository.save(stock);

        // when
        ResultActions perform = mockMvc.perform(get("/stocks/{stockId}", stock.getId())
                .contentType(APPLICATION_JSON_VALUE)
        );

        // then
        perform
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(stock.getId()))
                .andExpect(jsonPath("$.ticker").value(stock.getTicker()))
                .andExpect(jsonPath("$.quantity").value(stock.getQuantity()))
                .andExpect(jsonPath("$.orderPrice").value(stock.getOrderPrice()))
                .andDo(print());
    }

    @Test
    void editStock() throws Exception {
        // given
        Stock stock = StockFixture.anStock().build();
        stockRepository.save(stock);

        // when
        StockEdit request = StockEdit.builder()
                .ticker("VOO")
                .quantity(5)
                .orderPrice(BigDecimal.valueOf(10.56))
                .orderDate(LocalDate.of(2024, 7, 29))
                .build();

        String json = objectMapper.writeValueAsString(request);
        ResultActions perform = mockMvc.perform(patch("/stocks/{stockId}", stock.getId())
                .contentType(APPLICATION_JSON_VALUE)
                .content(json)
        );

        // then
        perform
                .andExpect(status().isOk())
                .andDo(print());

        Stock findStock = stockRepository.getStock(stock.getId());
        Assertions.assertThat(findStock.getTicker()).isEqualTo(request.ticker());
        Assertions.assertThat(findStock.getQuantity()).isEqualTo(request.quantity());
        Assertions.assertThat(findStock.getOrderPrice()).isEqualTo(request.orderPrice());
        Assertions.assertThat(findStock.getOrderDate()).isEqualTo(request.orderDate());
    }

    @Test
    void deleteStock() throws Exception {
        // given
        Stock stock = StockFixture.anStock().build();
        stockRepository.save(stock);

        // when
        ResultActions perform = mockMvc.perform(delete("/stocks/{stockId}", stock.getId())
                .contentType(APPLICATION_JSON_VALUE)
        );

        // then
        perform
                .andExpect(status().isOk())
                .andDo(print());

        long count = stockRepository.count();
        Assertions.assertThat(count).isEqualTo(0);
    }

}
