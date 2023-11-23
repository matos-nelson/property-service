package org.rent.circle.property.api.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.rent.circle.property.api.persistence.BaseModel;

@Entity
@Table(name = "property")
@Setter
@Getter
@ToString
public class Property extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "owner_id")
    private String ownerId;

    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "bed")
    private Byte bed;

    @Column(name = "bath")
    private Float bath;

    @Column(name = "sqft")
    private Integer sqft;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "deposit")
    private BigDecimal deposit;

    @Column(name = "pet_deposit")
    private BigDecimal petDeposit;

    @Column(name = "max_allowable_pets")
    private Byte maxAllowablePets;
}
