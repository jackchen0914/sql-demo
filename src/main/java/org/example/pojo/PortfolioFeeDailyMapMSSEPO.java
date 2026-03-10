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
@TableName("PortfolioFeeDailyMapMSSE")
public class PortfolioFeeDailyMapMSSEPO implements Serializable {

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

    @TableField("Type")
    private String type;

    @TableField("PFF_CHRG_LOG_ID")
    private Long pffChrgLogId;

    @TableField("PFF_CHRG_LOG_DTL_ID")
    private Long pffChrgLogDtlId;

    @TableField("TXN_DATE")
    private LocalDateTime txnDate;

    @TableField("AC_ID")
    private String acId;

    @TableField("CHRG_CCY_CDE")
    private String chrgCcyCde;
}
