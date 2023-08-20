package org.rent.circle.property.api.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.quarkus.test.junit.QuarkusTest;
import java.math.BigDecimal;
import org.force66.beantester.BeanTester;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class SavePropertyDtoTest {

    @Test
    public void SavePropertyDto_SettersAndGetters_ShouldWork() {
        // Arrange
        BeanTester beanTester = new BeanTester();
        beanTester.addExcludedField("price");
        beanTester.addExcludedField("deposit");
        beanTester.addExcludedField("petDeposit");

        // Act
        beanTester.testBean(SavePropertyDto.class);

        // Assert

        // Test Excluded Fields

        // Arrange
        SavePropertyDto savePropertyDto = new SavePropertyDto();

        // Act
        savePropertyDto.setPrice(BigDecimal.valueOf(1));
        savePropertyDto.setDeposit(BigDecimal.valueOf(2));
        savePropertyDto.setPetDeposit(BigDecimal.valueOf(3));

        // Assert
        assertEquals(BigDecimal.valueOf(1), savePropertyDto.getPrice());
        assertEquals(BigDecimal.valueOf(2), savePropertyDto.getDeposit());
        assertEquals(BigDecimal.valueOf(3), savePropertyDto.getPetDeposit());
    }
}
