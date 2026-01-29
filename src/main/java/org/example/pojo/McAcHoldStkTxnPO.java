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
 * @since 2025-12-24
 */
@Getter
@Setter
@ToString
@TableName("MC_AC_HOLD_STK_TXN")
public class McAcHoldStkTxnPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "AC_HOLD_STK_TXN_ID", type = IdType.INPUT)
    private Long acHoldStkTxnId;

    @TableField("IS_RELSE")
    private String isRelse;

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
