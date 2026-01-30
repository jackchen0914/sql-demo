package org.example.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import lombok.RequiredArgsConstructor;
import org.example.mapper.GlobalSettingsMapper;
import org.example.mapper.McInstrMapper;
import org.example.mapper.McMrktMapper;
import org.example.pojo.GlobalSettingsPO;
import org.example.pojo.McInstrPO;
import org.example.pojo.McMrktPO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

/**
 * 避免数据库事务冲突
 */
@Service
@RequiredArgsConstructor
public class OracleQueryService {

    private final McMrktMapper mcMrktMapper;
    private final McInstrMapper mcInstrMapper;
    private final GlobalSettingsMapper globalSettingsMapper;

//    @DS("oracle")
//    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    private Long findInstrIdByCode(String instrument) {
        if (instrument.isBlank()) {
            return null;
        }
        List<McInstrPO> instrIdByCodeList = mcInstrMapper.findInstrIdByCodeList(instrument);
        return CollectionUtils.isEmpty(instrIdByCodeList) ? null: instrIdByCodeList.get(0).getInstrId();
    }

//    @DS("oracle")
//    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public Long findMarketId(String market) {
        if (market.isBlank()) {
            return null;
        }
        McMrktPO instrIdByCode = mcMrktMapper.findInstrIdByCode(market);
        return instrIdByCode.getMrktId() != null ? instrIdByCode.getMrktId() : null;
    }

//    public String findSettingsValueBySource(String key) {
//
//        GlobalSettingsPO globalSettingsPO = iGlobalSettingsService.selectSettingBySource(key);
//        if (Objects.nonNull(globalSettingsPO)) {
//            return globalSettingsPO.getSetting();
//        }
//        return " ";
//
//    }
}
