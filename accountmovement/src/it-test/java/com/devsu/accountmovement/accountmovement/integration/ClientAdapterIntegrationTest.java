package com.devsu.accountmovement.accountmovement.integration;

import static org.assertj.core.api.Assertions.assertThat;
import com.devsu.accountmovement.accountmovement.domain.models.Client;
import com.devsu.accountmovement.accountmovement.infrastructure.adapter.ClientAdapter;
import com.devsu.accountmovement.accountmovement.infrastructure.controllers.AccountController;

import com.devsu.accountmovement.accountmovement.infrastructure.controllers.dto.AccountDTO;
import org.junit.jupiter.api.*;
import org.mockito.MockitoAnnotations;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.mockserver.integration.ClientAndServer;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import org.junit.jupiter.api.Test;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class ClientAdapterIntegrationTest{

    @Autowired
    private ClientAdapter clientAdapter;
    @Autowired
    private AccountController accountController;
    @Autowired
    private TestRestTemplate restTemplate;

    //@Value("${client.api.url}")
    private String clientPersonApiUrl;
    private MockMvc mockMvc;
    private ClientAndServer mockServer;
    @BeforeEach
    public void startMockServer() {
        mockServer = ClientAndServer.startClientAndServer(8081);
    }

    @AfterEach
    public void stopMockServer() {
        mockServer.stop();
    }

    @Test
    public void testCreateAccountWithExistingClient() throws Exception {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();

        mockServer.when(HttpRequest.request()
                        .withMethod("GET")
                        .withPath("/api/client/12345"))
                .respond(HttpResponse.response()
                        .withStatusCode(200)
                        .withContentType(MediaType.APPLICATION_JSON)
                        .withBody("{\"id\":1,\"identification\":\"12345\", \"name\":\"John Doe\"}"));
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountNumber("98765");
        accountDTO.setAccountType("Ahorros");
        accountDTO.setAccountBalance(100L);
        accountDTO.setStatus(true);
        accountDTO.setIdentification("12345");
        HttpEntity<AccountDTO> request = new HttpEntity<>(accountDTO);
        ResponseEntity<Object> accountResponse = restTemplate.postForEntity("http://localhost:8080/api/account", request, Object.class);
        assertThat(accountResponse.getStatusCode().is2xxSuccessful()).isTrue();
     }
         
}
