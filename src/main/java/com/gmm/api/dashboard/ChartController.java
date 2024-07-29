package com.gmm.api.dashboard;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChartController {

    private final DashboardQueryRepository dashboardQueryRepository;

    @GetMapping("/charts/stocks")
    public List<ChartResponse> getChartOfStock() {
        return dashboardQueryRepository.getChartOfStock();
    }

    @GetMapping("/charts/tickers")
    public List<ChartResponse> getChartOfTicker() {
        return dashboardQueryRepository.getChartOfTicker();
    }

}
