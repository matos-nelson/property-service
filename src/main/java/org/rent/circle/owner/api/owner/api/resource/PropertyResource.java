package org.rent.circle.owner.api.owner.api.resource;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import lombok.AllArgsConstructor;
import org.rent.circle.owner.api.owner.api.dto.PropertyDto;
import org.rent.circle.owner.api.owner.api.dto.SavePropertyDto;
import org.rent.circle.owner.api.owner.api.service.PropertyService;

@AllArgsConstructor
@Path("/property")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PropertyResource {

    private final PropertyService propertyService;

    @GET
    @Path("/{id}/owner/{ownerId}")
    public PropertyDto getProperty(@PathParam("id") long propertyId, long ownerId) {
        return propertyService.getProperty(propertyId, ownerId);
    }

    @GET
    @Path("/owner/{ownerId}")
    public List<PropertyDto> getProperties(long ownerId, @QueryParam("page") @NotNull @Min(0) Integer page,
        @QueryParam("pageSize") @NotNull @Min(1) Integer pageSize) {
        return propertyService.getProperties(ownerId, page, pageSize);
    }

    @POST
    public Long saveAddress(@Valid SavePropertyDto savePropertyDto) {
        return propertyService.saveProperty(savePropertyDto);
    }
}
