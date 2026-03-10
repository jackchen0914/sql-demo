package org.example.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.*;
import org.example.pojo.*;
import org.example.service.IdGeneratorService;
import org.example.service.PortfolioFeeDailyToMsseService;
import org.example.utils.PropertyConverUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author JackChen
 * @since 2025-12-29
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PortfolioFeeDailyToMsseServiceImpl implements PortfolioFeeDailyToMsseService {

    private final PortfoliofeeDailyMapper portfoliofeeDailyMapper;
    private final PortfolioFeeDailyMapMSSEMapper portfolioFeeDailyMapMSSEMapper;

    private final McPffChrgLogDtlMapper mcPffChrgLogDtlMapper;
    private final McPffChrgLogMapper mcPffChrgLogMapper;
    private final McMrktMapper mcMrktMapper;

    private final IdGeneratorService idGeneratorService;

    @Override
    public String writeProcessedData() {
        List<PortfoliofeeDailyPO> portfoliofeeDailyPOS = portfoliofeeDailyMapper.selectByPage(1, 10);

        List<McPffChrgLogDtlPO> mcPffChrgLogDtlPOList = new ArrayList<>();
        List<McPffChrgLogPO> mcPffChrgLogPOList = new ArrayList<>();
        List<PortfolioFeeDailyMapMSSEPO> portfolioFeeDailyMapMSSEPOList = new ArrayList<>();
        for (int i = 0; i < portfoliofeeDailyPOS.size(); i++) {
            PortfoliofeeDailyPO items = portfoliofeeDailyPOS.get(i);
            Long mainId = idGeneratorService.generateMainId();
            Long pffChrgLogDtlId = idGeneratorService.generateDetailId();
            McPffChrgLogDtlPO mcPffChrgLogDtlPO = new McPffChrgLogDtlPO();
            McPffChrgLogPO mcPffChrgLogPO = new McPffChrgLogPO();
            PortfolioFeeDailyMapMSSEPO portfolioFeeDailyMapMSSEPO = new PortfolioFeeDailyMapMSSEPO();

//        mcPffChrgLogDtlPO.setPffChrgLogDtlId((long) (1+i));
//        mcPffChrgLogDtlPO.setPffChrgLogId((long) (1+i));
            mcPffChrgLogDtlPO.setPffChrgLogDtlId(pffChrgLogDtlId);
            mcPffChrgLogDtlPO.setPffChrgLogId(mainId);
            mcPffChrgLogDtlPO.setTxnDate(items.getDate());
            mcPffChrgLogDtlPO.setChrgAmt(items.getORFee());
            mcPffChrgLogDtlPO.setChrwvAmt(BigDecimal.ZERO);
//            mcPffChrgLogDtlPO.setChrwvMtdCde("0");
            mcPffChrgLogDtlPO.setChrwvMtdCde("WITHOUT_POST");
            mcPffChrgLogDtlPO.setIsWaive("N");
            mcPffChrgLogDtlPO.setProfoVal(items.getPortfolioValue());
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
            mcPffChrgLogPO.setYearMth(items.getDate());
            mcPffChrgLogPO.setFromDate(items.getDate());
            mcPffChrgLogPO.setToDate(items.getDate());
            mcPffChrgLogPO.setCmpnyCde("TFS");
            mcPffChrgLogPO.setMrktId(findMarketId(PropertyConverUtils.marketCodeConver(items.getMarket())));
//            mcPffChrgLogPO.setAcId(po.getClntCode());
            mcPffChrgLogPO.setAcId("02-0000389-30");
            mcPffChrgLogPO.setChrgCcyCde(PropertyConverUtils.standardizeCurrencyCode(items.getCcy()));
            mcPffChrgLogPO.setAcruChrgAmt(items.getORFee());
            mcPffChrgLogPO.setAcruChrwvDrAmt(BigDecimal.ZERO);
            mcPffChrgLogPO.setAcruChrwvWithoutPostAmt(BigDecimal.ZERO);
            mcPffChrgLogPO.setIsShowStmt("Y");
            mcPffChrgLogPO.setIsPostLedg(Objects.equals(items.getStatus(), "POST") ? "Y" : "N");
            mcPffChrgLogPO.setPostLedgDate(items.getPostDate());
//            mcPffChrgLogPO.setAcFundTxnRid();
            mcPffChrgLogPO.setRecVerNum(0L);
            mcPffChrgLogPO.setInitTime(LocalDateTime.now());
            mcPffChrgLogPO.setLastUpdTime(LocalDateTime.now());
            mcPffChrgLogPO.setLastUpdBy("MIG");
            mcPffChrgLogPO.setTagSeq(0L);

            portfolioFeeDailyMapMSSEPO.setDate(items.getDate());
            portfolioFeeDailyMapMSSEPO.setClntCode(items.getClntCode());
            portfolioFeeDailyMapMSSEPO.setAcctType(items.getAcctType());
            portfolioFeeDailyMapMSSEPO.setMarket(items.getMarket());
            portfolioFeeDailyMapMSSEPO.setCcy(items.getCcy());
            portfolioFeeDailyMapMSSEPO.setType(items.getType());
            portfolioFeeDailyMapMSSEPO.setPffChrgLogId(mainId);
            portfolioFeeDailyMapMSSEPO.setPffChrgLogDtlId(pffChrgLogDtlId);
            portfolioFeeDailyMapMSSEPO.setTxnDate(items.getDate());
            portfolioFeeDailyMapMSSEPO.setAcId("02-0000389-30");
            portfolioFeeDailyMapMSSEPO.setChrgCcyCde(PropertyConverUtils.standardizeCurrencyCode(items.getCcy()));

            portfolioFeeDailyMapMSSEPOList.add(portfolioFeeDailyMapMSSEPO);
            mcPffChrgLogDtlPOList.add(mcPffChrgLogDtlPO);
            mcPffChrgLogPOList.add(mcPffChrgLogPO);
        }
        portfolioFeeDailyMapMSSEMapper.insert(portfolioFeeDailyMapMSSEPOList);
        saveToOracleMcPffChrgLog(mcPffChrgLogPOList);
        saveToOracleMcPffChrgLogDtl(mcPffChrgLogDtlPOList);
        return "ok";
    }

    @DS("oracle")
    @Transactional(rollbackFor = Exception.class)
    public void saveToOracleMcPffChrgLogDtl(List<McPffChrgLogDtlPO> list) {
        mcPffChrgLogDtlMapper.batchInsert(list);
    }

    @DS("oracle")
    @Transactional(rollbackFor = Exception.class)
    public void saveToOracleMcPffChrgLog(List<McPffChrgLogPO> list) {
        mcPffChrgLogMapper.batchInsert(list);
    }

    private Long findMarketId(String market) {
        McMrktPO instrIdByCode = mcMrktMapper.findInstrIdByCode(market);
        return instrIdByCode.getMrktId();
    }
}
