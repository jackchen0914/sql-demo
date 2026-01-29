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
 * @since 2026-01-16
 */
@Getter
@Setter
@ToString
@TableName("MC_LOCTN_STK_TXN_DTL")
public class McLoctnStkTxnDtlPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "LOCTN_STK_TXN_DTL_ID", type = IdType.INPUT)
    private Long loctnStkTxnDtlId;

    @TableField("CMPNY_CDE")
    private String cmpnyCde;

    @TableField("STK_TXN_ID")
    private Long stkTxnId;

    @TableField("TXN_TYP_ID")
    private Long txnTypId;

    @TableField("TXN_TYP_ACTN_CDE")
    private String txnTypActnCde;

    @TableField("TXN_REF_NUM")
    private String txnRefNum;

    @TableField("INSTR_ID")
    private Long instrId;

    @TableField("INSTCL_ID")
    private Long instclId;

    @TableField("INSTCL_VID")
    private Long instclVid;

    @TableField("MRKT_ID")
    private Long mrktId;

    @TableField("VAL_DATE")
    private LocalDateTime valDate;

    @TableField("INPT_DATE")
    private LocalDateTime inptDate;

    @TableField("MRKT_BUSDATE")
    private LocalDateTime mrktBusdate;

    @TableField("STK_CHNL_CDE")
    private String stkChnlCde;

    @TableField("STK_QTY")
    private BigDecimal stkQty;

    @TableField("REMRK")
    private String remrk;

    @TableField("IS_AUTO_APRV")
    private String isAutoAprv;

    @TableField("IS_MEMO")
    private String isMemo;

    @TableField("IS_TRNFR")
    private String isTrnfr;

    @TableField("IS_REV")
    private String isRev;

    @TableField("IS_SHOW_IN_STMT")
    private String isShowInStmt;

    @TableField("IS_TODAY_REV")
    private String isTodayRev;

    @TableField("REV_LOCTN_STK_TXN_DTL_ID")
    private Long revLoctnStkTxnDtlId;

    @TableField("IS_IGNR_DAT_SYNC")
    private String isIgnrDatSync;

    @TableField("STK_LOCTN_ID")
    private Long stkLoctnId;

    @TableField("SLEDG_ID")
    private Long sledgId;

    @TableField("STK_STAT_CDE")
    private String stkStatCde;

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
