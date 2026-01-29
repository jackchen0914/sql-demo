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
@TableName("MC_CEVENT")
public class McCeventPO implements Serializable {

    private static final long serialVersionUID = 1L;

//    @TableId(value = "CEVENT_ID", type = IdType.INPUT)
    private Long ceventId;

    @TableField("CEVENT_TYP_GRP_CDE")
    private String ceventTypGrpCde;

    @TableField("CEVENT_TYP_CDE")
    private String ceventTypCde;

    @TableField("EVNT_REF_NUM")
    private String evntRefNum;

    @TableField("EVNT_NAM")
    private String evntNam;

    @TableField("CEVENT_STAT_CDE")
    private String ceventStatCde;

    @TableField("MRKT_ID")
    private Long mrktId;

    @TableField("INSTR_ID")
    private Long instrId;

    @TableField("CCASS_ANNOUNCEMENT_NUM")
    private String ccassAnnouncementNum;

    @TableField("SHOLD_DATE")
    private LocalDateTime sholdDate;

    @TableField("CUTOFF_DATE")
    private LocalDateTime cutoffDate;

    @TableField("AUTH_DATE")
    private LocalDateTime authDate;

    @TableField("REPLY_DEADLINE_DATE")
    private LocalDateTime replyDeadlineDate;

    @TableField("NOMINEE_LETR_IND")
    private String nomineeLetrInd;

    @TableField("IS_ALERT_AFTR_ACTN")
    private String isAlertAftrActn;

    @TableField("IS_ALERT_AFTR_DEADLINE")
    private String isAlertAftrDeadline;

    @TableField("REMRK")
    private String remrk;

    @TableField("LETR_REMRK")
    private String letrRemrk;

    @TableField("IS_SHOLD_CAPTURED")
    private String isSholdCaptured;

    @TableField("IS_NOTF_SENT")
    private String isNotfSent;

    @TableField("IS_PEND_ACTIVATE")
    private String isPendActivate;

    @TableField("IS_PROCESSED")
    private String isProcessed;

    @TableField("CMPLT_DATE")
    private LocalDateTime cmpltDate;

    @TableField("ACTIVATION_DATE")
    private LocalDateTime activationDate;

    @TableField("ACT_CUTOFF_DATE")
    private LocalDateTime actCutoffDate;

    @TableField("REV_CUTOFF_DATE")
    private LocalDateTime revCutoffDate;

    @TableField("REV_AUTH_DATE")
    private LocalDateTime revAuthDate;

    @TableField("REV_CMPLT_DATE")
    private LocalDateTime revCmpltDate;

    @TableField("CANL_DATE")
    private LocalDateTime canlDate;

    @TableField("CUTOFF_REV_CNT")
    private Integer cutoffRevCnt;

    @TableField("AUTH_REV_CNT")
    private Integer authRevCnt;

    @TableField("EXCH_RATE_UPD_CNT")
    private Integer exchRateUpdCnt;

    @TableField("CMPNY_CDE")
    private String cmpnyCde;

    @TableField("EXCESS_ALOT_CNT")
    private Integer excessAlotCnt;

    @TableField("RCALC_CHRG_CNT")
    private Integer rcalcChrgCnt;

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

    @TableField("IS_PRCES")
    private String isPrces;

    @TableField("IS_SHOLD_AMENDED")
    private String isSholdAmended;

    @TableField("IS_ENOTF")
    private String isEnotf;

    @TableField("CEVENT_CREAT_TYP_CDE")
    private String ceventCreatTypCde;

    @TableField("EXTERNAL_CREAT_ID")
    private String externalCreatId;

    @TableField("TREAT_GRP_CDE")
    private String treatGrpCde;

    @TableField("DEF_TAX_RATE")
    private BigDecimal defTaxRate;

    @TableField("IS_OTCR_TXN")
    private String isOtcrTxn;

    @TableField("IS_STAMP_DUTY_CHRG")
    private String isStampDutyChrg;

    @TableField("OTCR_RPT_ON_STG_CDE")
    private String otcrRptOnStgCde;
}
