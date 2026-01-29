package org.example.pojo.dtos;

import lombok.Data;
import org.example.pojo.McCeventPO;
import org.example.pojo.McConvEvntPO;
import org.example.pojo.McPffChrgLogDtlPO;
import org.example.pojo.McPffChrgLogPO;

import java.util.List;

@Data
public class CaRSCResultDTO {
    private McConvEvntPO mainRecord;
    private List<McCeventPO> detailRecord;
}
