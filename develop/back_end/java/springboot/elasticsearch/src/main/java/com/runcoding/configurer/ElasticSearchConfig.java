package com.runcoding.configurer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xukai
 */
@Configuration
public class ElasticSearchConfig {

    @Value("${spring.data.elasticsearch.indexName:product}")
    private String indexName;

    @Bean
    public String indexName(){
        return indexName;
    }

}


