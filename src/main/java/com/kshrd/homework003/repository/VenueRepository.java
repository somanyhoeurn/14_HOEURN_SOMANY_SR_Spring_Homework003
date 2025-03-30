package com.kshrd.homework003.repository;

import com.kshrd.homework003.model.entity.Venue;
import com.kshrd.homework003.model.request.VenueRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VenueRepository {

    @Results(id = "venueMapper",value = {
            @Result(property = "venueId",column = "venue_id"),
            @Result(property = "venueName",column = "venue_name")
    })
    @Select("""
                SELECT * FROM venues OFFSET #{offset} LIMIT #{size} ;
            """)
    List<Venue> getAllVenues(Long offset, Long size);

    @ResultMap("venueMapper")
    @Select("""
                SELECT * FROM venues WHERE venue_id = #{venueId}
            """)
    Venue getVenueById(Long venueId);

    @ResultMap("venueMapper")
    @Select("""
                INSERT INTO venues VALUES (DEFAULT, #{req.venueName}, #{req.location})
                RETURNING *;
            """)
    Venue addVenue(@Param("req") VenueRequest request);

    @ResultMap("venueMapper")
    @Delete("""
                DELETE FROM venues WHERE venue_id = #{venueId}
            """)
    int deleteVenue(Long venueId);

    @ResultMap("venueMapper")
    @Select("""
                UPDATE venues SET venue_Name = #{req.venueName}, location = #{req.location} WHERE venue_id = #{venueId}
                RETURNING *;
            """)
    Venue updateVenue(Long venueId,@Param("req") VenueRequest request);
}
