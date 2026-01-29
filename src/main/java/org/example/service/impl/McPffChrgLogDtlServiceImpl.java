package org.example.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.McMrktMapper;
import org.example.mapper.McPffChrgLogDtlMapper;
import org.example.mapper.McPffChrgLogMapper;
import org.example.mapper.PortfoliofeeDailyMapper;
import org.example.pojo.*;
import org.example.service.IMcPffChrgLogDtlService;
import org.example.utils.PropertyConverUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JackChen
 * @since 2025-12-29
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class McPffChrgLogDtlServiceImpl extends ServiceImpl<McPffChrgLogDtlMapper, McPffChrgLogDtlPO> implements IMcPffChrgLogDtlService {

    private final PortfoliofeeDailyMapper portfoliofeeDailyMapper;

    private final McPffChrgLogDtlMapper mcPffChrgLogDtlMapper;

    private final McPffChrgLogMapper mcPffChrgLogMapper;

    private final McMrktMapper mcMrktMapper;

    @Override
    public void writeProcessedData() {
        List<PortfoliofeeDailyPO> portfoliofeeDailyPOS = portfoliofeeDailyMapper.selectTop5();

        List<McPffChrgLogDtlPO> mcPffChrgLogDtlPOList = new ArrayList<>();
        List<McPffChrgLogPO> mcPffChrgLogPOList = new ArrayList<>();
        for (int i = 0; i < portfoliofeeDailyPOS.size(); i++) {
            PortfoliofeeDailyPO po = portfoliofeeDailyPOS.get(i);

            McPffChrgLogDtlPO mcPffChrgLogDtlPO = new McPffChrgLogDtlPO();
            McPffChrgLogPO mcPffChrgLogPO = new McPffChrgLogPO();
            mcPffChrgLogDtlPO.setPffChrgLogDtlId((long) (1+i));
            mcPffChrgLogDtlPO.setPffChrgLogId((long) (1+i));
            mcPffChrgLogDtlPO.setTxnDate(po.getDate());
            mcPffChrgLogDtlPO.setChrgAmt(po.getORFee());
            mcPffChrgLogDtlPO.setChrwvAmt(BigDecimal.ZERO);
//            mcPffChrgLogDtlPO.setChrwvMtdCde("0");
            mcPffChrgLogDtlPO.setChrwvMtdCde("WITHOUT_POST");
            mcPffChrgLogDtlPO.setIsWaive("N");
            mcPffChrgLogDtlPO.setProfoVal(po.getPortfolioValue());
//            mcPffChrgLogDtlPO.setChrgGrpVid();
            mcPffChrgLogDtlPO.setRecVerNum(0L);
            mcPffChrgLogDtlPO.setInitTime(LocalDateTime.now());
            mcPffChrgLogDtlPO.setLastUpdTime(LocalDateTime.now());
            mcPffChrgLogDtlPO.setLastUpdBy("MIG");
            mcPffChrgLogDtlPO.setTagSeq(0L);

            mcPffChrgLogPO.setPffChrgLogId((long) (1+i));
//            mcPffChrgLogPO.setTxnTypId();
            mcPffChrgLogPO.setSpcalChrgGrpCde("PFF");
            mcPffChrgLogPO.setYearMth(po.getDate());
            mcPffChrgLogPO.setFromDate(po.getDate());
            mcPffChrgLogPO.setToDate(po.getDate());
            mcPffChrgLogPO.setCmpnyCde("TFS");
            mcPffChrgLogPO.setMrktId(findMarketId(PropertyConverUtils.marketCodeConver(po.getMarket())));
//            mcPffChrgLogPO.setAcId(po.getClntCode());
            mcPffChrgLogPO.setAcId("02-0000389-30");
            mcPffChrgLogPO.setChrgCcyCde(PropertyConverUtils.standardizeCurrencyCode(po.getCcy()));
            mcPffChrgLogPO.setAcruChrgAmt(po.getORFee());
            mcPffChrgLogPO.setAcruChrwvDrAmt(BigDecimal.ZERO);
            mcPffChrgLogPO.setAcruChrwvWithoutPostAmt(BigDecimal.ZERO);
            mcPffChrgLogPO.setIsShowStmt("Y");
            mcPffChrgLogPO.setIsPostLedg(Objects.equals(po.getStatus(), "POST") ? "Y" : "N");
            mcPffChrgLogPO.setPostLedgDate(po.getPostDate());
//            mcPffChrgLogPO.setAcFundTxnRid();
            mcPffChrgLogPO.setRecVerNum(0L);
            mcPffChrgLogPO.setInitTime(LocalDateTime.now());
            mcPffChrgLogPO.setLastUpdTime(LocalDateTime.now());
            mcPffChrgLogPO.setLastUpdBy("MIG");
            mcPffChrgLogPO.setTagSeq(0L);

            mcPffChrgLogDtlPOList.add(mcPffChrgLogDtlPO);
            mcPffChrgLogPOList.add(mcPffChrgLogPO);
        }
            saveToOracleMcPffChrgLog(mcPffChrgLogPOList);
            saveToOracleMcPffChrgLogDtl(mcPffChrgLogDtlPOList);
    }

    @DS("oracle")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveToOracleMcPffChrgLogDtl(List<McPffChrgLogDtlPO> list){
        mcPffChrgLogDtlMapper.insert(list);
    }

    @DS("oracle")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveToOracleMcPffChrgLog(List<McPffChrgLogPO> list){
        mcPffChrgLogMapper.insert(list);
    }

    private Long findMarketId(String market) {
        McMrktPO instrIdByCode = mcMrktMapper.findInstrIdByCode(market);
        return instrIdByCode.getMrktId();
    }
}
