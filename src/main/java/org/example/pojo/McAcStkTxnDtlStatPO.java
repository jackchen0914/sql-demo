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
 * @since 2025-12-19
 */
@Getter
@Setter
@ToString
@TableName("MC_AC_STK_TXN_DTL_STAT")
public class McAcStkTxnDtlStatPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "AC_STK_TXN_DTL_STK_STAT_ID", type = IdType.INPUT)
    private Long acStkTxnDtlStkStatId;

    @TableField("AC_STK_TXN_DTL_ID")
    private Long acStkTxnDtlId;

    @TableField("STK_STAT_CDE")
    private String stkStatCde;

    @TableField("STK_QTY")
    private BigDecimal stkQty;

    @TableField("REC_VER_NUM")
    private Long recVerNum;

    @TableField("INIT_TIME")
    private LocalDateTime initTime;

    @TableField("LAST_UPD_TIME")
    private LocalDateTime lastUpdTime;

    @TableField("LAST_UPD_BY")
    private String lastUpdBy;

    @TableField("TAG_SEQ")
    private Long tagSeq;
}
