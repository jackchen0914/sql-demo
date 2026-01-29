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
 * @since 2025-12-23
 */
@Getter
@Setter
@ToString
@TableName("MC_AC_FUND_HOLD_REC")
public class McAcFundHoldRecPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "AC_FUND_HOLD_RID", type = IdType.INPUT)
    private Long acFundHoldRid;

    @TableField("CMPNY_IBUSDATE")
    private LocalDateTime cmpnyIbusdate;

    @TableField("CMPNY_BUSDATE")
    private LocalDateTime cmpnyBusdate;

    @TableField("MRKT_IBUSDATE")
    private LocalDateTime mrktIbusdate;

    @TableField("MRKT_BUSDATE")
    private LocalDateTime mrktBusdate;

    @TableField("AC_ID")
    private String acId;

    @TableField("SEGR_FUND_ID")
    private Long segrFundId;

    @TableField("CCY_CDE")
    private String ccyCde;

    @TableField("CMPNY_CDE")
    private String cmpnyCde;

    @TableField("TXN_TYP_ID")
    private Long txnTypId;

    @TableField("REF_NUM")
    private String refNum;

    @TableField("HOLD_AMT")
    private BigDecimal holdAmt;

    @TableField("EXPR_DATE")
    private LocalDateTime exprDate;

    @TableField("FUND_HOLD_TYP")
    private String fundHoldTyp;

    @TableField("FUND_HOLD_STAT")
    private String fundHoldStat;

    @TableField("FUND_CHNL_CDE")
    private String fundChnlCde;

    @TableField("EXTRNL_REF_NUM")
    private String extrnlRefNum;

    @TableField("REMRK")
    private String remrk;

    @TableField("HOLD_DATE")
    private LocalDateTime holdDate;

    @TableField("HOLD_BY")
    private String holdBy;

    @TableField("RELSE_DATE")
    private LocalDateTime relseDate;

    @TableField("RELSE_BY")
    private String relseBy;

    @TableField("RELSE_AMT")
    private BigDecimal relseAmt;

    @TableField("LAST_RELSE_DATE")
    private LocalDateTime lastRelseDate;

    @TableField("LAST_RELSE_BY")
    private String lastRelseBy;

    @TableField("INSTCL_ID")
    private Long instclId;

    @TableField("MRKT_ID")
    private Long mrktId;

    @TableField("CALNDR_ID")
    private Long calndrId;

    @TableField("INSTR_BAT_GRP_ID")
    private Long instrBatGrpId;

    @TableField("AC_FUND_HOLD_TXN_ID")
    private Long acFundHoldTxnId;

    @TableField("IS_INSTR_RELT")
    private String isInstrRelt;

    @TableField("IS_POST")
    private String isPost;

    @TableField("EXTRNL_SYS_CDE")
    private String extrnlSysCde;

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

    @TableField("INTRNL_REF")
    private String intrnlRef;
}
