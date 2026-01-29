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
 * @since 2025-12-12
 */
@Getter
@Setter
@ToString
@TableName("MC_AC_FUND_TXN_REC")
public class McAcFundTxnRecPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "AC_FUND_TXN_RID", type = IdType.INPUT)
    private Long acFundTxnRid;

    @TableField("FUND_STAT_CDE")
    private String fundStatCde;

    @TableField("IS_REV")
    private String isRev;

    @TableField("REV_AC_FUND_TXN_RID")
    private Long revAcFundTxnRid;

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
