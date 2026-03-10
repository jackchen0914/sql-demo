package org.example.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author JackChen
 * @since 2026-03-03
 */
@Getter
@Setter
@ToString
@TableName("InterestDailyMapMSSE")
public class InterestDailyMapMSSEPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("Date")
    private LocalDateTime date;

    @TableField("ClntCode")
    private String clntCode;

    @TableField("AcctType")
    private String acctType;

    @TableField("Market")
    private String market;

    @TableField("CCY")
    private String ccy;

    @TableField("BankAccount")
    private String bankAccount;

    @TableField("Type")
    private String type;

    @TableField("ACUINT_TXN_DTL_ID")
    private Long acuintTxnDtlId;

    @TableField("ACUINT_TXN_ID")
    private Long acuintTxnId;

    @TableField("TXN_DATE")
    private LocalDateTime txnDate;

    @TableField("AC_ID")
    private String acId;

    @TableField("CCY_CDE")
    private String ccyCde;
}
