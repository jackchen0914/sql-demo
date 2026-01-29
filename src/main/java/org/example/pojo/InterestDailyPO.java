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
 * @since 2025-12-26
 */
@Getter
@Setter
@ToString
@TableName("InterestDaily")
public class InterestDailyPO implements Serializable {

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

    @TableField("BankAccount")
//    @TableId("BankAccount")
    private String bankAccount;

    @TableField("Type")
//    @TableId("Type")
    private String type;

    @TableField("ReferenceNo")
    private String referenceNo;

    @TableField("Interest")
    private BigDecimal interest;

    @TableField("Principal")
    private BigDecimal principal;

    @TableField("Rate")
    private BigDecimal rate;

    @TableField("CalculationPeriod")
    private Integer calculationPeriod;

    @TableField("Status")
    private String status;

    @TableField("PostDate")
    private LocalDateTime postDate;

    @TableField("SysInterestCal")
    private BigDecimal sysInterestCal;

    @TableId("Timestamp")
    private byte[] timestamp;

    @TableField("MethodType")
//    @TableId("MethodType")
    private String methodType;
}
