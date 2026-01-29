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
 * @since 2025-12-12
 */
@Getter
@Setter
@ToString
@TableName("MC_AC_FUND_REC")
public class McAcFundRecPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "AC_FUND_RID", type = IdType.INPUT)
    private Long acFundRid;

    @TableField("CMPNY_IBUSDATE")
    private LocalDateTime cmpnyIbusdate;

    @TableField("CMPNY_BUSDATE")
    private LocalDateTime cmpnyBusdate;

    @TableField("MRKT_IBUSDATE")
    private LocalDateTime mrktIbusdate;

    @TableField("MRKT_BUSDATE")
    private LocalDateTime mrktBusdate;

    @TableField("AC_ID")
    private String acId;

    @TableField("CCY_CDE")
    private String ccyCde;

    @TableField("SEGR_FUND_ID")
    private Long segrFundId;

    @TableField("CMPNY_CDE")
    private String cmpnyCde;

    @TableField("TXN_TYP_ID")
    private Long txnTypId;

    @TableField("VAL_DATE")
    private LocalDateTime valDate;

    @TableField("AMT")
    private BigDecimal amt;

    @TableField("IS_NON_AC_HLDR")
    private String isNonAcHldr;

    @TableField("NON_AC_HLDR_NAM")
    private String nonAcHldrNam;

    @TableField("REMRK")
    private String remrk;

    @TableField("BANK_CDE")
    private String bankCde;

    @TableField("BANK_OTH")
    private String bankOth;

    @TableField("BANK_REF")
    private String bankRef;

    @TableField("BANK_DATE")
    private LocalDateTime bankDate;

    @TableField("BANK_CLR_DATE")
    private LocalDateTime bankClrDate;

    @TableField("CHQ_PICKUP_UNIT_CDE")
    private String chqPickupUnitCde;

    @TableField("PAYE_NAM")
    private String payeNam;

    @TableField("OTH_AC_PAYE_NAM")
    private String othAcPayeNam;

    @TableField("CLNT_BANK_CDE")
    private String clntBankCde;

    @TableField("CLNT_BANK_AC_NUM")
    private String clntBankAcNum;

    @TableField("IS_PRT_RCPT")
    private String isPrtRcpt;

    @TableField("LAST_PRT_RCPT_TIME")
    private LocalDateTime lastPrtRcptTime;

    @TableField("LAST_PRT_RCPT_BY")
    private String lastPrtRcptBy;

    @TableField("CMPNY_BANK_AC_ID")
    private Long cmpnyBankAcId;

    @TableField("FUND_STAT_CDE")
    private String fundStatCde;

    @TableField("FUND_CHNL_CDE")
    private String fundChnlCde;

    @TableField("FUND_BAT_IMP_ID")
    private Long fundBatImpId;

    @TableField("FUND_BAT_IMP_CDE")
    private String fundBatImpCde;

    @TableField("CMPNY_CHQ_BAT_EXP_ID")
    private Long cmpnyChqBatExpId;

    @TableField("CMPNY_CHQ_BAT_EXP_CDE")
    private String cmpnyChqBatExpCde;

    @TableField("TXN_TYP_ACTN_CDE")
    private String txnTypActnCde;

    @TableField("IS_MEMO")
    private String isMemo;

    @TableField("IS_CHQ")
    private String isChq;

    @TableField("IS_CHRG")
    private String isChrg;

    @TableField("IS_TRNFR")
    private String isTrnfr;

    @TableField("IS_REV")
    private String isRev;

    @TableField("REV_AC_FUND_RID")
    private Long revAcFundRid;

    @TableField("IS_DHON")
    private String isDhon;

    @TableField("DHON_CHQ_AC_FUND_RID")
    private Long dhonChqAcFundRid;

    @TableField("IS_RTUN")
    private String isRtun;

    @TableField("RTUN_AC_FUND_RID")
    private Long rtunAcFundRid;

    @TableField("IS_UNDER_REVRSE")
    private String isUnderRevrse;

    @TableField("IS_UNDER_DHON")
    private String isUnderDhon;

    @TableField("IS_UNDER_RTUN")
    private String isUnderRtun;

    @TableField("RCPT_NUM")
    private String rcptNum;

    @TableField("IS_TODAY_REV")
    private String isTodayRev;

    @TableField("IS_TRIG_CREAT")
    private String isTrigCreat;

    @TableField("PRIMY_REMRK_FR_STMT")
    private String primyRemrkFrStmt;

    @TableField("PRIMY_SYS_REMRK_FR_STMT")
    private String primySysRemrkFrStmt;

    @TableField("BASE_CCY_EQU_AMT")
    private BigDecimal baseCcyEquAmt;

    @TableField("IS_AUTO_APRV")
    private String isAutoAprv;

    @TableField("IS_IGNR_DAT_SYNC")
    private String isIgnrDatSync;

    @TableField("RVIS_UNIT")
    private String rvisUnit;

    @TableField("RVIS_BY")
    private String rvisBy;

    @TableField("RVIS_CMPNY_IBUSDATE")
    private LocalDateTime rvisCmpnyIbusdate;

    @TableField("RVIS_CMPNY_BUSDATE")
    private LocalDateTime rvisCmpnyBusdate;

    @TableField("RVIS_MRKT_IBUSDATE")
    private LocalDateTime rvisMrktIbusdate;

    @TableField("RVIS_MRKT_BUSDATE")
    private LocalDateTime rvisMrktBusdate;

    @TableField("LAST_RVIS_TIME")
    private LocalDateTime lastRvisTime;

    @TableField("CHK_BY")
    private String chkBy;

    @TableField("APRV_REJ_CMPNY_IBUSDATE")
    private LocalDateTime aprvRejCmpnyIbusdate;

    @TableField("APRV_REJ_CMPNY_BUSDATE")
    private LocalDateTime aprvRejCmpnyBusdate;

    @TableField("APRV_REJ_MRKT_IBUSDATE")
    private LocalDateTime aprvRejMrktIbusdate;

    @TableField("APRV_REJ_MRKT_BUSDATE")
    private LocalDateTime aprvRejMrktBusdate;

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

    @TableField("REVW_MRKT_IBUSDATE")
    private LocalDateTime revwMrktIbusdate;

    @TableField("REVW_MRKT_BUSDATE")
    private LocalDateTime revwMrktBusdate;

    @TableField("REVW_TIME")
    private LocalDateTime revwTime;

    @TableField("REV_DHON_RTUN_BY")
    private String revDhonRtunBy;

    @TableField("REV_DHON_RTUN_CMPNY_IBUSDATE")
    private LocalDateTime revDhonRtunCmpnyIbusdate;

    @TableField("REV_DHON_RTUN_CMPNY_BUSDATE")
    private LocalDateTime revDhonRtunCmpnyBusdate;

    @TableField("REV_DHON_RTUN_MRKT_IBUSDATE")
    private LocalDateTime revDhonRtunMrktIbusdate;

    @TableField("REV_DHON_RTUN_MRKT_BUSDATE")
    private LocalDateTime revDhonRtunMrktBusdate;

    @TableField("REV_DHON_RTUN_TIME")
    private LocalDateTime revDhonRtunTime;

    @TableField("IS_ELECTRONIC")
    private String isElectronic;

    @TableField("IS_WORK")
    private String isWork;

    @TableField("AC_FUND_TXN_RID")
    private Long acFundTxnRid;

    @TableField("INSTCL_ID")
    private Long instclId;

    @TableField("MRKT_ID")
    private Long mrktId;

    @TableField("CALNDR_ID")
    private Long calndrId;

    @TableField("INSTR_BAT_GRP_ID")
    private Long instrBatGrpId;

    @TableField("IS_OPERATE_IN_FUND")
    private String isOperateInFund;

    @TableField("AC_FUND_EPAYMENT_BAT_ID")
    private Long acFundEpaymentBatId;

    @TableField("AC_FUND_EPAYMENT_BAT_CDE")
    private String acFundEpaymentBatCde;

    @TableField("UNSEND_TO_CPS_BY")
    private String unsendToCpsBy;

    @TableField("UNSEND_TO_CPS_TIME")
    private LocalDateTime unsendToCpsTime;

    @TableField("UNSEND_TO_CPS_CMPNY_IBUSDATE")
    private LocalDateTime unsendToCpsCmpnyIbusdate;

    @TableField("UNSEND_TO_CPS_CMPNY_BUSDATE")
    private LocalDateTime unsendToCpsCmpnyBusdate;

    @TableField("READY_TO_CPS_BY")
    private String readyToCpsBy;

    @TableField("READY_TO_CPS_TIME")
    private LocalDateTime readyToCpsTime;

    @TableField("READY_TO_CPS_CMPNY_IBUSDATE")
    private LocalDateTime readyToCpsCmpnyIbusdate;

    @TableField("READY_TO_CPS_CMPNY_BUSDATE")
    private LocalDateTime readyToCpsCmpnyBusdate;

    @TableField("SEND_TO_CPS_BY")
    private String sendToCpsBy;

    @TableField("SEND_TO_CPS_TIME")
    private LocalDateTime sendToCpsTime;

    @TableField("SEND_TO_CPS_CMPNY_IBUSDATE")
    private LocalDateTime sendToCpsCmpnyIbusdate;

    @TableField("SEND_TO_CPS_CMPNY_BUSDATE")
    private LocalDateTime sendToCpsCmpnyBusdate;

    @TableField("DOWNLOAD_BY")
    private String downloadBy;

    @TableField("DOWNLOAD_TIME")
    private LocalDateTime downloadTime;

    @TableField("DOWNLOAD_CMPNY_IBUSDATE")
    private LocalDateTime downloadCmpnyIbusdate;

    @TableField("DOWNLOAD_CMPNY_BUSDATE")
    private LocalDateTime downloadCmpnyBusdate;

    @TableField("EXTRNL_MOD_CDE")
    private String extrnlModCde;

    @TableField("EXTRNL_MOD_KEY")
    private String extrnlModKey;

    @TableField("EXTRNL_TXN_REF_NUM")
    private String extrnlTxnRefNum;

    @TableField("IS_INSTR_RELT")
    private String isInstrRelt;

    @TableField("IS_POST")
    private String isPost;

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

    @TableField("EXTRNL_REF_NUM")
    private String extrnlRefNum;

    @TableField("EXTRNL_SYS_CDE")
    private String extrnlSysCde;

    @TableField("FUNTRF_AC_ID")
    private String funtrfAcId;

    @TableField("FUNTRF_SEGR_FUND_ID")
    private Long funtrfSegrFundId;

    @TableField("OTH_CLNT_BANK_CDE")
    private String othClntBankCde;

    @TableField("OTH_CLNT_BANK_AC_NUM")
    private String othClntBankAcNum;

    @TableField("INIT_USER")
    private String initUser;

    @TableField("INIT_USER_UNIT")
    private String initUserUnit;

    @TableField("PAYE_TYP_CDE")
    private String payeTypCde;

    @TableField("FUND_DPST_BY_CDE")
    private String fundDpstByCde;

    @TableField("DPST_NAM")
    private String dpstNam;

    @TableField("IS_REACT")
    private String isReact;

    @TableField("REACT_AC_FUND_RID")
    private Long reactAcFundRid;

    @TableField("REACT_BY")
    private String reactBy;

    @TableField("REACT_CMPNY_IBUSDATE")
    private LocalDateTime reactCmpnyIbusdate;

    @TableField("REACT_CMPNY_BUSDATE")
    private LocalDateTime reactCmpnyBusdate;

    @TableField("REACT_MRKT_IBUSDATE")
    private LocalDateTime reactMrktIbusdate;

    @TableField("REACT_MRKT_BUSDATE")
    private LocalDateTime reactMrktBusdate;

    @TableField("REACT_TIME")
    private LocalDateTime reactTime;

    @TableField("IS_UNDER_REACT")
    private String isUnderReact;

    @TableField("BANK_REPLY_DPST_STAT_CDE")
    private String bankReplyDpstStatCde;

    @TableField("BANK_REPLY_DPST_BY_CDE")
    private String bankReplyDpstByCde;

    @TableField("BANK_REPLY_DPST_NAM")
    private String bankReplyDpstNam;

    @TableField("BANK_REPLY_TIME")
    private LocalDateTime bankReplyTime;

    @TableField("IS_NON_REG_BANK_AC")
    private String isNonRegBankAc;

    @TableField("FUND_TP_SCOPE_TYP_CDE")
    private String fundTpScopeTypCde;

    @TableField("FUND_TP_RELTN_CDE")
    private String fundTpReltnCde;

    @TableField("CHK_BY_2")
    private String chkBy2;
}
