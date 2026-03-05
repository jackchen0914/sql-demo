package org.example.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2026-03-03
 */
@Getter
@Setter
@ToString
@TableName("DivAnnSplitMapMSSE")
public class DivAnnSplitMapMSSEPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("AnnouncementNo")
    private String announcementNo;

    @TableField("Market")
    private String market;

    @TableField("CCY")
    private String ccy;

    @TableField("Instrument")
    private String instrument;

    @TableField("ReferenceNo")
    private String referenceNo;

    @TableField("ActionTypeList")
    private String actionTypeList;

    @TableField("SplitFrom")
    private BigDecimal splitFrom;

    @TableField("SplitTo")
    private BigDecimal splitTo;

    @TableField("SplitInstrument")
    private String splitInstrument;

    @TableField("CEVENT_ID")
    private Long ceventId;

    @TableField("MRKT_ID")
    private Long mrktId;

    @TableField("INSTR_ID")
    private Long instrId;

    @TableField("CCASS_ANNOUNCEMENT_NUM")
    private String ccassAnnouncementNum;

    @TableField("CEVENT_TYP_GRP_CDE")
    private String ceventTypGrpCde;

    @TableField("SHAR_RATIO_INIT")
    private BigDecimal sharRatioInit;

    @TableField("SHAR_RATIO_TO")
    private BigDecimal sharRatioTo;

    @TableField("NEW_INSTR_ID")
    private Long newInstrId;
}
