package org.rent.circle.owner.api.service.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.rent.circle.owner.api.owner.api.dto.SavePropertyDto;
import org.rent.circle.owner.api.owner.api.persistence.model.Property;
import org.rent.circle.owner.api.owner.api.service.mapper.PropertyMapper;

@QuarkusTest
public class PropertyMapperTest {

    @Inject
    PropertyMapper propertyMapper;

    @Test
    public void toModel_WhenGivenNull_ShouldReturnNull() {
        // Arrange

        // Act
        Property result = propertyMapper.toModel(null);

        // Assert
        assertNull(result);
    }

    @Test
    public void toModel_WhenGivenASavePropertyDto_ShouldMap() {
        // Arrange
        SavePropertyDto savePropertyDto = SavePropertyDto.builder()
            .addressId(1L)
            .ownerId(2L)
            .build();

        // Act
        Property result = propertyMapper.toModel(savePropertyDto);

        // Assert
        assertNotNull(result);
        assertEquals(savePropertyDto.getAddressId(), result.getAddressId());
        assertEquals(savePropertyDto.getOwnerId(), result.getOwnerId());
    }
}
