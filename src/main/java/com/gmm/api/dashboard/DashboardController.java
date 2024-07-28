package com.gmm.api.dashboard;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardQueryRepository dashboardQueryRepository;

    @GetMapping("/dashboard/stocks")
    public List<ChartResponse> getDashboardOfStock() {
        return dashboardQueryRepository.getChartOfStock();
    }

}
