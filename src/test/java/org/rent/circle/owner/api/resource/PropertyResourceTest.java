package org.rent.circle.owner.api.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import java.util.List;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.rent.circle.owner.api.dto.PropertyDto;
import org.rent.circle.owner.api.dto.SavePropertyDto;

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

    @Test
    public void GET_WhenAPropertyCantBeFound_ShouldReturnNoContent() {
        // Arrange

        // Act
        // Assert
        given()
            .when()
            .get("/123/owner/123")
            .then()
            .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void GET_WhenAPropertyIsFound_ShouldReturnPropertyInfo() {
        // Arrange

        // Act
        // Assert
        given()
            .when()
            .get("/100/owner/123")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .body("type", is("RENTAL"),
                "id", is(100),
                "addressId", is(456),
                "ownerId", is(123));
    }

    @Test
    public void GET_getProperties_WhenPropertiesCantBeFound_ShouldReturnNoProperties() {
        // Arrange

        // Act
        // Assert
        given()
            .when()
            .get("/owner/999?page=0&pageSize=10")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .body(is("[]"));
    }

    @Test
    public void GET_getProperties_WhenPropertiesAreFound_ShouldReturnProperties() {
        // Arrange

        // Act
        List<PropertyDto> result = given()
            .when()
            .get("/owner/123?page=0&pageSize=10")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .body()
            .as(new TypeRef<List<PropertyDto>>() {
            });

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("RENTAL", result.get(0).getType());
        assertEquals(100, result.get(0).getId());
        assertEquals(456, result.get(0).getAddressId());
        assertEquals(123, result.get(0).getOwnerId());
    }

    @Test
    public void GET_getProperties_WhenFailsValidation_ShouldReturnBadRequest() {
        // Arrange

        // Act
        // Assert
        given()
            .when()
            .get("/owner/123?page=0")
            .then()
            .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
