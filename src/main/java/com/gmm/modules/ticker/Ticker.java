package com.gmm.modules.ticker;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Ticker {

    private Long id;     // 아이디

    @Builder
    public Ticker(Long id) {
        this.id = id;
    }

    public void edit() {
        throw new UnsupportedOperationException("Unsupported edit");
    }
}
