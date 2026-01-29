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
@TableName("MC_STK_MEMO_TXN")
public class McStkMemoTxnPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "STK_MEMO_TXN_ID", type = IdType.INPUT)
    private Long stkMemoTxnId;

    @TableField("CMPNY_CDE")
    private String cmpnyCde;

    @TableField("TXN_TYP_ID")
    private Long txnTypId;

    @TableField("TXN_TYP_ACTN_CDE")
    private String txnTypActnCde;

    @TableField("TXN_REF_NUM")
    private String txnRefNum;

    @TableField("VAL_DATE")
    private LocalDateTime valDate;

    @TableField("REMRK")
    private String remrk;

    @TableField("PRIMY_REMRK_FR_STMT")
    private String primyRemrkFrStmt;

    @TableField("STK_REQ_STAT_CDE")
    private String stkReqStatCde;

    @TableField("STK_CHNL_CDE")
    private String stkChnlCde;

    @TableField("CREAT_BY")
    private String creatBy;

    @TableField("CREAT_MRKT_BUSDATE")
    private LocalDateTime creatMrktBusdate;

    @TableField("CREAT_INPT_DATE")
    private LocalDateTime creatInptDate;

    @TableField("LAST_SUBMITTED_BY")
    private String lastSubmittedBy;

    @TableField("LAST_SUBMITTED_MRKT_BUSDATE")
    private LocalDateTime lastSubmittedMrktBusdate;

    @TableField("LAST_SUBMITTED_INPT_DATE")
    private LocalDateTime lastSubmittedInptDate;

    @TableField("LAST_SUBMITTED_TIME")
    private LocalDateTime lastSubmittedTime;

    @TableField("LAST_RTUN_BY")
    private String lastRtunBy;

    @TableField("LAST_RTUN_MRKT_BUSDATE")
    private LocalDateTime lastRtunMrktBusdate;

    @TableField("LAST_RTUN_INPT_DATE")
    private LocalDateTime lastRtunInptDate;

    @TableField("LAST_RTUN_TIME")
    private LocalDateTime lastRtunTime;

    @TableField("LAST_RTUN_REASN")
    private String lastRtunReasn;

    @TableField("LAST_IN_PROGRESS_BY")
    private String lastInProgressBy;

    @TableField("LAST_IN_PROGRESS_MRKT_BUSDATE")
    private LocalDateTime lastInProgressMrktBusdate;

    @TableField("LAST_IN_PROGRESS_TIME")
    private LocalDateTime lastInProgressTime;

    @TableField("LAST_IN_PROGRESS_INPT_DATE")
    private LocalDateTime lastInProgressInptDate;

    @TableField("LAST_ON_HAND_BY")
    private String lastOnHandBy;

    @TableField("LAST_ON_HAND_MRKT_BUSDATE")
    private LocalDateTime lastOnHandMrktBusdate;

    @TableField("LAST_ON_HAND_INPT_DATE")
    private LocalDateTime lastOnHandInptDate;

    @TableField("LAST_ON_HAND_TIME")
    private LocalDateTime lastOnHandTime;

    @TableField("ACT_ON_HAND_INPT_DATE")
    private LocalDateTime actOnHandInptDate;

    @TableField("APRV_REJECT_DEL_BY")
    private String aprvRejectDelBy;

    @TableField("APRV_REJECT_DEL_MRKT_BUSDATE")
    private LocalDateTime aprvRejectDelMrktBusdate;

    @TableField("APRV_REJECT_DEL_INPT_DATE")
    private LocalDateTime aprvRejectDelInptDate;

    @TableField("APRV_REJECT_DEL_TIME")
    private LocalDateTime aprvRejectDelTime;

    @TableField("REV_BY")
    private String revBy;

    @TableField("REV_MRKT_BUSDATE")
    private LocalDateTime revMrktBusdate;

    @TableField("REV_INPT_DATE")
    private LocalDateTime revInptDate;

    @TableField("REV_TIME")
    private LocalDateTime revTime;

    @TableField("REVW_BY")
    private String revwBy;

    @TableField("REVW_MRKT_BUSDATE")
    private LocalDateTime revwMrktBusdate;

    @TableField("REVW_INPT_DATE")
    private LocalDateTime revwInptDate;

    @TableField("REVW_TIME")
    private LocalDateTime revwTime;

    @TableField("REJ_REASN")
    private String rejReasn;

    @TableField("AC_ID")
    private String acId;

    @TableField("INSTR_ID")
    private Long instrId;

    @TableField("INSTCL_ID")
    private Long instclId;

    @TableField("INSTCL_VID")
    private Long instclVid;

    @TableField("MRKT_ID")
    private Long mrktId;

    @TableField("STK_LOCTN_ID")
    private Long stkLoctnId;

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

    @TableField("EXTRNL_REF_NUM")
    private String extrnlRefNum;

    @TableField("IS_TP_TXN")
    private String isTpTxn;

    @TableField("TAX_CATG_CDE")
    private String taxCatgCde;

    @TableField("IS_OTCR_TXN")
    private String isOtcrTxn;

    @TableField("OTCR_PRICE_CCY_CDE")
    private String otcrPriceCcyCde;

    @TableField("OTCR_PRICE")
    private BigDecimal otcrPrice;

    @TableField("OTCR_STK_TXN_DATE")
    private LocalDateTime otcrStkTxnDate;

    @TableField("IS_REV_TXN")
    private String isRevTxn;

    @TableField("REV_REASN_CDE")
    private String revReasnCde;

    @TableField("ORIG_TXN_REF_NUM")
    private String origTxnRefNum;

    @TableField("ORIG_TXN_TYP_CDE")
    private String origTxnTypCde;
}
