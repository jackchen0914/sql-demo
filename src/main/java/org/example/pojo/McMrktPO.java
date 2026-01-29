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
@TableName("MC_MRKT")
public class McMrktPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "MRKT_ID", type = IdType.INPUT)
    private Long mrktId;

    @TableField("MRKT_CDE")
    private String mrktCde;

    @TableField("INSTR_BAT_GRP_ID")
    private Long instrBatGrpId;

    @TableField("CALNDR_ID")
    private Long calndrId;

    @TableField("CLOS_PRICE_DEVIATION_THESHOLD")
    private BigDecimal closPriceDeviationTheshold;

    @TableField("PRIMY_MRKT_NAM")
    private String primyMrktNam;

    @TableField("DAY_TO_SETL")
    private BigDecimal dayToSetl;

    @TableField("BUY_SIDE_DAY_TO_SETL")
    private BigDecimal buySideDayToSetl;

    @TableField("SELL_SIDE_DAY_TO_SETL")
    private BigDecimal sellSideDayToSetl;

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

    @TableField("FUND_DAY_TO_SETL")
    private BigDecimal fundDayToSetl;
}
