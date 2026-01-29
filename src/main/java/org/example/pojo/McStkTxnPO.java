package org.example.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2025-12-19
 */
@Getter
@Setter
@ToString
@TableName("MC_STK_TXN")
public class McStkTxnPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "STK_TXN_ID", type = IdType.INPUT)
    private Long stkTxnId;

    @TableField("REV_STK_TXN_ID")
    private Long revStkTxnId;

    @TableField("STK_APRV_STAT_CDE")
    private String stkAprvStatCde;

    @TableField("IS_REV")
    private String isRev;

    @TableField("IS_AWAITING")
    private String isAwaiting;

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
