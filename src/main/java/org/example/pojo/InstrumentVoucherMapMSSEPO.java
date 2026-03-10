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
@TableName("InstrumentVoucherMapMSSE")
public class InstrumentVoucherMapMSSEPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("VoucherNo")
    private Integer voucherNo;

    @TableField("Market")
    private String market;

    @TableField("AC_STK_TXN_DTL_STK_STAT_ID")
    private Long acStkTxnDtlStkStatId;

    @TableField("AC_STK_TXN_DTL_ID")
    private Long acStkTxnDtlId;

    @TableField("STK_TXN_ID")
    private Long stkTxnId;

    @TableField("STK_MEMO_TXN_ID")
    private Long stkMemoTxnId;

    @TableField("STK_MEMO_TXN_REQ_ID")
    private Long stkMemoTxnReqId;
}
