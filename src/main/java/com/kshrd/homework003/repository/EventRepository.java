package com.kshrd.homework003.repository;

import com.kshrd.homework003.model.request.EventRequest;
import org.apache.ibatis.annotations.*;
import com.kshrd.homework003.model.entity.Event;

import java.util.List;

@Mapper
public interface EventRepository {

    @Results(id = "eventMapper", value = {
            @Result(property = "eventId", column = "event_id"),
            @Result(property = "eventName", column = "event_Name"),
            @Result(property = "eventDate", column = "event_date"),
            @Result(property = "venues", column = "venue_id",
                one = @One(select = "com.kshrd.homework003.repository.VenueRepository.getVenueById")),
            @Result(property = "attendees", column = "event_id",
                many = @Many(select = "com.kshrd.homework003.repository.EventAttendeeRepository.getAllAttendeeByEventId"))

    })
    @Select("""
                SELECT * FROM events OFFSET #{offset} LIMIT #{limit};
            """)
    List<Event> getAllEvents(Integer offset, Integer limit);

    @ResultMap("eventMapper")
    @Select("""
                SELECT * FROM events WHERE event_id = #{eventId};
            """)
    Event getEventById(Long eventId);

    @ResultMap("eventMapper")
    @Select("""
                INSERT INTO events VALUES (DEFAULT, #{req.eventName}, #{req.eventDate}, #{req.venuesId})
                RETURNING *;
            """)
    Event addEvent(@Param("req") EventRequest eventRequest);


    @Delete("""
                DELETE FROM events WHERE event_id = #{eventId};
            """)
    int deleteEventById(Long eventId);

    @ResultMap("eventMapper")
    @Select("""
                UPDATE events SET event_name = #{req.eventName}, 
                                  event_date = #{req.eventDate}, 
                                  venue_id = #{req.venuesId} 
                              WHERE event_id = #{eventId}
                RETURNING *;
            """)
    Event updateEventById(Long eventId,@Param("req") EventRequest eventRequest);
}
