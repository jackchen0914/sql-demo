package org.example.pojo.dtos;

import lombok.Data;
import org.example.pojo.McCommCalcMtdPO;
import org.example.pojo.McCommRulePO;

@Data
public class BrokerageWithRageResultDTO {

    private McCommRulePO mcCommRuleRecord;
    private McCommCalcMtdPO mcCommCalcMtdRecord;

}
