package org.example.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

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
@TableName("HoldCashMapMSSE")
public class HoldCashMapMSSEPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("VoucherNo")
    private Integer voucherNo;

    @TableField("Market")
    private String market;

    @TableField("AC_FUND_HOLD_RID")
    private Long acFundHoldRid;

    @TableField("AC_FUND_HOLD_TXN_ID")
    private Long acFundHoldTxnId;
}
