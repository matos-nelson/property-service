package org.rent.circle.owner.api.owner.api.persistence.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import org.rent.circle.owner.api.owner.api.persistence.model.Property;

@ApplicationScoped
public class PropertyRepository implements PanacheRepository<Property> {

    public Property findByIdAndOwnerId(Long id, Long ownerId) {
        Parameters queryParams = Parameters.with("id", id).and("ownerId", ownerId);
        return find("id = :id and ownerId = :ownerId", queryParams).firstResult();
    }
}
