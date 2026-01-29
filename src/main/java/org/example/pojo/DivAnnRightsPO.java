package org.example.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2026-01-07
 */
@Getter
@Setter
@ToString
@TableName("DivAnnRights")
public class DivAnnRightsPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("AnnouncementNo")
    private String announcementNo;

    @TableField("RightsIssue")
    private Integer rightsIssue;

    @TableField("RightsFor")
    private Integer rightsFor;

    @TableField("RightsInstrument")
    private String rightsInstrument;

    @TableField("Status")
    private String status;

    @TableField("timestamp")
    private byte[] timestamp;
}
