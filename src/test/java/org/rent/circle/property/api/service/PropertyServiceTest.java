package org.rent.circle.property.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import jakarta.inject.Inject;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.rent.circle.property.api.dto.PropertyDto;
import org.rent.circle.property.api.dto.SavePropertyDto;
import org.rent.circle.property.api.dto.UpdatePropertyDto;
import org.rent.circle.property.api.persistence.model.Property;
import org.rent.circle.property.api.persistence.repository.PropertyRepository;
import org.rent.circle.property.api.service.mapper.PropertyMapper;

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
        assertEquals(propertyDto.getOwnerId(), result.get(0).getOwnerId());
        assertEquals(propertyDto.getAddressId(), result.get(0).getAddressId());
    }

    @Test
    public void updateProperty_WhenPropertyIsNotFound_ShouldReturnNotUpdate() {
        // Arrange
        Long propertyId = 1L;
        UpdatePropertyDto updatePropertyInfo = UpdatePropertyDto.builder().build();
        when(propertyRepository.findById(propertyId)).thenReturn(null);

        // Act
        propertyService.updateProperty(propertyId, updatePropertyInfo);

        // Assert
        verify(propertyMapper, never()).updateModel(updatePropertyInfo, null);
        verify(propertyRepository, never()).persist(Mockito.any(Property.class));
    }

    @Test
    public void updateProperty_WhenCalled_ShouldUpdate() {
        // Arrange
        Long propertyId = 1L;

        Property property = new Property();
        property.setId(propertyId);

        UpdatePropertyDto updatePropertyInfo = UpdatePropertyDto.builder()
            .bed((byte) 3)
            .bath(1.75F)
            .sqft(2000)
            .price(BigDecimal.valueOf(100))
            .petDeposit(BigDecimal.valueOf(200))
            .deposit(BigDecimal.valueOf(300))
            .maxAllowablePets((byte) 1)
            .build();
        when(propertyRepository.findById(propertyId)).thenReturn(property);

        // Act
        propertyService.updateProperty(propertyId, updatePropertyInfo);

        // Assert
        verify(propertyMapper, times(1)).updateModel(updatePropertyInfo, property);
        verify(propertyRepository, times(1)).persist(property);
    }
}
