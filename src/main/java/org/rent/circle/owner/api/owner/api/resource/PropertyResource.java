package org.rent.circle.owner.api.owner.api.resource;

import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;
import org.rent.circle.owner.api.owner.api.dto.SavePropertyDto;
import org.rent.circle.owner.api.owner.api.service.PropertyService;

@AllArgsConstructor
@Path("/property")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PropertyResource {

    private final PropertyService propertyService;

    @POST
    public Long saveAddress(@Valid SavePropertyDto savePropertyDto) {
        return propertyService.saveProperty(savePropertyDto);
    }
}
