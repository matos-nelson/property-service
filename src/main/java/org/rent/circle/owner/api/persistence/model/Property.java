package org.rent.circle.owner.api.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.rent.circle.owner.api.persistence.BaseModel;

@Entity
@Table(name = "property")
@Setter
@Getter
@ToString
public class Property extends BaseModel {

    public Property() {
        super();
        this.type = PropertyType.RENTAL.value;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "owner_id")
    private Long ownerId;

    @Column(name = "address_id")
    private Long addressId;
}

enum PropertyType {
    RENTAL("RENTAL");

    public final String value;

    PropertyType(String value) {
        this.value = value;
    }
}
