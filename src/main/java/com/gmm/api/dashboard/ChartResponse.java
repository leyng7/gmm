package com.gmm.api.dashboard;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ChartResponse {

    private final String name;
    private final BigDecimal value;

    public ChartResponse(String name, BigDecimal value) {
        this.name = name;
        this.value = value;
    }

}
