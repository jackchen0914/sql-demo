package org.example.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.CashTransferPO;
import org.example.pojo.dtos.CashTransferAllDTO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JackChen
 * @since 2025-12-25
 */
@Mapper
@DS("master")
public interface CashTransferMapper extends BaseMapper<CashTransferPO> {

    @Select("SELECT TOP 5 SeqNo, TransferDate, Status, AllClient, ClntCode, AcctType, MarketFrom, MarketTo, CCYFrom, CCYTo, BankFrom, BankTo, AmountFrom, AmountTo, FXrate, Remarks, UserIDSave, UserIDCancel, [TimeStamp], SyncRef, ExternalSeqNo, BuyInXRate, SellOutXRate, BatchID\n" +
            "FROM dbo.CashTransfer;")
    List<CashTransferPO> selectTop5();

    List<CashTransferAllDTO> selectCashTransferAll(@Param("offset") int offset, @Param("limit") int limit);
}
