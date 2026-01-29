package org.example.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2026-01-07
 */
@Getter
@Setter
@ToString
@TableName("MC_CONV_EVNT")
public class McConvEvntPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "CEVENT_ID", type = IdType.INPUT)
    private Long ceventId;

    @TableField("NEW_INSTR_ID")
    private Long newInstrId;

    @TableField("SHAR_RATIO_INIT")
    private BigDecimal sharRatioInit;

    @TableField("SHAR_RATIO_TO")
    private BigDecimal sharRatioTo;

    @TableField("EFF_DATE")
    private LocalDateTime effDate;

    @TableField("CONV_DATE")
    private LocalDateTime convDate;

    @TableField("IS_CCASS_STK")
    private String isCcassStk;

    @TableField("AC_HOLD_STK_TXN_ID")
    private Long acHoldStkTxnId;
}
