package org.rent.circle.owner.api.dto;

import jakarta.validation.constraints.NotNull;
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
}
