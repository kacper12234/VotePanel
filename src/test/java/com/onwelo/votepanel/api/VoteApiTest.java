package com.onwelo.votepanel.api;

import com.onwelo.votepanel.dto.VoteDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Sql(scripts = "classpath:sql/data_for_voting.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Testcontainers
class VoteApiTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer postgresContainer = new PostgreSQLContainer(DockerImageName.parse("postgres:17-alpine"));

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }


    @Test
    @Order(1)
    void shouldAcceptFirstVote() {
        given()
                .contentType(ContentType.JSON)
                .body(new VoteDto(1L, 1L, 1L))
        .when()
                .post("/vote")
        .then()
                .statusCode(201);
    }

    @Test
    @Order(2)
    void shouldRejectDuplicateVoteWithForbidden() {
        given()
                .contentType(ContentType.JSON)
                .body(new VoteDto(2L, 1L, 1L))
        .when()
                .post("/vote")
        .then()
                .statusCode(403)
                .body(containsString("already voted"));
    }

    @Test
    @Order(3)
    void shouldRejectBlockedVoter() {
        given()
                .contentType(ContentType.JSON)
                .body(new VoteDto(1L, 1L, 2L)) // voter 2 is blocked per migration
        .when()
                .post("/vote")
        .then()
                .statusCode(403)
                .body(containsString("blocked"));
    }

    @Test
    @Order(4)
    void shouldReturnNotFoundForUnknownVoter() {
        given()
                .contentType(ContentType.JSON)
                .body(new VoteDto(1L, 1L, 9999L))
        .when()
                .post("/vote")
        .then()
                .statusCode(404)
                .body(containsString("Voter not found"));
    }

    @Test
    @Order(5)
    void shouldReturnNotFoundForUnknownChoice() {
        given()
                .contentType(ContentType.JSON)
                .body(new VoteDto(9999L, 1L, 1L))
        .when()
                .post("/vote")
        .then()
                .statusCode(404)
                .body(containsString("Choice not found"));
    }

    @Test
    @Order(6)
    void shouldReturnChoiceNotInElection() {
        given()
                .contentType(ContentType.JSON)
                .body(new VoteDto(1L, 2L, 1L))
        .when()
                .post("/vote")
        .then()
                .statusCode(400)
                .body(containsString("not in election"));
    }

    @Test
    @Order(7)
    void shouldReturnOneVote() {
        given()
        .when()
                .get("/election?id=1")
        .then()
                .statusCode(200)
                .body("name", Matchers.equalTo("General"))
                .body("choices.size()", Matchers.equalTo(2))
                .body("choices[0].name", Matchers.equalTo("Choice A"))
                .body("choices[0].votes", Matchers.equalTo(1))
                .body("choices[1].name", Matchers.equalTo("Choice B"))
                .body("choices[1].votes", Matchers.equalTo(0));
    }
}
