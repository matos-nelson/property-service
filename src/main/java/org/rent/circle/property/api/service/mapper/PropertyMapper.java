package org.rent.circle.property.api.service.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.rent.circle.property.api.dto.PropertyDto;
import org.rent.circle.property.api.dto.SavePropertyDto;
import org.rent.circle.property.api.dto.UpdatePropertyDto;
import org.rent.circle.property.api.persistence.model.Property;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "cdi")
public interface PropertyMapper {

    Property toModel(SavePropertyDto savePropertyDto);

    PropertyDto toDto(Property property);

    List<PropertyDto> getProperties(List<Property> properties);

    Property updateModel(UpdatePropertyDto updateProperty, @MappingTarget Property property);
}
