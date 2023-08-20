package org.rent.circle.property.api.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SavePropertyDto {

    @NotNull
    private Long ownerId;

    @NotNull
    private Long addressId;

    private Byte bed;

    private Float bath;

    private Integer sqft;

    private BigDecimal price;

    private BigDecimal deposit;

    private BigDecimal petDeposit;

    private Byte maxAllowablePets;
}
