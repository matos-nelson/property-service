package org.rent.circle.owner.api.owner.api.service.mapper;

import org.mapstruct.Mapper;
import org.rent.circle.owner.api.owner.api.dto.PropertyDto;
import org.rent.circle.owner.api.owner.api.dto.SavePropertyDto;
import org.rent.circle.owner.api.owner.api.persistence.model.Property;

@Mapper(componentModel = "cdi")
public interface PropertyMapper {

    Property toModel(SavePropertyDto savePropertyDto);

    PropertyDto toDto(Property property);
}
