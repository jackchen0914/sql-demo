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
@TableName("MC_INSTR")
public class McInstrPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "INSTR_ID", type = IdType.INPUT)
    private Long instrId;

    @TableField("PRIMY_INSTR_NAM")
    private String primyInstrNam;

    @TableField("INSTCL_ID")
    private Long instclId;

    @TableField("LAST_TRAD_DATE")
    private LocalDateTime lastTradDate;

    @TableField("STRK_PRICE")
    private BigDecimal strkPrice;

    @TableField("EXPR_DATE")
    private LocalDateTime exprDate;

    @TableField("BASE_CCY_CDE")
    private String baseCcyCde;

    @TableField("CNTR_CCY_CDE")
    private String cntrCcyCde;

    @TableField("FINAL_SETL_DATE")
    private LocalDateTime finalSetlDate;

    @TableField("FIRST_NOTICE_DATE")
    private LocalDateTime firstNoticeDate;

    @TableField("UPPER_LVL_INSTR_ID")
    private Long upperLvlInstrId;

    @TableField("ISIN_CDE")
    private String isinCde;

    @TableField("INSTR_CDE")
    private String instrCde;

    @TableField("INSTR_DSPLY_CDE")
    private String instrDsplyCde;

    @TableField("LST_DATE")
    private LocalDateTime lstDate;

    @TableField("CNTRCT_MONTH")
    private LocalDateTime cntrctMonth;

    @TableField("EXCH_RATE_TYP_CDE")
    private String exchRateTypCde;

    @TableField("INSTR_STAT")
    private String instrStat;

    @TableField("CNTRCT_MULTP")
    private BigDecimal cntrctMultp;

    @TableField("CALL_PRICE")
    private BigDecimal callPrice;

    @TableField("SUSPD_START_DATE")
    private LocalDateTime suspdStartDate;

    @TableField("IS_TMP_INSTR")
    private String isTmpInstr;

    @TableField("PRIMY_INSTR_ID")
    private Long primyInstrId;

    @TableField("REMRK")
    private String remrk;

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

    @TableField("IS_SCND_INSTR")
    private String isScndInstr;

    @TableField("UPPER_STRK_PRICE")
    private BigDecimal upperStrkPrice;

    @TableField("INSTR_GID")
    private Long instrGid;

    @TableField("CNTRCT_TYP")
    private String cntrctTyp;

    @TableField("CNTR_TYP")
    private String cntrTyp;
}
