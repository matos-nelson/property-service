package org.rent.circle.owner.api.persistence.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.rent.circle.owner.api.owner.api.persistence.model.Property;
import org.rent.circle.owner.api.owner.api.persistence.repository.PropertyRepository;

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
        Property result = propertyRepository.findByIdAndOwnerId(123L, 456L);

        // Assert
        assertNull(result);
    }

    @Test
    @TestTransaction
    public void findByZipAndCity_WhenCalled_ShouldReturnZip() {
        // Arrange
        Long id = 100L;
        Long ownerId = 123L;

        // Act
        Property result = propertyRepository.findByIdAndOwnerId(100L, 123L);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals(ownerId, result.getOwnerId());
    }
}
