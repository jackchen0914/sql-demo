package org.example.pojo.dtos;

import lombok.Data;
import org.example.pojo.McAcFundGenStmtRemrkPO;
import org.example.pojo.McAcFuntrfCcyexRecPO;
import org.example.pojo.McAcFuntrfCcyexReqPO;
import org.example.pojo.McAcOnlineFxReqPO;

@Data
public class CashTransferAllResultDTO {
    private McAcOnlineFxReqPO mainRecord;
    private McAcFuntrfCcyexRecPO mcAcFuntrfCcyexRecRecord;
    private McAcFuntrfCcyexReqPO mcAcFuntrfCcyexReqRecord;
    private McAcFundGenStmtRemrkPO mcAcFundGenStmtRemrkRecord;
}
