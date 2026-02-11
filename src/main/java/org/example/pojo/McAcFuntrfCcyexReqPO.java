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
 * @since 2026-02-09
 */
@Getter
@Setter
@ToString
@TableName("MC_AC_FUNTRF_CCYEX_REQ")
public class McAcFuntrfCcyexReqPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "AC_FUNTRF_CCYEX_REQ_ID", type = IdType.INPUT)
    private Long acFuntrfCcyexReqId;

    @TableField("AC_FUNTRF_CCYEX_RID")
    private Long acFuntrfCcyexRid;

    @TableField("AC_FUND_TXN_RID")
    private Long acFundTxnRid;

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
