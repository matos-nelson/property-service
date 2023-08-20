package org.rent.circle.property.api.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.quarkus.test.junit.QuarkusTest;
import java.math.BigDecimal;
import org.force66.beantester.BeanTester;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class UpdatePropertyDtoTest {

    @Test
    public void UpdatePropertyDto_SettersAndGetters_ShouldWork() {
        // Arrange
        BeanTester beanTester = new BeanTester();
        beanTester.addExcludedField("price");
        beanTester.addExcludedField("deposit");
        beanTester.addExcludedField("petDeposit");

        // Act
        beanTester.testBean(UpdatePropertyDto.class);

        // Assert

        // Test Excluded Fields

        // Arrange
        UpdatePropertyDto updatePropertyDto = new UpdatePropertyDto();

        // Act
        updatePropertyDto.setPrice(BigDecimal.valueOf(1));
        updatePropertyDto.setDeposit(BigDecimal.valueOf(2));
        updatePropertyDto.setPetDeposit(BigDecimal.valueOf(3));

        // Assert
        assertEquals(BigDecimal.valueOf(1), updatePropertyDto.getPrice());
        assertEquals(BigDecimal.valueOf(2), updatePropertyDto.getDeposit());
        assertEquals(BigDecimal.valueOf(3), updatePropertyDto.getPetDeposit());
    }
}
