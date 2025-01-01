package com.javajuniorready.feature;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.javajuniorready.BaseIntegrationTest;
import org.junit.Test;
import org.springframework.http.HttpStatus;

@WireMockTest
public class UserPlayedLottoAndWonIntegrationTest extends BaseIntegrationTest {


        @Test
        public void should_user_win_and_system_should_generate_winners() throws Exception {
            wireMockServer.stubFor(WireMock.get("/api/v1.0/random?min=1&max=99&count=6")
                    .willReturn(WireMock.aResponse()
                            .withStatus(HttpStatus.OK.value())
                            .withHeader("Content-Type", "application/json")
                            .withBody("[1, 2, 3, 4, 5, 6]")));


        }
}
