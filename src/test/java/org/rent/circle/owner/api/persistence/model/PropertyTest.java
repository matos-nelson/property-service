package org.rent.circle.owner.api.persistence.model;

import io.quarkus.test.junit.QuarkusTest;
import org.force66.beantester.BeanTester;
import org.junit.jupiter.api.Test;
import org.rent.circle.owner.api.owner.api.persistence.model.Property;

@QuarkusTest
public class PropertyTest {

    @Test
    public void Property_SettersAndGetters_ShouldWork() {
        // Arrange
        BeanTester beanTester = new BeanTester();

        // Act
        beanTester.testBean(Property.class);

        // Assert
    }
}
