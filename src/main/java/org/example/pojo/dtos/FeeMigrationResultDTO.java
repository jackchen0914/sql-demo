package org.example.pojo.dtos;

import lombok.Data;
import org.example.pojo.McPffChrgLogDtlPO;
import org.example.pojo.McPffChrgLogPO;

import java.util.List;

@Data
public class FeeMigrationResultDTO {
//    private List<McPffChrgLogPO> mainList;
    private McPffChrgLogPO mainRecord;
    private List<McPffChrgLogDtlPO> detailRecord;
}
