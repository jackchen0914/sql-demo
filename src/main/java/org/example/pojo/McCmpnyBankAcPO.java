package org.example.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;
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
@TableName("MC_CMPNY_BANK_AC")
//@KeySequence(value = "SEQ_MC_CMPNY_BANK_AC",dbType = DbType.ORACLE)
public class McCmpnyBankAcPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "CMPNY_BANK_AC_ID", type = IdType.INPUT)
//    @TableId(value = "CMPNY_BANK_AC_ID")
    @TableField(value = "CMPNY_BANK_AC_ID",jdbcType = JdbcType.BIGINT)
    private Long cmpnyBankAcId;

    @TableField(value = "CMPNY_CDE",jdbcType = JdbcType.VARCHAR)
    private String cmpnyCde;

    @TableField(value = "CMPNY_BANK_CDE",jdbcType = JdbcType.VARCHAR)
    private String cmpnyBankCde;

    @TableField(value = "CMPNY_BANK_AC_CDE",jdbcType = JdbcType.VARCHAR)
    private String cmpnyBankAcCde;

    @TableField(value = "CMPNY_BANK_AC_NUM",jdbcType = JdbcType.VARCHAR)
    private String cmpnyBankAcNum;

    @TableField(value = "CLNT_LEDG_AC_CDE",jdbcType = JdbcType.VARCHAR)
    private String clntLedgAcCde;

    @TableField(value = "BRKR_LEDG_AC_CDE",jdbcType = JdbcType.VARCHAR)
    private String brkrLedgAcCde;

    @TableField(value = "IS_INACT",jdbcType = JdbcType.VARCHAR)
    private String isInact;

    @TableField(value = "IS_IPO",jdbcType = JdbcType.VARCHAR)
    private String isIpo;

    @TableField(value ="IS_INHOUSE",jdbcType = JdbcType.VARCHAR)
    private String isInhouse;

    @TableField(value ="CMPNY_BANK_AC_DESCR",jdbcType = JdbcType.VARCHAR)
    private String cmpnyBankAcDescr;

    @TableField(value ="OVERDRAFT_AMT_DESCR",jdbcType = JdbcType.VARCHAR)
    private String overdraftAmtDescr;

    @TableField(value ="REC_VER_NUM",jdbcType = JdbcType.BIGINT)
    private Long recVerNum;

    @TableField(value = "INIT_TIME",jdbcType = JdbcType.TIMESTAMP)
    private LocalDateTime initTime;

    @TableField(value = "LAST_UPD_TIME",jdbcType = JdbcType.TIMESTAMP)
    private LocalDateTime lastUpdTime;

    @TableField(value = "LAST_UPD_BY",jdbcType = JdbcType.VARCHAR)
    private String lastUpdBy;

    @TableField(value="TAG_SEQ",jdbcType = JdbcType.BIGINT)
    private Long tagSeq;

    @TableField(value="ERP_GL_CDE",jdbcType = JdbcType.VARCHAR)
    private String erpGlCde;

    @TableField(value = "CMPNY_BANK_AC_TYP_CDE",jdbcType = JdbcType.VARCHAR)
    private String cmpnyBankAcTypCde;
}
