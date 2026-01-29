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
 * @since 2025-12-22
 */
@Getter
@Setter
@ToString
@TableName("MC_INSTCL_VER")
public class McInstclVerPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "INSTCL_VID", type = IdType.INPUT)
    private Long instclVid;

    @TableField("INSTCL_ID")
    private Long instclId;

    @TableField("PRIMY_INSTCL_NAM")
    private String primyInstclNam;

    @TableField("MRKT_ID")
    private Long mrktId;

    @TableField("INSTGP_ID")
    private Long instgpId;

    @TableField("UNDRLY_ID")
    private Long undrlyId;

    @TableField("INSTR_CATG_ID")
    private Long instrCatgId;

    @TableField("UPPER_LVL_INSTCL_ID")
    private Long upperLvlInstclId;

    @TableField("INSTCL_CDE")
    private String instclCde;

    @TableField("INSTCL_DSPLY_CDE")
    private String instclDsplyCde;

    @TableField("LOT_SIZ")
    private BigDecimal lotSiz;

    @TableField("PIP_SIZ")
    private BigDecimal pipSiz;

    @TableField("QUANTUM_UOM")
    private String quantumUom;

    @TableField("CNTRCT_MULTP")
    private BigDecimal cntrctMultp;

    @TableField("CCY_CDE")
    private String ccyCde;

    @TableField("SETL_CCY_CDE")
    private String setlCcyCde;

    @TableField("INSTSYM_RULE_ID")
    private Long instsymRuleId;

    @TableField("STRK_PRICE_STUCT_ID")
    private Long strkPriceStuctId;

    @TableField("PRICE_STUCT_ID")
    private Long priceStuctId;

    @TableField("PRICE_INTRV_ID")
    private Long priceIntrvId;

    @TableField("INSTR_BAT_GRP_ID")
    private Long instrBatGrpId;

    @TableField("IS_ALLW_SHORTSELL")
    private String isAllwShortsell;

    @TableField("IS_ALLW_SELL_IN_ADVANCE")
    private String isAllwSellInAdvance;

    @TableField("CALNDR_ID")
    private Long calndrId;

    @TableField("REMRK")
    private String remrk;

    @TableField("PRDINT_DENOMINATOR")
    private BigDecimal prdintDenominator;

    @TableField("PRDINT_RATE_TYP")
    private String prdintRateTyp;

    @TableField("STOCK_OPTN_TIER_CDE")
    private String stockOptnTierCde;

    @TableField("POSITN_LMT")
    private BigDecimal positnLmt;

    @TableField("RPT_LMT")
    private BigDecimal rptLmt;

    @TableField("QTY_STUCT_ID")
    private Long qtyStuctId;

    @TableField("PAR_VAL")
    private BigDecimal parVal;

    @TableField("IS_NO_STAMP")
    private String isNoStamp;

    @TableField("IS_CCASS_SETL")
    private String isCcassSetl;

    @TableField("IS_TMP_INSTCL")
    private String isTmpInstcl;

    @TableField("PRIMY_INSTCL_ID")
    private Long primyInstclId;

    @TableField("SUBMRKT_ID")
    private Long submrktId;

    @TableField("DAY_TO_SETL")
    private BigDecimal dayToSetl;

    @TableField("BUY_SIDE_DAY_TO_SETL")
    private BigDecimal buySideDayToSetl;

    @TableField("SELL_SIDE_DAY_TO_SETL")
    private BigDecimal sellSideDayToSetl;

    @TableField("INSTCL_STAT")
    private String instclStat;

    @TableField("VER_EFF_DATE")
    private LocalDateTime verEffDate;

    @TableField("VER_EXPR_DATE")
    private LocalDateTime verExprDate;

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

    @TableField("IS_SCND_INSTCL")
    private String isScndInstcl;

    @TableField("FUND_DAY_TO_SETL")
    private BigDecimal fundDayToSetl;

    @TableField("CNTR_TYP")
    private String cntrTyp;
}
