package org.rent.circle.property.api.persistence.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import org.rent.circle.property.api.persistence.model.Property;

@ApplicationScoped
public class PropertyRepository implements PanacheRepository<Property> {

    public Property findByIdAndOwnerId(Long id, String ownerId) {
        Parameters queryParams = Parameters.with("id", id).and("ownerId", ownerId);
        return find("id = :id and ownerId = :ownerId", queryParams).firstResult();
    }

    public List<Property> getOwnerProperties(String ownerId, int page, int pageSize) {
        return find("ownerId", ownerId)
            .page(Page.of(page, pageSize))
            .list();
    }
}
