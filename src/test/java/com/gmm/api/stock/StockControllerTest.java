package com.gmm.api.stock;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmm.domain.StockFixture;
import com.gmm.modules.stock.Stock;
import com.gmm.modules.stock.StockCreate;
import com.gmm.modules.stock.StockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    void addStockFail() throws Exception {
        // given
        StockCreate request = StockFixture.anStockCreate().build();
        String json = objectMapper.writeValueAsString(request);

        // when
        mockMvc.perform(post("/stocks")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andDo(print());

        // then
        assertNotEquals(0L, stockRepository.count());
    }


    @Test
    void getStocks() throws Exception {
        // given
        Stock stock = StockFixture.anStock().build();
        Stock stock2 = StockFixture.anStock()
                .ticker("VOO")
                .build();
        stockRepository.saveAll(List.of(stock, stock2));
        stockRepository.save(stock2);

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
                .andExpect(jsonPath("$.items[0].id").value(stock.getId()))
                .andExpect(jsonPath("$.items[0].ticker").value(stock.getTicker()))
                .andExpect(jsonPath("$.items[0].quantity").value(stock.getQuantity()))
                .andExpect(jsonPath("$.items[0].orderPrice").value(stock.getOrderPrice().getValue()))
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
                .andExpect(jsonPath("$.orderPrice").value(stock.getOrderPrice().getValue()))
                .andDo(print());
    }

}
