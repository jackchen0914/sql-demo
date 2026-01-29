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
 * @since 2026-01-22
 */
@Getter
@Setter
@ToString
@TableName("MC_RM_AC_INSTR_LNDRAT")
public class McRmAcInstrLndratPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "RM_AC_INSTR_LNDRAT_ID", type = IdType.INPUT)
    private Long rmAcInstrLndratId;

    @TableField("AC_ID")
    private String acId;

    @TableField("INSTR_ID")
    private Long instrId;

    @TableField("IS_ACTV")
    private String isActv;

    @TableField("AC_LNDRAT")
    private BigDecimal acLndrat;

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
}
