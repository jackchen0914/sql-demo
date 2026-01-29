package org.example.pojo.dtos;

import lombok.Data;
import org.example.pojo.*;

@Data
public class InstrumentVoucherResultDTO {
    private McStkTxnPO mcStkTxnRecord;
    private McAcStkTxnDtlPO mcAcStkTxnDtlRecord;
    private McAcStkTxnDtlStatPO mcAcStkTxnDtlStatRecord;
    private McStkMemoTxnPO mcStkMemoTxnRecord;
    private McStkMemoTxnReqPO mcStkMemoTxnReqRecord;
    private McLoctnStkTxnDtlPO mcLoctnStkTxnDtlRecord;
}
