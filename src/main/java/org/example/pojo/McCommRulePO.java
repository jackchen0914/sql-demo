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
 * @since 2026-01-27
 */
@Getter
@Setter
@ToString
@TableName("MC_COMM_RULE")
public class McCommRulePO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "COMM_RULE_ID", type = IdType.INPUT)
    private Long commRuleId;

    @TableField("COMM_RULE_CDE")
    private String commRuleCde;

    @TableField("COMM_RULE_DESCR")
    private String commRuleDescr;

    @TableField("COMM_FR_CDE")
    private String commFrCde;

    @TableField("COMM_TYP_CDE")
    private String commTypCde;

    @TableField("EXCH_GRP_ID")
    private Long exchGrpId;

    @TableField("MRKT_ID")
    private Long mrktId;

    @TableField("MRKT_GRP_ID")
    private Long mrktGrpId;

    @TableField("COMM_INSTR_TYP_GRP_CDE")
    private String commInstrTypGrpCde;

    @TableField("SUBMRKT_ID")
    private Long submrktId;

    @TableField("INSTCL_ID")
    private Long instclId;

    @TableField("TRAD_CCY_CDE")
    private String tradCcyCde;

    @TableField("COMM_CHNL_GRP_ID")
    private Long commChnlGrpId;

    @TableField("CLNT_CLASS_CDE")
    private String clntClassCde;

    @TableField("COMM_CALC_MTD_ID")
    private Long commCalcMtdId;

    @TableField("IS_OBSOL")
    private String isObsol;

    @TableField("CMPNY_CDE")
    private String cmpnyCde;

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
