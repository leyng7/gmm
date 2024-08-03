package com.gmm.api.dividend;

import com.gmm.modules.dividend.DividendService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DividendController {

    private final DividendService dividendService;

    @PostMapping("/dividend")
    public void addDividend(DividendAddRequest request) {
        dividendService.addDividend(request.toDividend());
    }

}
