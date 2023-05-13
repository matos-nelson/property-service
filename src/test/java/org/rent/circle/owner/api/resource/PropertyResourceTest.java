package org.rent.circle.owner.api.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.rent.circle.owner.api.owner.api.dto.SavePropertyDto;
import org.rent.circle.owner.api.owner.api.resource.PropertyResource;

@QuarkusTest
@TestHTTPEndpoint(PropertyResource.class)
@QuarkusTestResource(H2DatabaseTestResource.class)
public class PropertyResourceTest {

    @Test
    public void Post_WhenGivenAValidPropertyToSave_ShouldReturnSavedProperty() {
        // Arrange
        SavePropertyDto savePropertyDto = SavePropertyDto.builder()
            .addressId(123L)
            .ownerId(456L)
            .build();

        // Act
        // Assert
        given()
            .contentType("application/json")
            .body(savePropertyDto)
            .when()
            .post()
            .then()
            .statusCode(HttpStatus.SC_OK)
            .body(is("1"));
    }

    @Test
    public void Post_WhenGivenAnInValidPropertyToSave_ShouldReturnBadRequest() {
        // Arrange
        SavePropertyDto savePropertyDto = SavePropertyDto.builder()
            .addressId(null)
            .ownerId(456L)
            .build();

        // Act
        // Assert
        given()
            .contentType("application/json")
            .body(savePropertyDto)
            .when()
            .post()
            .then()
            .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
