package org.example.pojo.dtos;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class BrokerageWithRageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String market;
    private String ccy;
    private String source;
    private BigDecimal minBrokerage;
    private BigDecimal maxBrokerage;
    private String type;
    private BigDecimal percentRate;
    private BigDecimal additionalAmount;
    private String settingsValue;
}
