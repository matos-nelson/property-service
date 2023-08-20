package org.rent.circle.property.api.service.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.rent.circle.property.api.dto.PropertyDto;
import org.rent.circle.property.api.dto.SavePropertyDto;
import org.rent.circle.property.api.dto.UpdatePropertyDto;
import org.rent.circle.property.api.persistence.model.Property;

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
            .bed((byte) 3)
            .bath(1.75F)
            .sqft(2000)
            .price(BigDecimal.valueOf(100))
            .petDeposit(BigDecimal.valueOf(200))
            .deposit(BigDecimal.valueOf(300))
            .maxAllowablePets((byte) 1)
            .build();

        // Act
        Property result = propertyMapper.toModel(savePropertyDto);

        // Assert
        assertNotNull(result);
        assertEquals(savePropertyDto.getAddressId(), result.getAddressId());
        assertEquals(savePropertyDto.getOwnerId(), result.getOwnerId());
        assertEquals(savePropertyDto.getBed(), result.getBed());
        assertEquals(savePropertyDto.getBath(), result.getBath());
        assertEquals(savePropertyDto.getSqft(), result.getSqft());
        assertEquals(savePropertyDto.getPrice(), result.getPrice());
        assertEquals(savePropertyDto.getPetDeposit(), result.getPetDeposit());
        assertEquals(savePropertyDto.getDeposit(), result.getDeposit());
        assertEquals(savePropertyDto.getMaxAllowablePets(), result.getMaxAllowablePets());
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
        property.setBed((byte) 3);
        property.setBath(1.75F);
        property.setSqft(2000);
        property.setPrice(BigDecimal.valueOf(100));
        property.setPetDeposit(BigDecimal.valueOf(200));
        property.setDeposit(BigDecimal.valueOf(300));
        property.setMaxAllowablePets((byte) 1);

        // Act
        PropertyDto result = propertyMapper.toDto(property);

        // Assert
        assertNotNull(result);
        assertEquals(property.getOwnerId(), result.getOwnerId());
        assertEquals(property.getAddressId(), result.getAddressId());
        assertEquals(property.getBed(), result.getBed());
        assertEquals(property.getBath(), result.getBath());
        assertEquals(property.getSqft(), result.getSqft());
        assertEquals(property.getPrice(), result.getPrice());
        assertEquals(property.getPetDeposit(), result.getPetDeposit());
        assertEquals(property.getDeposit(), result.getDeposit());
        assertEquals(property.getMaxAllowablePets(), result.getMaxAllowablePets());
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
        property.setBed((byte) 3);
        property.setBath(1.75F);
        property.setSqft(2000);
        property.setPrice(BigDecimal.valueOf(100));
        property.setPetDeposit(BigDecimal.valueOf(200));
        property.setDeposit(BigDecimal.valueOf(300));
        property.setMaxAllowablePets((byte) 1);

        // Act
        List<PropertyDto> result = propertyMapper.getProperties(Collections.singletonList(property));

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(property.getOwnerId(), result.get(0).getOwnerId());
        assertEquals(property.getAddressId(), result.get(0).getAddressId());
        assertEquals(property.getBed(), result.get(0).getBed());
        assertEquals(property.getBath(), result.get(0).getBath());
        assertEquals(property.getSqft(), result.get(0).getSqft());
        assertEquals(property.getPrice(), result.get(0).getPrice());
        assertEquals(property.getPetDeposit(), result.get(0).getPetDeposit());
        assertEquals(property.getDeposit(), result.get(0).getDeposit());
        assertEquals(property.getMaxAllowablePets(), result.get(0).getMaxAllowablePets());
    }

    @Test
    public void updateModel_WhenGivenUpdatePropertyIsNull_ShouldNotUpdate() {
        // Arrange
        Property property = new Property();
        property.setAddressId(123L);
        property.setOwnerId(456L);
        property.setBed((byte) 3);
        property.setBath(1.75F);
        property.setSqft(2000);
        property.setPrice(BigDecimal.valueOf(100));
        property.setPetDeposit(BigDecimal.valueOf(200));
        property.setDeposit(BigDecimal.valueOf(300));
        property.setMaxAllowablePets((byte) 1);

        // Act
        propertyMapper.updateModel(null, property);

        // Assert
        assertNotNull(property);
        assertNotNull(property.getAddressId());
        assertNotNull(property.getOwnerId());
        assertNotNull(property.getBed());
        assertNotNull(property.getBath());
        assertNotNull(property.getSqft());
        assertNotNull(property.getPrice());
        assertNotNull(property.getPetDeposit());
        assertNotNull(property.getDeposit());
        assertNotNull(property.getMaxAllowablePets());
    }

    @Test
    public void updateModel_WhenGivenAnUpdatePropertyDto_ShouldUpdate() {
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

        Property property = new Property();

        // Act
        propertyMapper.updateModel(updatePropertyDto, property);

        // Assert
        assertEquals(updatePropertyDto.getBed(), property.getBed());
        assertEquals(updatePropertyDto.getBath(), property.getBath());
        assertEquals(updatePropertyDto.getSqft(), property.getSqft());
        assertEquals(updatePropertyDto.getPrice(), property.getPrice());
        assertEquals(updatePropertyDto.getPetDeposit(), property.getPetDeposit());
        assertEquals(updatePropertyDto.getDeposit(), property.getDeposit());
        assertEquals(updatePropertyDto.getMaxAllowablePets(), property.getMaxAllowablePets());
    }
}
