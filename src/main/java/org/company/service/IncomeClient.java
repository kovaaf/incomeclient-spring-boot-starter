package org.company.service;

import org.company.configuration.properties.IncomeClientProperties;
import org.company.entity.Person;
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
                .filter(p -> personId == p.getId())
                .findFirst()
                .map(Person::getIncome)
                .orElseThrow(() -> new NoPersonWithSuchIdException(personId));
    }

    private List<Person> getPersonList() {
        String url = incomeClientProperties.getUrl();
        List<Person> personList = defaultRestTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Person>>() {}).getBody();

        if (personList == null) {
            throw new NonValidExternalResourceOrEmptyException(url);
        }
        return personList;
    }
}
