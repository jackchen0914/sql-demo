package org.example.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

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
@TableName("ClntPriceCap")
public class ClntPriceCapPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ClntCode")
    private String clntCode;

    @TableField("Market")
//    @TableId("Market")
    private String market;

    @TableField("Instrument")
//    @TableId("Instrument")
    private String instrument;

    @TableField("MarginPercent")
    private BigDecimal marginPercent;

    @TableField("PriceCap")
    private BigDecimal priceCap;

    @TableField("Description")
    private String description;

    @TableField("SyncRef")
    private String syncRef;

    @TableField("SyncRefCMS")
    private String syncRefCMS;

    @TableField("timestamp")
    private byte[] timestamp;

    @TableField("TTLRef")
    private String tTLRef;

    @TableField("StockLimit")
    private BigDecimal stockLimit;
}
