package pl.aml.poc.servicea.service;

import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CallBService {

    private final RestTemplate restTemplate;

    @Autowired
    public CallBService(KeycloakRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String callServiceB() {
        String url = "http://localhost:8082/serviceB/secured/call";
        return restTemplate.getForObject(url, String.class);
    }

}
