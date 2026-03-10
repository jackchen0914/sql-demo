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
 * @since 2026-03-09
 */
@Getter
@Setter
@ToString
@TableName("DivAnnMapMSSE")
public class DivAnnMapMSSEPO implements Serializable {

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
}
