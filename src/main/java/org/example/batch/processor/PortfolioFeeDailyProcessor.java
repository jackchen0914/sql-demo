package org.example.batch.processor;

import lombok.RequiredArgsConstructor;
import org.example.mapper.McMrktMapper;
import org.example.pojo.McMrktPO;
import org.example.pojo.McPffChrgLogDtlPO;
import org.example.pojo.McPffChrgLogPO;
import org.example.pojo.PortfoliofeeDailyPO;
import org.example.pojo.dtos.FeeMigrationResultDTO;
import org.example.service.IdGeneratorService;
import org.example.utils.PropertyConverUtils;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class PortfolioFeeDailyProcessor implements ItemProcessor<PortfoliofeeDailyPO, FeeMigrationResultDTO> {

    private final McMrktMapper mcMrktMapper;
    private final IdGeneratorService idGeneratorService;

    @Override
    public FeeMigrationResultDTO process(PortfoliofeeDailyPO portfoliofeeDailyPO) throws Exception {
        if(portfoliofeeDailyPO == null){
            return null;
        }

        FeeMigrationResultDTO result = new FeeMigrationResultDTO();

        Long mainId = idGeneratorService.generateMainId();

        List<McPffChrgLogDtlPO> mcPffChrgLogDtlPOList = new ArrayList<>();

        McPffChrgLogDtlPO mcPffChrgLogDtlPO = new McPffChrgLogDtlPO();
        McPffChrgLogPO mcPffChrgLogPO = new McPffChrgLogPO();
//        mcPffChrgLogDtlPO.setPffChrgLogDtlId((long) (1+i));
//        mcPffChrgLogDtlPO.setPffChrgLogId((long) (1+i));
        mcPffChrgLogDtlPO.setPffChrgLogDtlId(idGeneratorService.generateDetailId());
        mcPffChrgLogDtlPO.setPffChrgLogId(mainId);
        mcPffChrgLogDtlPO.setTxnDate(portfoliofeeDailyPO.getDate());
        mcPffChrgLogDtlPO.setChrgAmt(portfoliofeeDailyPO.getORFee());
        mcPffChrgLogDtlPO.setChrwvAmt(BigDecimal.ZERO);
//            mcPffChrgLogDtlPO.setChrwvMtdCde("0");
        mcPffChrgLogDtlPO.setChrwvMtdCde("WITHOUT_POST");
        mcPffChrgLogDtlPO.setIsWaive("N");
        mcPffChrgLogDtlPO.setProfoVal(portfoliofeeDailyPO.getPortfolioValue());
//            mcPffChrgLogDtlPO.setChrgGrpVid();
        mcPffChrgLogDtlPO.setRecVerNum(0L);
        mcPffChrgLogDtlPO.setInitTime(LocalDateTime.now());
        mcPffChrgLogDtlPO.setLastUpdTime(LocalDateTime.now());
        mcPffChrgLogDtlPO.setLastUpdBy("MIG");
        mcPffChrgLogDtlPO.setTagSeq(0L);

        mcPffChrgLogPO.setPffChrgLogId(mainId);
//        mcPffChrgLogPO.setPffChrgLogId((long) (1+i));
//            mcPffChrgLogPO.setTxnTypId();
        mcPffChrgLogPO.setSpcalChrgGrpCde("PFF");
        mcPffChrgLogPO.setYearMth(portfoliofeeDailyPO.getDate());
        mcPffChrgLogPO.setFromDate(portfoliofeeDailyPO.getDate());
        mcPffChrgLogPO.setToDate(portfoliofeeDailyPO.getDate());
        mcPffChrgLogPO.setCmpnyCde("TFS");
        mcPffChrgLogPO.setMrktId(findMarketId(PropertyConverUtils.marketCodeConver(portfoliofeeDailyPO.getMarket())));
//            mcPffChrgLogPO.setAcId(po.getClntCode());
        mcPffChrgLogPO.setAcId("02-0000389-30");
        mcPffChrgLogPO.setChrgCcyCde(PropertyConverUtils.standardizeCurrencyCode(portfoliofeeDailyPO.getCcy()));
        mcPffChrgLogPO.setAcruChrgAmt(portfoliofeeDailyPO.getORFee());
        mcPffChrgLogPO.setAcruChrwvDrAmt(BigDecimal.ZERO);
        mcPffChrgLogPO.setAcruChrwvWithoutPostAmt(BigDecimal.ZERO);
        mcPffChrgLogPO.setIsShowStmt("Y");
        mcPffChrgLogPO.setIsPostLedg(Objects.equals(portfoliofeeDailyPO.getStatus(), "POST") ? "Y" : "N");
        mcPffChrgLogPO.setPostLedgDate(portfoliofeeDailyPO.getPostDate());
//            mcPffChrgLogPO.setAcFundTxnRid();
        mcPffChrgLogPO.setRecVerNum(0L);
        mcPffChrgLogPO.setInitTime(LocalDateTime.now());
        mcPffChrgLogPO.setLastUpdTime(LocalDateTime.now());
        mcPffChrgLogPO.setLastUpdBy("MIG");
        mcPffChrgLogPO.setTagSeq(0L);

        result.setMainRecord(mcPffChrgLogPO);
        mcPffChrgLogDtlPOList.add(mcPffChrgLogDtlPO);

        result.setDetailRecord(mcPffChrgLogDtlPOList);

        return result;
    }

    private Long findMarketId(String market) {
        McMrktPO instrIdByCode = mcMrktMapper.findInstrIdByCode(market);
        return instrIdByCode.getMrktId();
    }
}
