package org.rent.circle.owner.api.service.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.rent.circle.owner.api.dto.PropertyDto;
import org.rent.circle.owner.api.dto.SavePropertyDto;
import org.rent.circle.owner.api.persistence.model.Property;

@Mapper(componentModel = "cdi")
public interface PropertyMapper {

    Property toModel(SavePropertyDto savePropertyDto);

    PropertyDto toDto(Property property);

    List<PropertyDto> getProperties(List<Property> properties);
}
