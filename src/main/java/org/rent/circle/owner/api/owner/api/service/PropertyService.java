package org.rent.circle.owner.api.owner.api.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rent.circle.owner.api.owner.api.dto.SavePropertyDto;
import org.rent.circle.owner.api.owner.api.persistence.model.Property;
import org.rent.circle.owner.api.owner.api.persistence.repository.PropertyRepository;
import org.rent.circle.owner.api.owner.api.service.mapper.PropertyMapper;

@AllArgsConstructor
@ApplicationScoped
@Slf4j
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final PropertyMapper propertyMapper;

    @Transactional
    public Long saveProperty(SavePropertyDto savePropertyDto) {

        Property property = propertyMapper.toModel(savePropertyDto);
        propertyRepository.persist(property);

        return property.getId();
    }
}
