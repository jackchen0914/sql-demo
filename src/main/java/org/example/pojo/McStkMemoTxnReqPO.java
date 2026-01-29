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
 * @since 2026-01-16
 */
@Getter
@Setter
@ToString
@TableName("MC_STK_MEMO_TXN_REQ")
public class McStkMemoTxnReqPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "STK_MEMO_TXN_REQ_ID", type = IdType.INPUT)
    private Long stkMemoTxnReqId;

    @TableField("STK_MEMO_TXN_ID")
    private Long stkMemoTxnId;

    @TableField("STK_TXN_ID")
    private Long stkTxnId;

    @TableField("REV_STK_TXN_ID")
    private Long revStkTxnId;

    @TableField("IS_REV")
    private String isRev;

    @TableField("STK_REQ_STAT_CDE")
    private String stkReqStatCde;

    @TableField("STK_APRV_STAT_CDE")
    private String stkAprvStatCde;

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
