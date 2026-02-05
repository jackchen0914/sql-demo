package org.example.config;

import org.example.pojo.*;
import org.example.pojo.dtos.BrokerageWithRageDTO;
import org.example.pojo.dtos.CaRSCDTO;
import org.example.pojo.dtos.CashTransferAllDTO;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public class OracleBatchInsertGenerator {
    /**
     * 生成可追加的到现有xml的批量插入片段
     * @param entityClass 实体类
     * @param tableName 表明
     */
    public static String generateInsertFragment(Class<?> entityClass,String tableName){
        StringBuilder sb = new StringBuilder();

        sb.append("    <!--  Oracle BatchInsert  -->\n");
        sb.append("    <insert id=\"batchInsert\" parameterType=\"java.util.List\">\n");
        sb.append("      INSERT ALL\n");
        sb.append("    <foreach collection=\"list\" item=\"item\">\n");

        Field[] fields = entityClass.getDeclaredFields();
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            String fieldName = field.getName();
            if(!fieldName.contains("serialVersionUID")){
//                System.out.println("========>"+fieldName);
                String columnName = camelToUnderscore(fieldName).toUpperCase();
                String jdbcType = getJdbcType(field.getType());

                if(i>0){
                    columns.append(", ");
                    values.append(", ");
                }
                columns.append(columnName);
                values.append("#{item.").append(fieldName).append(",jdbcType=").append(jdbcType).append("}");
            }
        }

        sb.append("  INTO ").append(tableName).append(" (").append(columns).append(")\n");
        sb.append(" VALUES (").append(values).append(")\n");
        sb.append(" </foreach>\n");
        sb.append(" SELECT 1 FROM DUAL\n");
        sb.append(" </insert>");

        return sb.toString();
    }

    /**
     * 生成ResultMap片段
     * @param entityClass 实体类
     * @param resultMapId resultMap的id
     */
    public static String generateResultMap(Class<?> entityClass,String resultMapId){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("     <resultMap type=\"").append(entityClass.getName()).append("\" id=\"").append(resultMapId).append("\">\n");
        Field[] fields = entityClass.getDeclaredFields();
        for (Field field : fields){
            String fieldName = field.getName();
            String columnName = capitalize(fieldName);
            stringBuilder.append("     <result property=\"").append(fieldName).append("\"")
                         .append("     column=\"").append(columnName).append("\"")
                         .append("     />\n");
        }
        stringBuilder.append("     </resultMap>");
        return stringBuilder.toString();
    }

    /**
     * 首字母大写
     */
    public static String capitalize(String str){
        if(str == null || str.isEmpty()){
            return str;
        }
        return str.substring(0,1).toUpperCase() + str.substring(1);
    }

    /**
     * 生成对应的mapper接口方法声明
     * @param entityClass 实体类
     */
    public static String generateMapperMethod(Class<?> entityClass){
        return String.format("int batchInsert(@Param(\"list\") List<%s> list);",entityClass.getSimpleName());
    }

    /**
     * 生成完整的mapper xml文件
     * @param entityClass
     * @param tableName
     * @param mapperNameSpace
     * @return
     */
    public static String generateFullXml(Class<?> entityClass,String tableName,String mapperNameSpace){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        stringBuilder.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n");
        stringBuilder.append("<mapper namespace=\"").append(mapperNameSpace).append("\">\n\n");
        stringBuilder.append(generateInsertFragment(entityClass,tableName)).append("\n\n");
        stringBuilder.append("</mapper>");
        return stringBuilder.toString();
    }

    private static String camelToUnderscore(String camelCase) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < camelCase.length(); i++) {
            char c = camelCase.charAt(i);
            if(Character.isUpperCase(c)){
                if(i>0){
                    result.append("_");
                }
                result.append(Character.toLowerCase(c));
            }else{
                result.append(c);
            }
        }
        return result.toString();
    }

    private static String getJdbcType(Class<?> type){
        if (type == String.class) return "VARCHAR";
        if (type == Long.class || type == long.class) return "BIGINT";
        if (type == Integer.class || type == int.class) return "INTEGER";
        if (type == BigDecimal.class) return "DECIMAL";
        if (type == Date.class || type == LocalDateTime.class) return "TIMESTAMP";
        if (type == Boolean.class || type == boolean.class) return "BOOLEAN";
        if (type == Double.class || type == double.class) return "DOUBLE";
        return "VARCHAR";
    }

    public static void main(String[] args) {
        //生成可追加的xml片段
        String fragment = generateInsertFragment(McAcHoldStkTxnDtlPO.class, "MSSAPP.MC_AC_HOLD_STK_TXN_DTL");
        System.out.println("===== xml =====");
        System.out.println(fragment);
        String fragment1 = generateInsertFragment(McAcHoldStkTxnPO.class, "MSSAPP.MC_AC_HOLD_STK_TXN");
        System.out.println("===== xml =====");
        System.out.println(fragment1);
//        String fragment2 = generateInsertFragment(McStkMemoTxnPO.class, "MSSAPP.MC_STK_MEMO_TXN");
//        System.out.println("===== xml =====");
//        System.out.println(fragment2);
        //生成 接口方法
//        String method = generateMapperMethod(McRmAcInstrLndratPO.class);
//        System.out.println("===== mapper api method =====");
//        System.out.println(method);
        //生成resultMap映射
//        String resultMap = generateResultMap(BrokerageWithRageDTO.class,"selectBrokerageWithRageMap");
//        System.out.println("===== result map =====");
//        System.out.println(resultMap);
    }
}
