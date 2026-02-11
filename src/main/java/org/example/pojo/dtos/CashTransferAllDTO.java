package org.example.pojo.dtos;

import lombok.Data;
import org.springframework.cglib.core.Local;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CashTransferAllDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer seqNo;
    private LocalDateTime transferDate;
    private String status;
    private String clntCodeFrom;
    private String clntCodeTo;
    private String acctTypeFrom;
    private String acctTypeTo;
    private String marketFrom;
    private String marketTo;
    private String cCYFrom;
    private String cCYTo;
    private String bankFrom;
    private String bankTo;
    private BigDecimal amountFrom;
    private BigDecimal amountTo;
    private BigDecimal fXRate;
    private String remarksFrom;
    private String remarksTo;
    private String userIDSave;
    private String userIDCancel;
    private BigDecimal buyInXRate;
    private BigDecimal sellOutXRate;
    private LocalDateTime cancelDateFrom;
    private LocalDateTime cancelDateTo;
    private String approverFrom;
    private String approverTo;
    private LocalDateTime approvalTimeFrom;
    private LocalDateTime approvalTimeTo;

    private BigDecimal baseCcyEquAmtValue;
    private BigDecimal baseCcyEquToAmtValue;
}
