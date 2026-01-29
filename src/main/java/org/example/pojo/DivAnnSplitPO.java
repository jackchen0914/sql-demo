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
@TableName("DivAnnSplit")
public class DivAnnSplitPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("AnnouncementNo")
    private String announcementNo;

    @TableField("SplitFrom")
    private Integer splitFrom;

    @TableField("SplitTo")
    private Integer splitTo;

    @TableField("SplitInstrument")
    private String splitInstrument;

    @TableField("Status")
    private String status;

    @TableField("SplitClosingPrice")
    private BigDecimal splitClosingPrice;

    @TableField("timestamp")
    private byte[] timestamp;
}
