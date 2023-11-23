package org.rent.circle.property.api.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rent.circle.property.api.dto.PropertyDto;
import org.rent.circle.property.api.dto.SavePropertyDto;
import org.rent.circle.property.api.dto.UpdatePropertyDto;
import org.rent.circle.property.api.persistence.model.Property;
import org.rent.circle.property.api.persistence.repository.PropertyRepository;
import org.rent.circle.property.api.service.mapper.PropertyMapper;

@AllArgsConstructor
@ApplicationScoped
@Slf4j
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final PropertyMapper propertyMapper;

    public PropertyDto getProperty(long propertyId, String ownerId) {

        Property property = propertyRepository.findByIdAndOwnerId(propertyId, ownerId);
        return propertyMapper.toDto(property);
    }

    public List<PropertyDto> getProperties(String ownerId, int page, int pageSize) {

        List<Property> properties = propertyRepository.getOwnerProperties(ownerId, page, pageSize);
        return propertyMapper.getProperties(properties);
    }

    @Transactional
    public Long saveProperty(SavePropertyDto savePropertyDto) {

        Property property = propertyMapper.toModel(savePropertyDto);
        propertyRepository.persist(property);

        return property.getId();
    }

    @Transactional
    public void updateProperty(long propertyId, String ownerId, UpdatePropertyDto updatePropertyInfo) {

        Property property = propertyRepository.findByIdAndOwnerId(propertyId, ownerId);
        if (property == null) {
            return;
        }

        propertyMapper.updateModel(updatePropertyInfo, property);
        propertyRepository.persist(property);
    }
}
