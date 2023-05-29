package pl.aml.poc.servicea.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ServiceBRestTemplateConfig {

    private final OAuth2AuthorizedClientManager authorizedClientManager;

    public ServiceBRestTemplateConfig(OAuth2AuthorizedClientManager authorizedClientManager) {
        this.authorizedClientManager = authorizedClientManager;
    }

    @Bean
    @Qualifier("serviceB")
    public RestTemplate serviceBRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(addBearerTokenInterceptor());
        return restTemplate;
    }

    private ClientHttpRequestInterceptor addBearerTokenInterceptor() {
        return ((request, body, execution) -> {
            OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest.withClientRegistrationId("keycloak")
                    .principal("ServiceA") // This should match client-id
                    .build();

            OAuth2AuthorizedClient authorizedClient = this.authorizedClientManager.authorize(authorizeRequest);

            String accessToken = authorizedClient.getAccessToken().getTokenValue();
            request.getHeaders().add("Authorization", "Bearer " + accessToken);
            return execution.execute(request, body);
        });
    }


}
