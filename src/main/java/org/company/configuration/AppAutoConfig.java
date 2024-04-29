package org.company.configuration;

import org.company.configuration.properties.IncomeClientProperties;
import org.company.service.IncomeClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableConfigurationProperties(IncomeClientProperties.class)
public class AppAutoConfig {
    @Bean(name = "defaultRestTemplate")
    @ConditionalOnMissingBean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Bean
    @ConditionalOnMissingBean
    public IncomeClient incomeClient(RestTemplate defaultRestTemplate, IncomeClientProperties incomeClientProperties) {
        return new IncomeClient(defaultRestTemplate, incomeClientProperties);
    }
}
