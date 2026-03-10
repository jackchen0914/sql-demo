package org.example.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author JackChen
 * @since 2026-03-09
 */
@Getter
@Setter
@ToString
@TableName("MC_CEVENT_OPTN")
public class McCeventOptnPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "CEVENT_OPTN_ID", type = IdType.INPUT)
    private Long ceventOptnId;

    @TableField("CEVENT_ID")
    private Long ceventId;

    @TableField("OPTN_NUM")
    private Integer optnNum;

    @TableField("CEVENT_PAID_STAT_CDE")
    private String ceventPaidStatCde;

    @TableField("EXPT_PAY_DATE")
    private LocalDateTime exptPayDate;

    @TableField("ACT_PAID_DATE")
    private LocalDateTime actPaidDate;

    @TableField("EX_SUB_PAID_STAT_CDE")
    private String exSubPaidStatCde;

    @TableField("EX_SUB_EXPT_PAY_DATE")
    private LocalDateTime exSubExptPayDate;

    @TableField("EX_SUB_ACT_PAID_DATE")
    private LocalDateTime exSubActPaidDate;

    @TableField("IS_DISABLED")
    private String isDisabled;

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
