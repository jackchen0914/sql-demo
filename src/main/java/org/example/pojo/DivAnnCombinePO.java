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
 * @since 2026-01-07
 */
@Getter
@Setter
@ToString
@TableName("DivAnnCombine")
public class DivAnnCombinePO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("AnnouncementNo")
    private String announcementNo;

    @TableField("CombineFrom")
    private Integer combineFrom;

    @TableField("CombineTo")
    private Integer combineTo;

    @TableField("CombineInstrument")
    private String combineInstrument;

    @TableField("Status")
    private String status;

    @TableField("CombineClosingPrice")
    private BigDecimal combineClosingPrice;

    @TableField("timestamp")
    private byte[] timestamp;
}
