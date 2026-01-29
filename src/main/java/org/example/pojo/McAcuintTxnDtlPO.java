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
@TableName("MC_ACUINT_TXN_DTL")
public class McAcuintTxnDtlPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ACUINT_TXN_DTL_ID", type = IdType.INPUT)
    private Long acuintTxnDtlId;

    @TableField("ACUINT_TXN_ID")
    private Long acuintTxnId;

    @TableField("TXN_DATE")
    private LocalDateTime txnDate;

    @TableField("CR_ACUINT")
    private BigDecimal crAcuint;

    @TableField("DR_ACUINT")
    private BigDecimal drAcuint;

    @TableField("LEDG_BAL")
    private BigDecimal ledgBal;

    @TableField("CAT1_DSCNT_VAL")
    private BigDecimal cat1DscntVal;

    @TableField("CAT2_DSCNT_VAL")
    private BigDecimal cat2DscntVal;

    @TableField("CAT3_DSCNT_VAL")
    private BigDecimal cat3DscntVal;

    @TableField("MRGN_AGE")
    private BigDecimal mrgnAge;

    @TableField("CR_INTSCH_VID")
    private Long crIntschVid;

    @TableField("DR_INTSCH_VID")
    private Long drIntschVid;

    @TableField("WAIVE_CR_ACUINT")
    private BigDecimal waiveCrAcuint;

    @TableField("WAIVE_DR_ACUINT")
    private BigDecimal waiveDrAcuint;

    @TableField("INTWV_MTD_CDE")
    private String intwvMtdCde;

    @TableField("ACUINT_TXN_DTL_STAT_CDE")
    private String acuintTxnDtlStatCde;

    @TableField("ACUINT_TXN_TYP_CDE")
    private String acuintTxnTypCde;

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

    @TableField("NORMAL_OD_BAL")
    private BigDecimal normalOdBal;

    @TableField("PENAL_ACUINT")
    private BigDecimal penalAcuint;

    @TableField("WAIVE_PENAL_ACUINT")
    private BigDecimal waivePenalAcuint;

    @TableField("INTSCH_ASGN_RULE_CDE")
    private String intschAsgnRuleCde;

    @TableField("TTL_SEGR_FUND_DV")
    private BigDecimal ttlSegrFundDv;

    @TableField("TTL_SAME_CCY_DV")
    private BigDecimal ttlSameCcyDv;

    @TableField("ALOC_SAME_CCY_SEQ_NUM")
    private Long alocSameCcySeqNum;

    @TableField("ALOC_SAME_CCY_DV")
    private BigDecimal alocSameCcyDv;

    @TableField("UNCO_OD_BY_SAME_CCY")
    private BigDecimal uncoOdBySameCcy;

    @TableField("REMN_DV")
    private BigDecimal remnDv;

    @TableField("EXCH_RATE_TO_BASE_CCY")
    private BigDecimal exchRateToBaseCcy;

    @TableField("BASE_CCY_REMN_DV")
    private BigDecimal baseCcyRemnDv;

    @TableField("BASE_CCY_TTL_CRS_CCY_DV")
    private BigDecimal baseCcyTtlCrsCcyDv;

    @TableField("ALOC_CRS_CCY_SEQ_NUM")
    private Long alocCrsCcySeqNum;

    @TableField("BASE_CCY_ALOC_CRS_CCY_DV")
    private BigDecimal baseCcyAlocCrsCcyDv;

    @TableField("EXCH_RATE_FROM_BASE_CCY")
    private BigDecimal exchRateFromBaseCcy;

    @TableField("ALOC_CRS_CCY_DV")
    private BigDecimal alocCrsCcyDv;
}
