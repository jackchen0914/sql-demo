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
@TableName("BrokerageRate")
public class BrokerageRatePO implements Serializable {

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

//    @TableId("SeqNo")
    @TableField("SeqNo")
    private Integer seqNo;

    @TableField("FromAmount")
    private BigDecimal fromAmount;

    @TableField("ToAmount")
    private BigDecimal toAmount;

    @TableField("PercentRate")
    private BigDecimal percentRate;

    @TableField("AdditionalAmount")
    private BigDecimal additionalAmount;

    @TableField("TimeStamp")
    private byte[] timeStamp;
}
