package org.rent.circle.owner.api.service.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.rent.circle.owner.api.owner.api.dto.PropertyDto;
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

    @Test
    public void toDto_WhenGivenNull_ShouldReturnNull() {
        // Arrange

        // Act
        PropertyDto result = propertyMapper.toDto(null);

        // Assert
        assertNull(result);
    }

    @Test
    public void toDto_WhenGivenAProperty_ShouldMap() {
        // Arrange
        Property property = new Property();
        property.setAddressId(123L);
        property.setOwnerId(456L);

        // Act
        PropertyDto result = propertyMapper.toDto(property);

        // Assert
        assertNotNull(result);
        assertEquals(property.getOwnerId(), result.getOwnerId());
        assertEquals(property.getAddressId(), result.getAddressId());
        assertEquals(property.getType(), result.getType());
    }

    @Test
    public void getProperties_WhenGivenNull_ShouldReturnNull() {
        // Arrange

        // Act
        List<PropertyDto> result = propertyMapper.getProperties(null);

        // Assert
        assertNull(result);
    }

    @Test
    public void getProperties_WhenGivenAPropertyList_ShouldMap() {
        // Arrange
        Property property = new Property();
        property.setAddressId(123L);
        property.setOwnerId(456L);

        // Act
        List<PropertyDto> result = propertyMapper.getProperties(Collections.singletonList(property));

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(property.getOwnerId(), result.get(0).getOwnerId());
        assertEquals(property.getAddressId(), result.get(0).getAddressId());
        assertEquals(property.getType(), result.get(0).getType());
    }
}
