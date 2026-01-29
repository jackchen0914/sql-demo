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
@TableName("MC_AC_STK_TXN_DTL")
public class McAcStkTxnDtlPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "AC_STK_TXN_DTL_ID", type = IdType.INPUT)
    private Long acStkTxnDtlId;

    @TableField("STK_TXN_ID")
    private Long stkTxnId;

    @TableField("TXN_TYP_ID")
    private Long txnTypId;

    @TableField("TXN_TYP_ACTN_CDE")
    private String txnTypActnCde;

    @TableField("TXN_REF_NUM")
    private String txnRefNum;

    @TableField("CMPNY_CDE")
    private String cmpnyCde;

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

    @TableField("REV_AC_STK_TXN_DTL_ID")
    private Long revAcStkTxnDtlId;

    @TableField("IS_IGNR_DAT_SYNC")
    private String isIgnrDatSync;

    @TableField("AC_ID")
    private String acId;

    @TableField("AVG_COST")
    private BigDecimal avgCost;

    @TableField("AVG_COST_CCY_CDE")
    private String avgCostCcyCde;

    @TableField("PRIMY_REMRK_FR_STMT")
    private String primyRemrkFrStmt;

    @TableField("SYS_PRIMY_REMRK_FR_STMT")
    private String sysPrimyRemrkFrStmt;

    @TableField("STMT_MODULE_CDE")
    private String stmtModuleCde;

    @TableField("STMT_MODULE_KEY")
    private String stmtModuleKey;

    @TableField("PRIMY_AC_STK_STMT_REMRK_ID")
    private Long primyAcStkStmtRemrkId;

    @TableField("PRIMY_AC_STK_SYS_STMT_REMRK_ID")
    private Long primyAcStkSysStmtRemrkId;

    @TableField("EXTRNL_TXN_REF_NUM")
    private String extrnlTxnRefNum;

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

    @TableField("IS_TP_TXN")
    private String isTpTxn;

    @TableField("IS_OTCR_TXN")
    private String isOtcrTxn;
}
