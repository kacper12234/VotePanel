package com.onwelo.votepanel.api;

import com.onwelo.votepanel.dto.ChoiceDto;
import com.onwelo.votepanel.dto.ElectionDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Testcontainers
public class ElectionApiTest {

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
    void shouldFailWithoutChoices() {
        given()
                .contentType(ContentType.JSON)
                .body(new ElectionDto("test election", LocalDate.now(), Collections.emptyList()))
        .when()
                .post("/election")
        .then()
                .statusCode(400);
    }

    @Test
    @Order(2)
    void shouldAddElection() {
        List<ChoiceDto> choices = List.of(new ChoiceDto("Option 1"), new ChoiceDto("Option 2"));
        given()
                .contentType(ContentType.JSON)
                .body(new ElectionDto("test election", LocalDate.now(), choices))
        .when()
                .post("/election")
        .then()
                .statusCode(200);

        given()
                .when()
                .get("/election?id=1")
                .then()
                .statusCode(200)
                .body("name", Matchers.equalTo("test election"))
                .body("choices.size()", Matchers.equalTo(2))
                .body("choices[0].name", Matchers.equalTo("Option 1"))
                .body("choices[0].votes", Matchers.equalTo(0))
                .body("choices[1].name", Matchers.equalTo("Option 2"))
                .body("choices[1].votes", Matchers.equalTo(0));
    }

    @Test
    @Order(3)
    void shouldReturnNotFoundForUnknownElection() {
        given()
        .when()
                .get("/election?id=9999")
        .then()
                .statusCode(404);
    }


    @Test
    @Order(4)
    @Sql(scripts = "classpath:sql/election_data.sql")
    void shouldCountVotes() {
        given()
        .when()
                .get("/election?id=2")
        .then()
                .statusCode(200)
                .body("name", Matchers.equalTo("General"))
                .body("choices.size()", Matchers.equalTo(2))
                .body("choices[0].name", Matchers.equalTo("Choice A"))
                .body("choices[0].votes", Matchers.equalTo(2))
                .body("choices[1].name", Matchers.equalTo("Choice B"))
                .body("choices[1].votes", Matchers.equalTo(1));
    }
}
