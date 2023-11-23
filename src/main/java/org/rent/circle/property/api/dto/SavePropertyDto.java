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
    private String ownerId;

    @NotNull
    private Long addressId;

    @NotNull
    private Byte bed;

    @NotNull
    private Float bath;

    @NotNull
    private Integer sqft;

    @NotNull
    private BigDecimal price;

    @NotNull
    private BigDecimal deposit;

    @NotNull
    private BigDecimal petDeposit;

    @NotNull
    private Byte maxAllowablePets;
}
