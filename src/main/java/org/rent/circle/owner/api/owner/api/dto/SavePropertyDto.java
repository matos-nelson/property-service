package org.rent.circle.owner.api.owner.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SavePropertyDto {

    @NotNull
    private Long ownerId;

    @NotNull
    private Long addressId;
}
