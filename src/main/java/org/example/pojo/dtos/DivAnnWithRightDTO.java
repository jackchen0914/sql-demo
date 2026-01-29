package org.example.pojo.dtos;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class DivAnnWithRightDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String actionTypeList;

    private String status;

    private String referenceNo;

    private LocalDateTime payableDate;

    private String market;

    private LocalDateTime exDividendDate;

    private String instrument;

    private String announcementSummary;

    private Integer rightsIssue;

    private Integer rightsFor;

    private String rightsInstrument;

    //-----
    private Integer splitFrom;

    private Integer splitTo;

    private String splitInstrument;

    //-----
    private Integer combineFrom;

    private Integer combineTo;

    private String combineInstrument;
}
