package org.example.config;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.querys.SqlServerQuery;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.Types;
import java.util.Collections;

public class NewGeneratorCode {

    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:sqlserver://10.5.80.179:1433;databaseName=OctOBackFT_1231;encrypt=true;trustServerCertificate=true","pleung","123456")
        .globalConfig(builder -> builder
                .author("JackChen")
                .outputDir(System.getProperty("user.dir") + "/src/main/java")
                .commentDate("yyyy-MM-dd")
                .disableOpenDir()
//                .enableSwagger()
        )
        .dataSourceConfig(builder -> builder
                        .dbQuery(new SqlServerQuery())
                        .schema("dbo")
                .typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    if (typeCode == Types.SMALLINT){
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);
                }))
        .packageConfig(builder -> builder
                        .parent("org.example.generator")
                        .entity("pojo")
                        .mapper("mapper")
                        .service("service")
                        .serviceImpl("service.impl")
                        .xml("mapper.xml")
                        .controller("controller")
                        .pathInfo(Collections.singletonMap(OutputFile.xml,System.getProperty("user.dir") + "/src/main/resources/mapper")))
        .strategyConfig(builder -> builder
//                        .addInclude("CashVoucher")
//                        .addInclude("CashVoucherRequest")
//                        .addInclude("TransactionTypes")
//                        .addInclude("ForexRate")
//                        .addInclude("InstrumentVoucher")
//                        .addInclude("CashTransfer")
//                        .addInclude("InterestDaily")
//                        .addInclude("PortfolioFee_Daily")
//                        .addInclude("DivAnn")
//                        .addInclude("DivAnnSplit")
//                        .addInclude("DivAnnRights")
//                        .addInclude("DivAnnCombine")
//                        .addInclude("ClntPriceCap")
//                        .addInclude("Brokerage")
//                        .addInclude("BrokerageRate")
                        .addInclude("GlobalSettings")
                        .addTablePrefix("t_","sys_")
                        .entityBuilder()
                        .enableLombok()
                        .enableTableFieldAnnotation()
                        .formatFileName("%sPO")
                        .logicDeleteColumnName("deleted")
                        .controllerBuilder()
                        .enableRestStyle())
        .templateEngine(new FreemarkerTemplateEngine())
        .execute();
    }

}
