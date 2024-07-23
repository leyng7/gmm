package com.gmm.api.stock;

import com.gmm.infra.model.PageResponse;
import com.gmm.infra.model.Pageable;
import com.gmm.modules.stock.Stock;
import com.gmm.modules.stock.StockCreate;
import com.gmm.modules.stock.StockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @PostMapping("/stocks")
    public ResponseEntity<Void> addStock(
            @RequestBody @Valid StockCreate request
    ) {
        request.validate();
        stockService.addStock(request);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/stocks")
    public PageResponse<StockResponse> getStocks(Pageable pageable) {
        Page<Stock> page = stockService.getPageOfStock(pageable);
        return PageResponse.of(page, StockResponse.class);
    }

}
