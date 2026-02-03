package org.example.pojo.dtos;

import lombok.Data;
import org.example.pojo.McAcFundRecPO;
import org.example.pojo.McAcFundTxnRecPO;
import org.example.pojo.McFundTpReltnPO;

@Data
public class CashVoucherResultDTO {
    private McAcFundRecPO mcAcFundRecRecord;
    private McAcFundTxnRecPO mcAcFundTxnRecRecord;
    private McFundTpReltnPO mcFundTpReltnPRecord;
}
