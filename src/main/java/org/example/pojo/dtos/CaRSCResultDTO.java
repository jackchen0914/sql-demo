package org.example.pojo.dtos;

import lombok.Data;
import org.example.pojo.*;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CaRSCResultDTO {
    private McConvEvntPO mainRecord;
    private McCeventPO detailRecord;
    private String tableFlag;

    private String actionTypeList;
    private String announcementNo;
    private String market;
    private String instrument;
    private String ccy;
    private BigDecimal rightsIssue;
    private BigDecimal rightsFor;
    private String rightsInstrument;
    private BigDecimal splitFrom;
    private BigDecimal splitTo;
    private String splitInstrument;
    private BigDecimal combineFrom;
    private BigDecimal combineTo;
    private String combineInstrument;

}
