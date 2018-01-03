package com.runcoding.handler.configurer;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @module 主数据源配置
 * @author xukai
 * @date: 2017年10月27日
 */
@Configuration
@MapperScan(basePackages = "com.runcoding.dao",
            sqlSessionFactoryRef = "mainSqlSessionFactory")
public class DataSourceConfiguration {


    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> dataSource;

    @Bean(name = "mainDataSource")
    @ConfigurationProperties("spring.datasource")
    @Primary
    public DataSource dataSource() {
        return DataSourceBuilder.create().type(dataSource).build();
    }

    @Bean(name = "mainTransactionManager")
    @Primary
    public DataSourceTransactionManager mainTransactionManager(@Qualifier("mainDataSource") DataSource mainDataSource) {
        return new DataSourceTransactionManager(mainDataSource);
    }

    @Bean(name = "mainSqlSessionFactory")
    @Primary
    public SqlSessionFactory mainSqlSessionFactory(@Qualifier("mainDataSource") DataSource mainDataSource)
            throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(mainDataSource);
        Properties properties = new Properties();
        properties.setProperty("dialect", "mysql");
        properties.setProperty("reasonable", "false");
        properties.setProperty("pageSizeZero", "true");
        properties.setProperty("param", "pageNum=start;pageSize=limit");
        return sessionFactory.getObject();
    }
}
