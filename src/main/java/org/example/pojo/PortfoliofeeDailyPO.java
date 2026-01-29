package org.example.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author JackChen
 * @since 2025-12-29
 */
@Getter
@Setter
@ToString
@TableName("PortfolioFee_Daily")
public class PortfoliofeeDailyPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("Date")
//    @TableId("Date")
    private LocalDateTime date;

    @TableField("ClntCode")
//    @TableId("ClntCode")
    private String clntCode;

    @TableField("AcctType")
//    @TableId("AcctType")
    private String acctType;

    @TableField("Market")
//    @TableId("Market")
    private String market;

    @TableField("CCY")
//    @TableId("CCY")
    private String ccy;

    @TableField("Type")
//    @TableId("Type")
    private String type;

    @TableField("PortfolioValue")
    private BigDecimal portfolioValue;

    @TableField("Rate")
    private BigDecimal rate;

    @TableField("CalculationPeriod")
    private Integer calculationPeriod;

    @TableField("Status")
    private String status;

    @TableField("PostDate")
    private LocalDateTime postDate;

    @TableId("ReferenceNo")
//    @TableField("ReferenceNo")
    private Integer referenceNo;

    @TableField("FeeBeforeMin")
    private BigDecimal feeBeforeMin;

    @TableField("Fee")
    private BigDecimal fee;

    @TableField("ORFee")
    private BigDecimal oRFee;
}
