package org.company.service;

import org.company.configuration.properties.IncomeClientProperties;
import org.company.dto.PersonIncomeDTO;
import org.company.exceptions.NoPersonWithSuchIdException;
import org.company.exceptions.NonValidExternalResourceOrEmptyException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class IncomeClient {
    private final RestTemplate defaultRestTemplate;
    private final IncomeClientProperties incomeClientProperties;

    public IncomeClient(RestTemplate defaultRestTemplate, IncomeClientProperties incomeClientProperties) {
        this.defaultRestTemplate = defaultRestTemplate;
        this.incomeClientProperties = incomeClientProperties;
    }

    public double getIncome(long personId) {
        return getPersonList().stream()
                .filter(p -> personId == p.id())
                .findFirst()
                .map(PersonIncomeDTO::income)
                .orElseThrow(() -> new NoPersonWithSuchIdException(personId));
    }

    private List<PersonIncomeDTO> getPersonList() {
        String url = incomeClientProperties.getUrl();
        List<PersonIncomeDTO> personList = defaultRestTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<PersonIncomeDTO>>() {}).getBody();

        if (personList == null) {
            throw new NonValidExternalResourceOrEmptyException(url);
        }
        return personList;
    }
}
