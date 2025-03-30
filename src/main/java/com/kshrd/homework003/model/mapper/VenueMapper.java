package com.kshrd.homework003.model.mapper;

import com.kshrd.homework003.model.entity.Venue;
import com.kshrd.homework003.model.response.VenueResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VenueMapper {
    VenueResponse toVenueResponse(Venue venue);
}
