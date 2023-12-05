package com.example.netology_diplom_moneytransferservice;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.testcontainers.containers.GenericContainer;
import com.example.netology_diplom_moneytransferservice.dto.Amount;
import com.example.netology_diplom_moneytransferservice.dto.TransferRequest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransferServiceTestcontainersTest {
    /*
    ToDO После упаковки проекта Maven/package и создания образа командой 'docker build -t restapp'
    ToDO можно раскомментировать строки и запускать тест
     */
        @Autowired
        private TestRestTemplate restTemplate;

        private static final GenericContainer<?> appRestApi = new GenericContainer<>("restappdiplom3")
                .withExposedPorts(5500);

        @BeforeAll
        public static void setUp() {
            appRestApi.start();
        }

        @Test
        void requestResponseTest() {
            HttpEntity<TransferRequest> request = new HttpEntity<>(new TransferRequest(
                    1111111111111111L, "11/22", "111",
                    2222222222222222L, new Amount(100, "RUB")));
            String response = restTemplate.postForObject("http://localhost:" + appRestApi.getMappedPort(5500) + "/transfer",
                    request, String.class);
            String expectedMessage = "operationId";
            assertNotNull(response);
            assertTrue(response.contains(expectedMessage));
        }

}