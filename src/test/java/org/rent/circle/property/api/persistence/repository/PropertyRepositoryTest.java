package org.rent.circle.property.api.persistence.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.rent.circle.property.api.persistence.model.Property;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class PropertyRepositoryTest {

    @Inject
    PropertyRepository propertyRepository;

    @Test
    @TestTransaction
    public void findByIdAndOwnerId_WhenPropertyDoesNotExist_ShouldReturnNull() {
        // Arrange

        // Act
        Property result = propertyRepository.findByIdAndOwnerId(123L, "456");

        // Assert
        assertNull(result);
    }

    @Test
    @TestTransaction
    public void findByIdAndOwnerId_WhenCalled_ShouldReturnProperty() {
        // Arrange
        Long id = 100L;
        String ownerId = "123";

        // Act
        Property result = propertyRepository.findByIdAndOwnerId(id, "123");

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals(ownerId, result.getOwnerId());
    }

    @Test
    @TestTransaction
    public void getOwnerProperties_WhenPropertiesDoNotExist_ShouldReturnNoProperties() {
        // Arrange

        // Act
        List<Property> result = propertyRepository.getOwnerProperties("456", 1, 10);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    @TestTransaction
    public void getOwnerProperties_WhenPropertiesDoExist_ShouldReturnProperties() {
        // Arrange

        // Act
        List<Property> result = propertyRepository.getOwnerProperties("123", 0, 10);

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    @TestTransaction
    public void getOwnerProperties_WhenPropertiesDoNotExistInPage_ShouldReturnNoProperties() {
        // Arrange

        // Act
        List<Property> result = propertyRepository.getOwnerProperties("123", 10, 10);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
