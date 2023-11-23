package org.rent.circle.property.api.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import java.math.BigDecimal;
import java.util.List;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.rent.circle.property.api.dto.PropertyDto;
import org.rent.circle.property.api.dto.SavePropertyDto;
import org.rent.circle.property.api.dto.UpdatePropertyDto;

@QuarkusTest
@TestHTTPEndpoint(PropertyResource.class)
@QuarkusTestResource(H2DatabaseTestResource.class)
public class PropertyResourceTest {

    @Test
    public void Post_WhenGivenAValidPropertyToSave_ShouldReturnSavedProperty() {
        // Arrange
        SavePropertyDto savePropertyDto = SavePropertyDto.builder()
            .addressId(1L)
            .ownerId("2")
            .bed((byte) 3)
            .bath(1.75f)
            .sqft(2000)
            .price(BigDecimal.valueOf(100))
            .petDeposit(BigDecimal.valueOf(200))
            .deposit(BigDecimal.valueOf(300))
            .maxAllowablePets((byte) 1)
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
            .ownerId("456")
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
            .body("id", is(100),
                "addressId", is(456),
                "ownerId", is("123"),
                "bed", is(4),
                "bath", is(2.25F),
                "sqft", is(1943),
                "price", is(1010.0F),
                "deposit", is(1210.0F),
                "petDeposit", is(500.0F),
                "maxAllowablePets", is(3));
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
            .as(new TypeRef<>() {
            });

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(100, result.get(0).getId());
        assertEquals("123", result.get(0).getOwnerId());
        assertEquals(456L, result.get(0).getAddressId());
        assertEquals(4, result.get(0).getBed().intValue());
        assertEquals(2.25F, result.get(0).getBath());
        assertEquals(1943, result.get(0).getSqft());
        assertEquals(1010.0F, result.get(0).getPrice().floatValue());
        assertEquals(1210.0F, result.get(0).getDeposit().floatValue());
        assertEquals(500.0F, result.get(0).getPetDeposit().floatValue());
        assertEquals((byte) 3, result.get(0).getMaxAllowablePets());
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

    @Test
    public void PATCH_WhenGivenAnInValidRequestToUpdate_ShouldReturnBadRequest() {
        // Arrange
        UpdatePropertyDto updatePropertyDto = UpdatePropertyDto.builder()
            .sqft(2000)
            .price(BigDecimal.valueOf(100))
            .petDeposit(BigDecimal.valueOf(200))
            .deposit(BigDecimal.valueOf(300))
            .maxAllowablePets((byte) 1)
            .build();

        // Act
        // Assert
        given()
            .contentType("application/json")
            .body(updatePropertyDto)
            .when()
            .patch("/200/owner/1")
            .then()
            .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void PATCH_WhenAPropertyCantBeFound_ShouldReturnNoContent() {
        // Arrange
        UpdatePropertyDto updatePropertyDto = UpdatePropertyDto.builder()
            .bed((byte) 3)
            .bath(1.75F)
            .sqft(2000)
            .price(BigDecimal.valueOf(100))
            .petDeposit(BigDecimal.valueOf(200))
            .deposit(BigDecimal.valueOf(300))
            .maxAllowablePets((byte) 1)
            .build();

        // Act
        // Assert
        given()
            .contentType("application/json")
            .body(updatePropertyDto)
            .when()
            .patch("/1000/owner/1")
            .then()
            .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void PATCH_WheGivenAValidUpdateRequest_ShouldReturnNoContent() {
        // Arrange
        UpdatePropertyDto updatePropertyDto = UpdatePropertyDto.builder()
            .bed((byte) 2)
            .bath(1.75F)
            .sqft(2000)
            .price(BigDecimal.valueOf(100))
            .petDeposit(BigDecimal.valueOf(200))
            .deposit(BigDecimal.valueOf(300))
            .maxAllowablePets((byte) 1)
            .build();

        // Act
        // Assert
        given()
            .contentType("application/json")
            .body(updatePropertyDto)
            .when()
            .patch("/200/owner/1")
            .then()
            .statusCode(HttpStatus.SC_NO_CONTENT);

        given()
            .when()
            .get("/200/owner/1")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .body("id", is(200),
                "addressId", is(2),
                "ownerId", is("1"),
                "bed", is(updatePropertyDto.getBed().intValue()),
                "bath", is(updatePropertyDto.getBath()),
                "sqft", is(updatePropertyDto.getSqft()),
                "price", is(updatePropertyDto.getPrice().floatValue()),
                "deposit", is(updatePropertyDto.getDeposit().floatValue()),
                "petDeposit", is(updatePropertyDto.getPetDeposit().floatValue()),
                "maxAllowablePets", is(updatePropertyDto.getMaxAllowablePets().intValue()));
    }
}
