package org.rent.circle.property.api.persistence.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.quarkus.test.junit.QuarkusTest;
import java.math.BigDecimal;
import org.force66.beantester.BeanTester;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class PropertyTest {

    @Test
    public void Property_SettersAndGetters_ShouldWork() {
        // Arrange
        BeanTester beanTester = new BeanTester();
        beanTester.addExcludedField("price");
        beanTester.addExcludedField("deposit");
        beanTester.addExcludedField("petDeposit");

        // Act
        beanTester.testBean(Property.class);

        // Assert

        // Test Excluded Fields

        // Arrange
        Property property = new Property();

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
