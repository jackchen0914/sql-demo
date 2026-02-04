package org.example.pojo.dtos;

import lombok.Data;
import org.example.pojo.McAcFundHoldRecPO;
import org.example.pojo.McAcFundHoldTxnPO;
import org.example.pojo.McCeventPO;
import org.example.pojo.McConvEvntPO;

import java.util.List;

@Data
public class HoldCashResultDTO {
    private McAcFundHoldRecPO mainRecord;
    private McAcFundHoldTxnPO detailRecord;
}
