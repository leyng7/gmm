package com.gmm.modules.ticker;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ticker {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticker_id")
    private Long id;

    private String symbol;

    private String type;

    @Builder
    public Ticker(Long id, String symbol, String type) {
        this.id = id;
        this.symbol = symbol;
        this.type = type;
    }

    public Ticker(String symbol, String type) {
        this(null, symbol, type);
    }

    public void edit(String symbol, String type) {
        this.symbol = symbol;
        this.type = type;
    }

}
