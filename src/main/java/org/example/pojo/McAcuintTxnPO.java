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
 * @since 2025-12-25
 */
@Getter
@Setter
@ToString
@TableName("MC_ACUINT_TXN")
public class McAcuintTxnPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ACUINT_TXN_ID", type = IdType.INPUT)
    private Long acuintTxnId;

    @TableField("AC_ID")
    private String acId;

    @TableField("CMPNY_CDE")
    private String cmpnyCde;

    @TableField("CCY_CDE")
    private String ccyCde;

    @TableField("SEGR_FUND_ID")
    private Long segrFundId;

    @TableField("YEAR_MTH")
    private LocalDateTime yearMth;

    @TableField("FROM_DATE")
    private LocalDateTime fromDate;

    @TableField("TO_DATE")
    private LocalDateTime toDate;

    @TableField("POST_DATE")
    private LocalDateTime postDate;

    @TableField("CR_ACUINT")
    private BigDecimal crAcuint;

    @TableField("DR_ACUINT")
    private BigDecimal drAcuint;

    @TableField("ADJ_CR_ACUINT")
    private BigDecimal adjCrAcuint;

    @TableField("ADJ_DR_ACUINT")
    private BigDecimal adjDrAcuint;

    @TableField("WAIVE_CR_ACUINT")
    private BigDecimal waiveCrAcuint;

    @TableField("WAIVE_DR_ACUINT")
    private BigDecimal waiveDrAcuint;

    @TableField("IS_WAIVE_NEED_POST")
    private String isWaiveNeedPost;

    @TableField("LAST_CR_INTSCH_VID")
    private Long lastCrIntschVid;

    @TableField("LAST_DR_INTSCH_VID")
    private Long lastDrIntschVid;

    @TableField("FUND_TXN_ID")
    private Long fundTxnId;

    @TableField("REV_FUND_TXN_ID")
    private Long revFundTxnId;

    @TableField("ACUINT_TXN_STAT_CDE")
    private String acuintTxnStatCde;

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

    @TableField("INTCY_TYP_CDE")
    private String intcyTypCde;

    @TableField("INTCY_FROM_DATE")
    private LocalDateTime intcyFromDate;

    @TableField("INTCY_TO_DATE")
    private LocalDateTime intcyToDate;

    @TableField("REMRK")
    private String remrk;

    @TableField("IS_ON_HOLD_POST")
    private String isOnHoldPost;

    @TableField("ADJ_DR_ACUINT_EXCL_PENAL")
    private BigDecimal adjDrAcuintExclPenal;

    @TableField("PENAL_ACUINT")
    private BigDecimal penalAcuint;

    @TableField("ADJ_PENAL_ACUINT")
    private BigDecimal adjPenalAcuint;

    @TableField("WAIVE_PENAL_ACUINT")
    private BigDecimal waivePenalAcuint;

    @TableField("ROVER_PENAL_ACUINT")
    private BigDecimal roverPenalAcuint;
}
