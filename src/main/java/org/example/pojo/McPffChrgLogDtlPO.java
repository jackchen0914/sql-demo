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
@TableName("MC_PFF_CHRG_LOG_DTL")
public class McPffChrgLogDtlPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "PFF_CHRG_LOG_DTL_ID", type = IdType.INPUT)
    private Long pffChrgLogDtlId;

    @TableField("PFF_CHRG_LOG_ID")
    private Long pffChrgLogId;

    @TableField("TXN_DATE")
    private LocalDateTime txnDate;

    @TableField("CHRG_AMT")
    private BigDecimal chrgAmt;

    @TableField("CHRWV_AMT")
    private BigDecimal chrwvAmt;

    @TableField("CHRWV_MTD_CDE")
    private String chrwvMtdCde;

    @TableField("IS_WAIVE")
    private String isWaive;

    @TableField("PROFO_VAL")
    private BigDecimal profoVal;

    @TableField("CHRG_GRP_VID")
    private Long chrgGrpVid;

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
