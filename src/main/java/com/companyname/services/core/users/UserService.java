package com.companyname.services.core.users;

import com.companyname.services.core.users.api.behavior.RegisterUser;
import com.companyname.services.core.users.api.error.RegistrationErrorException;
import com.companyname.services.core.users.api.model.UserRegistrationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.keycloak.admin.client.Keycloak;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

import static org.springframework.http.MediaType.ALL_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
public class UserService implements RegisterUser {
    @Override
    public void forRequest(UserRegistrationRequest request) {
        Keycloak keycloak = Keycloak.getInstance("https://localhost:9880", "master", "auth-admin", "836Jw4XceeWT", "admin-cli");
        String accessToken = "bearer " + keycloak.tokenManager().getAccessTokenString();

        WebClient webClient = WebClient
                .builder()
                .baseUrl("https://localhost:9880/admin/realms/Employee-Management-Service")
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.add(HttpHeaders.ACCEPT, ALL_VALUE);
                    httpHeaders.add(HttpHeaders.AUTHORIZATION, accessToken);
                })
                .build();

        try {
            ObjectMapper MAPPER = new ObjectMapper();
            MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            MAPPER.registerModule(new JavaTimeModule());

            System.out.println("Send to keycloak: " + MAPPER.writeValueAsString(new KeycloakUser().mappedFrom(request)));

            KeycloakResponse keycloakResponse = webClient
                    .post()
                    .uri("/users")
                    .contentType(APPLICATION_JSON)
                    .bodyValue(new KeycloakUser().mappedFrom(request))
                    .exchangeToMono(response -> response.bodyToMono(KeycloakResponse.class))
                    .block(Duration.ofSeconds(10));
            if (keycloakResponse != null && keycloakResponse.getError() != null && !keycloakResponse.getError().isEmpty()) {
                throw new RegistrationErrorException("An error occurred while registering the user with message from Keycloak: " + keycloakResponse.getError());
            }
        } catch (Exception ex) {
            throw new RegistrationErrorException("An error occurred while registering the user", ex);
        }
    }
}
