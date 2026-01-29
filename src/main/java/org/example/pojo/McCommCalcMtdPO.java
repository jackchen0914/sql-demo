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
 * @since 2026-01-27
 */
@Getter
@Setter
@ToString
@TableName("MC_COMM_CALC_MTD")
public class McCommCalcMtdPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "COMM_CALC_MTD_ID", type = IdType.INPUT)
    private Long commCalcMtdId;

    @TableField("CMPNY_CDE")
    private String cmpnyCde;

    @TableField("CALC_MTD_CDE")
    private String calcMtdCde;

    @TableField("COMM_CALC_MTD_DESCR")
    private String commCalcMtdDescr;

    @TableField("COMM_FR_CDE")
    private String commFrCde;

    @TableField("COMM_TYP_CDE")
    private String commTypCde;

    @TableField("COMM_BASE_VAL_CDE")
    private String commBaseValCde;

    @TableField("COMM_CALC_TYP_CDE")
    private String commCalcTypCde;

    @TableField("COMM_HLDP_AMT")
    private BigDecimal commHldpAmt;

    @TableField("COMM_HLDP_RATE")
    private BigDecimal commHldpRate;

    @TableField("PAY_CCY_CDE")
    private String payCcyCde;

    @TableField("IS_OBSOL")
    private String isObsol;

    @TableField("TIER_COMM_BASE_VAL_CDE")
    private String tierCommBaseValCde;

    @TableField("MIN_COMM_AMT")
    private BigDecimal minCommAmt;

    @TableField("MAX_COMM_AMT")
    private BigDecimal maxCommAmt;

    @TableField("FIX_RATE")
    private BigDecimal fixRate;

    @TableField("FIX_AMT")
    private BigDecimal fixAmt;

    @TableField("ADDTN_FIX_AMT")
    private BigDecimal addtnFixAmt;

    @TableField("IS_ADVANCE_SETG")
    private String isAdvanceSetg;

    @TableField("IS_AE_RB_CMPNY_BSLN_PRTCT")
    private String isAeRbCmpnyBslnPrtct;

    @TableField("VER_STAT")
    private String verStat;

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
