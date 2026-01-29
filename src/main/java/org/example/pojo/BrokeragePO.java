package org.example.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author JackChen
 * @since 2026-01-27
 */
@Getter
@Setter
@ToString
@TableName("Brokerage")
public class BrokeragePO implements Serializable {

    private static final long serialVersionUID = 1L;

//    @TableId("Market")
    @TableField("Market")
    private String market;

//    @TableId("CCY")
    @TableField("CCY")
    private String ccy;

//    @TableId("Source")
    @TableField("Source")
    private String source;

    @TableField("Type")
    private String type;

    @TableField("Method")
    private String method;

    @TableField("MinBrokerage")
    private BigDecimal minBrokerage;

    @TableField("MaxBrokerage")
    private BigDecimal maxBrokerage;

    @TableField("Discount")
    private BigDecimal discount;

    @TableField("AdditionalDiscount")
    private BigDecimal additionalDiscount;

    @TableField("TimeStamp")
    private byte[] timeStamp;
}
