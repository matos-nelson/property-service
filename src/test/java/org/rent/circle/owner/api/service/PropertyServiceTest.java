package org.rent.circle.owner.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
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
}
