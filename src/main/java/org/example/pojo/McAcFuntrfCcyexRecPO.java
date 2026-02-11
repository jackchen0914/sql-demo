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
 * @since 2026-02-09
 */
@Getter
@Setter
@ToString
@TableName("MC_AC_FUNTRF_CCYEX_REC")
public class McAcFuntrfCcyexRecPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "AC_FUNTRF_CCYEX_RID", type = IdType.INPUT)
    private Long acFuntrfCcyexRid;

    @TableField("CMPNY_IBUSDATE")
    private LocalDateTime cmpnyIbusdate;

    @TableField("CMPNY_BUSDATE")
    private LocalDateTime cmpnyBusdate;

    @TableField("AC_ID")
    private String acId;

    @TableField("SEGR_FUND_ID")
    private Long segrFundId;

    @TableField("CCY_CDE")
    private String ccyCde;

    @TableField("CMPNY_CDE")
    private String cmpnyCde;

    @TableField("FUNTRF_CCYEX_TYP_CDE")
    private String funtrfCcyexTypCde;

    @TableField("VAL_DATE")
    private LocalDateTime valDate;

    @TableField("AMT")
    private BigDecimal amt;

    @TableField("REMRK")
    private String remrk;

    @TableField("IS_PRT_RCPT")
    private String isPrtRcpt;

    @TableField("LAST_PRT_RCPT_TIME")
    private LocalDateTime lastPrtRcptTime;

    @TableField("LAST_PRT_RCPT_BY")
    private String lastPrtRcptBy;

    @TableField("CMPNY_BANK_AC_ID")
    private Long cmpnyBankAcId;

    @TableField("EXCH_RATE_TYP_CDE")
    private String exchRateTypCde;

    @TableField("EXCH_RATE")
    private BigDecimal exchRate;

    @TableField("RND_MTD")
    private String rndMtd;

    @TableField("RCPT_NUM")
    private String rcptNum;

    @TableField("TO_AC_ID")
    private String toAcId;

    @TableField("TO_SEGR_FUND_ID")
    private Long toSegrFundId;

    @TableField("TO_CMPNY_CDE")
    private String toCmpnyCde;

    @TableField("TO_CCY_CDE")
    private String toCcyCde;

    @TableField("TO_AMT")
    private BigDecimal toAmt;

    @TableField("TO_CMPNY_BANK_AC_ID")
    private Long toCmpnyBankAcId;

    @TableField("FUNTRF_CCYEX_STAT_CDE")
    private String funtrfCcyexStatCde;

    @TableField("FUND_CHNL_CDE")
    private String fundChnlCde;

    @TableField("IS_REV")
    private String isRev;

    @TableField("REV_AC_FUNTRF_CCYEX_RID")
    private Long revAcFuntrfCcyexRid;

    @TableField("IS_UNDER_REVRSE")
    private String isUnderRevrse;

    @TableField("IS_TODAY_REV")
    private String isTodayRev;

    @TableField("BASE_CCY_EQU_AMT")
    private BigDecimal baseCcyEquAmt;

    @TableField("BASE_CCY_EQU_TO_AMT")
    private BigDecimal baseCcyEquToAmt;

    @TableField("TO_RCPT_NUM")
    private String toRcptNum;

    @TableField("IS_IGNR_DAT_SYNC")
    private String isIgnrDatSync;

    @TableField("IS_AUTO_APRV")
    private String isAutoAprv;

    @TableField("RVIS_UNIT")
    private String rvisUnit;

    @TableField("RVIS_BY")
    private String rvisBy;

    @TableField("RVIS_CMPNY_IBUSDATE")
    private LocalDateTime rvisCmpnyIbusdate;

    @TableField("RVIS_CMPNY_BUSDATE")
    private LocalDateTime rvisCmpnyBusdate;

    @TableField("LAST_RVIS_TIME")
    private LocalDateTime lastRvisTime;

    @TableField("CHK_BY")
    private String chkBy;

    @TableField("APRV_REJ_CMPNY_IBUSDATE")
    private LocalDateTime aprvRejCmpnyIbusdate;

    @TableField("APRV_REJ_CMPNY_BUSDATE")
    private LocalDateTime aprvRejCmpnyBusdate;

    @TableField("APRV_REJ_TIME")
    private LocalDateTime aprvRejTime;

    @TableField("REJ_REASN")
    private String rejReasn;

    @TableField("REVW_BY")
    private String revwBy;

    @TableField("REVW_CMPNY_IBUSDATE")
    private LocalDateTime revwCmpnyIbusdate;

    @TableField("REVW_CMPNY_BUSDATE")
    private LocalDateTime revwCmpnyBusdate;

    @TableField("REVW_TIME")
    private LocalDateTime revwTime;

    @TableField("REV_BY")
    private String revBy;

    @TableField("REV_CMPNY_IBUSDATE")
    private LocalDateTime revCmpnyIbusdate;

    @TableField("REV_CMPNY_BUSDATE")
    private LocalDateTime revCmpnyBusdate;

    @TableField("REV_TIME")
    private LocalDateTime revTime;

    @TableField("IS_TRNFR")
    private String isTrnfr;

    @TableField("IS_CCYEX")
    private String isCcyex;

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

    @TableField("FUND_BAT_IMP_ID")
    private Long fundBatImpId;

    @TableField("FUND_BAT_IMP_CDE")
    private String fundBatImpCde;

    @TableField("INIT_USER")
    private String initUser;

    @TableField("INIT_USER_UNIT")
    private String initUserUnit;

    @TableField("EXTRNL_REF_NUM")
    private String extrnlRefNum;

    @TableField("EXTRNL_SYS_CDE")
    private String extrnlSysCde;

    @TableField("CONV_PURP_CDE")
    private String convPurpCde;

    @TableField("FUND_TP_SCOPE_TYP_CDE")
    private String fundTpScopeTypCde;

    @TableField("FUND_TP_RELTN_CDE")
    private String fundTpReltnCde;
}
