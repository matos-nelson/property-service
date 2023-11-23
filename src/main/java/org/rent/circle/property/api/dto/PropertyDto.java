package org.rent.circle.property.api.dto;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertyDto {

    private Long id;
    private String ownerId;
    private Long addressId;
    private Byte bed;
    private Float bath;
    private Integer sqft;
    private BigDecimal price;
    private BigDecimal deposit;
    private BigDecimal petDeposit;
    private Byte maxAllowablePets;
}
