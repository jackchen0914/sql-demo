package org.example.pojo;

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
 * @since 2026-01-07
 */
@Getter
@Setter
@ToString
@TableName("DivAnn")
public class DivAnnPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("AnnouncementNo")
    private String announcementNo;

    @TableField("DeclarationDate")
    private LocalDateTime declarationDate;

    @TableField("ExDividendDate")
    private LocalDateTime exDividendDate;

    @TableField("BookClosedDate")
    private LocalDateTime bookClosedDate;

    @TableField("PayableDate")
    private LocalDateTime payableDate;

    @TableField("Market")
    private String market;

    @TableField("CCY")
    private String ccy;

    @TableField("FeeCCY")
    private String feeCCY;

    @TableField("Instrument")
    private String instrument;

    @TableField("AnnouncementSummary")
    private String announcementSummary;

    @TableField("WaiveScripFee")
    private String waiveScripFee;

    @TableField("ActionTypeList")
    private String actionTypeList;

    @TableField("Status")
    private String status;

    @TableField("ReferenceNo")
    private String referenceNo;

    @TableField("timestamp")
    private byte[] timestamp;

    @TableField("Location")
    private String location;

    @TableField("IncomeCode")
    private String incomeCode;

    @TableField("TaxType")
    private String taxType;

    @TableField("NetOfNegNetAmt")
    private String netOfNegNetAmt;
}
