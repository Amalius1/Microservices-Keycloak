package pl.aml.poc.servicea.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CallBService {

    private final RestTemplate restTemplate;

    @Autowired
    public CallBService(@Qualifier("serviceB") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String callServiceB() {
        String url = "http://localhost:8082/serviceB/secured/call";

        return restTemplate.getForObject(url, String.class);
    }

}
