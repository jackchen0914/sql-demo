package org.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.DivAnnCombineMapper;
import org.example.mapper.DivAnnRightsMapper;
import org.example.mapper.DivAnnSplitMapper;
import org.example.mapper.McConvEvntMapper;
import org.example.pojo.McConvEvntPO;
import org.example.service.IMcConvEvntService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JackChen
 * @since 2026-01-07
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class McConvEvntServiceImpl extends ServiceImpl<McConvEvntMapper, McConvEvntPO> implements IMcConvEvntService {

    private final DivAnnSplitMapper divAnnSplitMapper;

    private final DivAnnRightsMapper divAnnRightsMapper;

    private final DivAnnCombineMapper divAnnCombineMapper;

    @Override
    public void writeProcessedData() {

    }
}
