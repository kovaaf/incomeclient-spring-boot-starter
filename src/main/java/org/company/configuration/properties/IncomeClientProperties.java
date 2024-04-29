package org.company.configuration.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "income-client-application")
public class IncomeClientProperties {
    private final String url;
    public IncomeClientProperties(String url) {
        this.url = url;
    }
}
