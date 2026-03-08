package com.onwelo.votepanel.api;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Testcontainers
public class VoterApiTest {

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
    void shouldAddVoter() {
        given()
                .contentType("application/json")
                .body("""
                        {
                          "name": "Jan Kowalski",
                          "personalNumber": "98081207075",
                          "address" :"ul. Herberta 00-123 Warszawa"
                        }
                       """)
        .when()
                .post("/voter")
        .then()
                .statusCode(201);

        given()
        .when()
                .get("/voter?personalNumber=98081207075")
        .then()
                .statusCode(200)
                .body("personalNumber", Matchers.equalTo("98081207075"))
                .body("name", Matchers.equalTo("Jan Kowalski"))
                .body("address", Matchers.equalTo("ul. Herberta 00-123 Warszawa"))
                .body("blocked", Matchers.equalTo(false));
    }

    @Test
    @Order(2)
    void shouldReturnNotFoundForUnknownVoter() {
        given()
                .when()
                .get("/voter?personalNumber=99999999999")
        .then()
                .statusCode(404)
                .body(containsString("personal number"));
    }

    @Test
    @Order(3)
    void shouldBlockVoter() {
        given()
        .when()
                .put("/voter/block?id=1")
        .then()
                .statusCode(200);

        given()
        .when()
                .get("/voter?personalNumber=98081207075")
        .then()
                .statusCode(200)
                .body("personalNumber", Matchers.equalTo("98081207075"))
                .body("name", Matchers.equalTo("Jan Kowalski"))
                .body("address", Matchers.equalTo("ul. Herberta 00-123 Warszawa"))
                .body("blocked", Matchers.equalTo(true));
    }

    @Test
    @Order(4)
    void shouldUnlockVoter() {
        given()
                .when()
                .put("/voter/unblock?id=1")
                .then()
                .statusCode(200);

        given()
        .when()
                .get("/voter?personalNumber=98081207075")
        .then()
                .statusCode(200)
                .body("personalNumber", Matchers.equalTo("98081207075"))
                .body("name", Matchers.equalTo("Jan Kowalski"))
                .body("address", Matchers.equalTo("ul. Herberta 00-123 Warszawa"))
                .body("blocked", Matchers.equalTo(false));
    }

    @Test
    @Order(5)
    void shouldReturnNotFoundForUnknownVoterUpdate() {
        given()
        .when()
                .put("/voter/block?id=999")
        .then()
                .statusCode(404)
                .body(containsString("id"));
    }
}
