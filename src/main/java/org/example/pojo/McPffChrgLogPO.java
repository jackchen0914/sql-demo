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
 * @since 2025-12-29
 */
@Getter
@Setter
@ToString
@TableName("MC_PFF_CHRG_LOG")
public class McPffChrgLogPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "PFF_CHRG_LOG_ID", type = IdType.INPUT)
    private Long pffChrgLogId;

    @TableField("TXN_TYP_ID")
    private Long txnTypId;

    @TableField("SPCAL_CHRG_GRP_CDE")
    private String spcalChrgGrpCde;

    @TableField("YEAR_MTH")
    private LocalDateTime yearMth;

    @TableField("FROM_DATE")
    private LocalDateTime fromDate;

    @TableField("TO_DATE")
    private LocalDateTime toDate;

    @TableField("CMPNY_CDE")
    private String cmpnyCde;

    @TableField("MRKT_GRP_ID")
    private Long mrktGrpId;

    @TableField("MRKT_ID")
    private Long mrktId;

    @TableField("AC_ID")
    private String acId;

    @TableField("CHRG_CCY_CDE")
    private String chrgCcyCde;

    @TableField("ACRU_CHRG_AMT")
    private BigDecimal acruChrgAmt;

    @TableField("ACRU_CHRWV_DR_AMT")
    private BigDecimal acruChrwvDrAmt;

    @TableField("ACRU_CHRWV_WITHOUT_POST_AMT")
    private BigDecimal acruChrwvWithoutPostAmt;

    @TableField("IS_SHOW_STMT")
    private String isShowStmt;

    @TableField("IS_POST_LEDG")
    private String isPostLedg;

    @TableField("POST_LEDG_DATE")
    private LocalDateTime postLedgDate;

    @TableField("AC_FUND_TXN_RID")
    private Long acFundTxnRid;

    @TableField("REV_AC_FUND_TXN_RID")
    private Long revAcFundTxnRid;

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
