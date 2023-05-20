package org.rent.circle.owner.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import jakarta.inject.Inject;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.rent.circle.owner.api.owner.api.dto.PropertyDto;
import org.rent.circle.owner.api.owner.api.dto.SavePropertyDto;
import org.rent.circle.owner.api.owner.api.persistence.model.Property;
import org.rent.circle.owner.api.owner.api.persistence.repository.PropertyRepository;
import org.rent.circle.owner.api.owner.api.service.PropertyService;
import org.rent.circle.owner.api.owner.api.service.mapper.PropertyMapper;

@QuarkusTest
public class PropertyServiceTest {

    @InjectMock
    PropertyRepository propertyRepository;

    @InjectMock
    PropertyMapper propertyMapper;

    @Inject
    PropertyService propertyService;

    @Test
    public void saveProperty_WhenCalled_ShouldReturnProperty() {
        // Arrange
        SavePropertyDto savePropertyDto = SavePropertyDto.builder()
            .ownerId(1L)
            .addressId(2L)
            .build();

        Property property = new Property();
        property.setId(123L);

        when(propertyMapper.toModel(savePropertyDto)).thenReturn(property);

        // Act
        Long result = propertyService.saveProperty(savePropertyDto);

        // Assert
        assertNotNull(result);
        assertEquals(property.getId(), result);
    }

    @Test
    public void getProperty_WhenCalled_ShouldReturnProperty() {
        // Arrange
        Long propertyId = 123L;
        Long ownerId = 456L;

        Property property = new Property();
        property.setId(propertyId);
        property.setOwnerId(ownerId);

        PropertyDto propertyDto = new PropertyDto();
        propertyDto.setOwnerId(ownerId);
        propertyDto.setAddressId(888L);

        when(propertyRepository.findByIdAndOwnerId(propertyId, ownerId)).thenReturn(property);
        when(propertyMapper.toDto(property)).thenReturn(propertyDto);

        // Act
        PropertyDto result = propertyService.getProperty(propertyId, ownerId);

        // Assert
        assertNotNull(result);
        assertEquals(propertyDto.getType(), result.getType());
        assertEquals(propertyDto.getOwnerId(), result.getOwnerId());
        assertEquals(propertyDto.getAddressId(), result.getAddressId());
    }

    @Test
    public void getProperties_WhenCalled_ShouldReturnProperties() {
        // Arrange
        int page = 0;
        int pageSize = 10;
        Long ownerId = 456L;

        Property property = new Property();
        property.setId(123L);
        property.setOwnerId(ownerId);

        PropertyDto propertyDto = new PropertyDto();
        propertyDto.setOwnerId(ownerId);
        propertyDto.setAddressId(888L);

        when(propertyRepository.getOwnerProperties(ownerId, page, pageSize)).thenReturn(
            Collections.singletonList(property));
        when(propertyMapper.getProperties(Collections.singletonList(property))).thenReturn(
            Collections.singletonList(propertyDto));

        // Act
        List<PropertyDto> result = propertyService.getProperties(ownerId, page, pageSize);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(propertyDto.getType(), result.get(0).getType());
        assertEquals(propertyDto.getOwnerId(), result.get(0).getOwnerId());
        assertEquals(propertyDto.getAddressId(), result.get(0).getAddressId());
    }
}
