package org.rent.circle.property.api.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.quarkus.test.junit.QuarkusTest;
import java.math.BigDecimal;
import org.force66.beantester.BeanTester;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class PropertyDtoTest {

    @Test
    public void PropertyDto_SettersAndGetters_ShouldWork() {
        // Arrange
        BeanTester beanTester = new BeanTester();
        beanTester.addExcludedField("price");
        beanTester.addExcludedField("deposit");
        beanTester.addExcludedField("petDeposit");

        // Act
        beanTester.testBean(PropertyDto.class);

        // Assert

        // Test Excluded Fields

        // Arrange
        PropertyDto property = new PropertyDto();

        // Act
        property.setPrice(BigDecimal.valueOf(1));
        property.setDeposit(BigDecimal.valueOf(2));
        property.setPetDeposit(BigDecimal.valueOf(3));

        // Assert
        assertEquals(BigDecimal.valueOf(1), property.getPrice());
        assertEquals(BigDecimal.valueOf(2), property.getDeposit());
        assertEquals(BigDecimal.valueOf(3), property.getPetDeposit());
    }
}
