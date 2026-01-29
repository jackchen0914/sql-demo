package org.example.config;
//
//import com.baomidou.mybatisplus.core.MybatisConfiguration;
//import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import org.apache.ibatis.type.JdbcType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//
//@Configuration
public class MybatisPlusConfig {

//    @Bean
//    public ConfigurationCustomizer configurationCustomizer(){
//        return new MybatisPlusCustomizers();
//    }
//
//    class MybatisPlusCustomizers implements ConfigurationCustomizer{
//
//        @Override
//        public void customize(MybatisConfiguration configuration) {
//            configuration.setJdbcTypeForNull(JdbcType.NULL);
//        }
//    }
}
