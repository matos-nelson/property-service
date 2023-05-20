package org.rent.circle.owner.api.owner.api.dto;

import lombok.Data;

@Data
public class PropertyDto {

    private String type;
    private Long ownerId;
    private Long addressId;
}
