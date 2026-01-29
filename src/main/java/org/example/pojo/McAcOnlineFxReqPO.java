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
@TableName("MC_AC_ONLINE_FX_REQ")
public class McAcOnlineFxReqPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "AC_ONLINE_FX_REQ_ID", type = IdType.INPUT)
    private Long acOnlineFxReqId;

    @TableField("CMPNY_CDE")
    private String cmpnyCde;

    @TableField("CMPNY_IBUSDATE")
    private LocalDateTime cmpnyIbusdate;

    @TableField("CMPNY_BUSDATE")
    private LocalDateTime cmpnyBusdate;

    @TableField("FROM_AC_ID")
    private String fromAcId;

    @TableField("FROM_SEGR_FUND_ID")
    private Long fromSegrFundId;

    @TableField("FROM_CCY_CDE")
    private String fromCcyCde;

    @TableField("FROM_AMT")
    private BigDecimal fromAmt;

    @TableField("TO_AC_ID")
    private String toAcId;

    @TableField("TO_SEGR_FUND_ID")
    private Long toSegrFundId;

    @TableField("TO_CCY_CDE")
    private String toCcyCde;

    @TableField("TO_AMT")
    private BigDecimal toAmt;

    @TableField("VAL_DATE")
    private LocalDateTime valDate;

    @TableField("EXCH_RATE")
    private BigDecimal exchRate;

    @TableField("CONV_PURP_CDE")
    private String convPurpCde;

    @TableField("APRV_CMPNY_IBUSDATE")
    private LocalDateTime aprvCmpnyIbusdate;

    @TableField("APRV_CMPNY_BUSDATE")
    private LocalDateTime aprvCmpnyBusdate;

    @TableField("APRV_TIME")
    private LocalDateTime aprvTime;

    @TableField("CANCL_CMPNY_IBUSDATE")
    private LocalDateTime canclCmpnyIbusdate;

    @TableField("CANCL_CMPNY_BUSDATE")
    private LocalDateTime canclCmpnyBusdate;

    @TableField("CANCL_TIME")
    private LocalDateTime canclTime;

    @TableField("FX_STAT_CDE")
    private String fxStatCde;

    @TableField("ORDER_KEY")
    private String orderKey;

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
