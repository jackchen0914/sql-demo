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
 * @since 2025-12-12
 */
@Getter
@Setter
@ToString
@TableName("MC_FUND_TP_RELTN")
public class McFundTpReltnPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "FUND_TP_RELTN_CDE", type = IdType.INPUT)
    private String fundTpReltnCde;

    @TableField("FUND_TP_RELTN_DSCR")
    private String fundTpReltnDscr;

    @TableField("FUND_TP_RELTN_PRI")
    private Long fundTpReltnPri;

    @TableField("IS_INACT")
    private String isInact;

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
