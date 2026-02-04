package org.example.pojo.dtos;

import lombok.Data;
import org.example.pojo.McAcFundHoldRecPO;
import org.example.pojo.McAcFundHoldTxnPO;
import org.example.pojo.McAcHoldStkTxnDtlPO;
import org.example.pojo.McAcHoldStkTxnPO;

@Data
public class HoldInstrumentResultDTO {
    private McAcHoldStkTxnDtlPO mainRecord;
    private McAcHoldStkTxnPO detailRecord;
}
