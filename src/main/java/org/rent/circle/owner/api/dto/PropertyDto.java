package org.rent.circle.owner.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertyDto {

    private Long id;
    private String type;
    private Long ownerId;
    private Long addressId;
}
