package com.gmm.api.stock;

import com.gmm.modules.stock.StockCreate;
import com.gmm.modules.stock.StockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class StockController  {

    private final StockService stockService;

    @PostMapping("/stocks")
    public ResponseEntity<Void> addStock(
            @RequestBody @Valid StockCreate request
    ) {
        request.validate();
        stockService.addStock(request);

        return ResponseEntity.ok().build();
    }


}
