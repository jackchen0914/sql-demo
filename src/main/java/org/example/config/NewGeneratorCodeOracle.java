package org.example.config;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.querys.OracleQuery;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.Types;
import java.util.Collections;

public class NewGeneratorCodeOracle {

    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:oracle:thin:@//10.11.198.16:1721/MSEUZ","JTQAMSSAPP","JTQAMSSAPP!")
        .globalConfig(builder -> builder
                .author("JackChen")
                .outputDir(System.getProperty("user.dir") + "/src/main/java")
                .commentDate("yyyy-MM-dd")
                .disableOpenDir()
//                .enableSwagger()
        )
        .dataSourceConfig(builder -> builder
                        .dbQuery(new OracleQuery())
                        .schema("MSSAPP")
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
//                        .addInclude("MC_AC_FUND_REC")
//                        .addInclude("MC_AC_FUND_TXN_REC")
//                        .addInclude("MC_FUND_TP_RELTN")
//                        .addInclude("MC_CMPNY_BANK_AC")

//                        .addInclude("MC_AC_STK_TXN_DTL")
//                        .addInclude("MC_AC_STK_TXN_DTL_STAT")
//                        .addInclude("MC_STK_TXN")
//                        .addInclude("MC_STK_MEMO_TXN")
//                        .addInclude("MC_STK_MEMO_TXN_REQ")
//                        .addInclude("MC_LOCTN_STK_TXN_DTL")
//                        .addInclude("MC_INSTR")
//                        .addInclude("MC_MRKT")
//                        .addInclude("MC_INSTCL_VER")

//                        .addInclude("MC_AC_FUND_HOLD_REC")
//                        .addInclude("MC_AC_FUND_HOLD_TXN")

//                        .addInclude("MC_AC_HOLD_STK_TXN_DTL")
//                        .addInclude("MC_AC_HOLD_STK_TXN")

//                        .addInclude("MC_AC_ONLINE_FX_REQ")

//                        .addInclude("MC_ACUINT_TXN_DTL")
//                        .addInclude("MC_ACUINT_TXN")

//                        .addInclude("MC_PFF_CHRG_LOG")
//                        .addInclude("MC_PFF_CHRG_LOG_DTL")

//                        .addInclude("MC_CEVENT")
//                        .addInclude("MC_CONV_EVNT")

//                        .addInclude("MC_RM_AC_INSTR_LNDRAT")

                        .addInclude("MC_COMM_RULE")
                        .addInclude("MC_COMM_CALC_MTD")
                        .addTablePrefix("t_","sys_")
                        .entityBuilder()
                        .enableLombok()
                        .formatFileName("%sPO")
                        .enableTableFieldAnnotation()
                        .idType(IdType.INPUT)
                        .controllerBuilder()
                        .enableRestStyle())
        .templateEngine(new FreemarkerTemplateEngine())
        .execute();
    }

}
