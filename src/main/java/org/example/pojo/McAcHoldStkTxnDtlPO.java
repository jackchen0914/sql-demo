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
 * @since 2025-12-24
 */
@Getter
@Setter
@ToString
@TableName("MC_AC_HOLD_STK_TXN_DTL")
public class McAcHoldStkTxnDtlPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "AC_HOLD_STK_TXN_DTL_ID", type = IdType.INPUT)
    private Long acHoldStkTxnDtlId;

    @TableField("AC_HOLD_STK_TXN_ID")
    private Long acHoldStkTxnId;

    @TableField("HOLD_STK_TXN_TYP_CDE")
    private String holdStkTxnTypCde;

    @TableField("TXN_REF_NUM")
    private String txnRefNum;

    @TableField("TXN_TYP_ID")
    private Long txnTypId;

    @TableField("AC_ID")
    private String acId;

    @TableField("CMPNY_CDE")
    private String cmpnyCde;

    @TableField("INSTR_ID")
    private Long instrId;

    @TableField("INSTCL_ID")
    private Long instclId;

    @TableField("MRKT_ID")
    private Long mrktId;

    @TableField("MRKT_BUSDATE")
    private LocalDateTime mrktBusdate;

    @TableField("EXTRNL_REF_NUM")
    private String extrnlRefNum;

    @TableField("HOLD_STK_QTY")
    private BigDecimal holdStkQty;

    @TableField("EXPR_DATE")
    private LocalDateTime exprDate;

    @TableField("HOLD_STK_STAT_CDE")
    private String holdStkStatCde;

    @TableField("HOLD_STK_CHNL_CDE")
    private String holdStkChnlCde;

    @TableField("REMRK")
    private String remrk;

    @TableField("HOLD_DATE")
    private LocalDateTime holdDate;

    @TableField("HOLD_BY")
    private String holdBy;

    @TableField("RELSE_DATE")
    private LocalDateTime relseDate;

    @TableField("RELSE_BY")
    private String relseBy;

    @TableField("RELSE_STK_QTY")
    private BigDecimal relseStkQty;

    @TableField("SRC_SYS_CDE")
    private String srcSysCde;

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

    @TableField("INTRNL_REF")
    private String intrnlRef;
}
