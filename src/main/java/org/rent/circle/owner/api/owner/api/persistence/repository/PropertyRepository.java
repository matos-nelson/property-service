package org.rent.circle.owner.api.owner.api.persistence.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.rent.circle.owner.api.owner.api.persistence.model.Property;

@ApplicationScoped
public class PropertyRepository implements PanacheRepository<Property> {

}
