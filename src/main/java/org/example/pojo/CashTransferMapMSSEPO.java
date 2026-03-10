package org.example.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author JackChen
 * @since 2026-03-03
 */
@Getter
@Setter
@ToString
@TableName("CashTransferMapMSSE")
public class CashTransferMapMSSEPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("SeqNo")
    private Integer seqNo;

    @TableField("AC_ONLINE_FX_REQ_ID")
    private Long acOnlineFxReqId;

    @TableField("AC_FUNTRF_CCYEX_RID")
    private Long acFuntrfCcyexRid;

    @TableField("AC_FUNTRF_CCYEX_REQ_ID")
    private Long acFuntrfCcyexReqId;

    @TableField("AC_FUND_GEN_STMT_REMRK_RID")
    private Long acFundGenStmtRemrkRid;
}
