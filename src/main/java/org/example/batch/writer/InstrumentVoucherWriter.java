package org.example.batch.writer;

import com.baomidou.dynamic.datasource.annotation.DS;
import lombok.RequiredArgsConstructor;
import org.example.mapper.*;
import org.example.pojo.*;
import org.example.pojo.dtos.InstrumentVoucherResultDTO;
import org.example.pojo.dtos.InstrumentVoucherResultDTO;
import org.example.utils.DataBaseOperationUtils;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
public class InstrumentVoucherWriter implements ItemWriter<InstrumentVoucherResultDTO> {

    private static final int BATCH_SIZE = 1000;

    private final McStkTxnMapper mcStkTxnMapper;
    private final McAcStkTxnDtlMapper mcAcStkTxnDtlMapper;
    private final McAcStkTxnDtlStatMapper mcAcStkTxnDtlStatMapper;
    private final McStkMemoTxnMapper mcStkMemoTxnMapper;
    private final McStkMemoTxnReqMapper mcStkMemoTxnReqMapper;
    private final McLoctnStkTxnDtlMapper mcLoctnStkTxnDtlMapper;

    @Override
    @DS("oracle")
    @Transactional(rollbackFor = Exception.class)
    public void write(List<? extends InstrumentVoucherResultDTO> items) throws Exception {
        if(items.isEmpty()){
            return;
        }

        List<McStkTxnPO> mcStkTxnRecord = new ArrayList<>();
        List<McAcStkTxnDtlPO> mcAcStkTxnDtlRecord = new ArrayList<>();
        List<McAcStkTxnDtlStatPO> mcAcStkTxnDtlStatRecord = new ArrayList<>();
        List<McStkMemoTxnPO> mcStkMemoTxnRecord = new ArrayList<>();
        List<McStkMemoTxnReqPO> mcStkMemoTxnReqRecord = new ArrayList<>();
        List<McLoctnStkTxnDtlPO> mcLoctnStkTxnDtlRecord = new ArrayList<>();

        for (InstrumentVoucherResultDTO dto : items){

            DataBaseOperationUtils.addIfNotNull(mcStkTxnRecord,dto.getMcStkTxnRecord());
            DataBaseOperationUtils.addIfNotNull(mcAcStkTxnDtlRecord,dto.getMcAcStkTxnDtlRecord());
            DataBaseOperationUtils.addIfNotNull(mcAcStkTxnDtlStatRecord,dto.getMcAcStkTxnDtlStatRecord());
            DataBaseOperationUtils.addIfNotNull(mcStkMemoTxnRecord,dto.getMcStkMemoTxnRecord());
            DataBaseOperationUtils.addIfNotNull(mcStkMemoTxnReqRecord,dto.getMcStkMemoTxnReqRecord());
            DataBaseOperationUtils.addIfNotNull(mcLoctnStkTxnDtlRecord,dto.getMcLoctnStkTxnDtlRecord());

//            if(!dto.getDetailRecord().isEmpty()){
//                detailsRecord.addAll(dto.getDetailRecord());
//            }
        }

        DataBaseOperationUtils.batchInsertFrom(mcStkTxnRecord,mcStkTxnMapper::batchInsert,BATCH_SIZE);
        DataBaseOperationUtils.batchInsertFrom(mcAcStkTxnDtlRecord,mcAcStkTxnDtlMapper::batchInsert,BATCH_SIZE);
        DataBaseOperationUtils.batchInsertFrom(mcAcStkTxnDtlStatRecord,mcAcStkTxnDtlStatMapper::batchInsert,BATCH_SIZE);
        DataBaseOperationUtils.batchInsertFrom(mcStkMemoTxnRecord,mcStkMemoTxnMapper::batchInsert,BATCH_SIZE);
        DataBaseOperationUtils.batchInsertFrom(mcStkMemoTxnReqRecord,mcStkMemoTxnReqMapper::batchInsert,BATCH_SIZE);
        DataBaseOperationUtils.batchInsertFrom(mcLoctnStkTxnDtlRecord,mcLoctnStkTxnDtlMapper::batchInsert,BATCH_SIZE);
    }
}
