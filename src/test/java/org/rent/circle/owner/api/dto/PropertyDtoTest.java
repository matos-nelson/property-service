package org.rent.circle.owner.api.dto;

import io.quarkus.test.junit.QuarkusTest;
import org.force66.beantester.BeanTester;
import org.junit.jupiter.api.Test;
import org.rent.circle.owner.api.owner.api.dto.PropertyDto;

@QuarkusTest
public class PropertyDtoTest {

    @Test
    public void PropertyDtoDto_SettersAndGetters_ShouldWork() {
        // Arrange
        BeanTester beanTester = new BeanTester();

        // Act
        beanTester.testBean(PropertyDto.class);

        // Assert
    }
}
