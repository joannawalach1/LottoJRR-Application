package com.javajuniorready.feature;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import com.javajuniorready.BaseIntegrationTest;

public class UserPlayedLottoAndWonIntegrationTest extends BaseIntegrationTest {

        @Test
        public void should_user_win_and_system_should_generate_winners() {
            wireMockServer.stubFor(WireMock.get("/api/v1.0/random?min=1&max=99&count=25")
                    .willReturn(WireMock.aResponse()
                            .withStatus(HttpStatus.OK.value())
                            .withHeader("Content-Type", "application/json")
                            .withBody("""
                                [1, 2, 3, 4, 5, 6, 82, 82, 83, 83, 86, 57, 10, 81, 53, 93, 50, 54, 31, 88, 15, 43, 79, 32, 43]
                                """.trim()
                            )));
        }
}
