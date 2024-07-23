package com.gmm.api.stock;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmm.domain.StockFixture;
import com.gmm.infra.model.Money;
import com.gmm.modules.stock.StockCreate;
import com.gmm.modules.stock.StockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
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
        mockMvc.perform(post("/stocks")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andDo(print());

        // then
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


}
