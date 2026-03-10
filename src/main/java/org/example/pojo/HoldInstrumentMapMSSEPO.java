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
@TableName("HoldInstrumentMapMSSE")
public class HoldInstrumentMapMSSEPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("VoucherNo")
    private Integer voucherNo;

    @TableField("Market")
    private String market;

    @TableField("AC_HOLD_STK_TXN_DTL_ID")
    private Long acHoldStkTxnDtlId;

    @TableField("AC_HOLD_STK_TXN_ID")
    private Long acHoldStkTxnId;
}
