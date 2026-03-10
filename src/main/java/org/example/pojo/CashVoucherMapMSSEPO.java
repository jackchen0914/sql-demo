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
@TableName("CashVoucherMapMSSE")
public class CashVoucherMapMSSEPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("VoucherNo")
    private Integer voucherNo;

    @TableField("Market")
    private String market;

    @TableField("AC_FUND_RID")
    private Long acFundRid;
}
