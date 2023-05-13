package org.rent.circle.owner.api.dto;

import io.quarkus.test.junit.QuarkusTest;
import org.force66.beantester.BeanTester;
import org.junit.jupiter.api.Test;
import org.rent.circle.owner.api.owner.api.dto.SavePropertyDto;

@QuarkusTest
public class SavePropertyDtoTest {

    @Test
    public void SavePropertyDto_SettersAndGetters_ShouldWork() {
        // Arrange
        BeanTester beanTester = new BeanTester();

        // Act
        beanTester.testBean(SavePropertyDto.class);

        // Assert
    }
}
