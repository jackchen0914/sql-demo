package org.example.pojo.dtos;

import lombok.Data;
import org.example.pojo.McAcuintTxnDtlPO;
import org.example.pojo.McAcuintTxnPO;

@Data
public class InterestDailyResultDTO {

    private McAcuintTxnDtlPO mainRecord;
    private McAcuintTxnPO detailRecord;

}
