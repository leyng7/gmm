package com.gmm.infra.model;

import jakarta.persistence.AttributeConverter;

import java.math.BigDecimal;

public class MoneyConverter implements AttributeConverter<Money, BigDecimal> {

    @Override
    public BigDecimal convertToDatabaseColumn(Money money) {
        return money == null ? null : money.getValue();
    }

    @Override
    public Money convertToEntityAttribute(BigDecimal bigDecimal) {
        return bigDecimal == null ? null : Money.of(bigDecimal);
    }

}
