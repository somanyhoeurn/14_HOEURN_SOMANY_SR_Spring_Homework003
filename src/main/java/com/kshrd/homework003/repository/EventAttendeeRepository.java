package com.kshrd.homework003.repository;

import com.kshrd.homework003.model.response.AttendeeResponse;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EventAttendeeRepository {

    @Results(id = "eventAttendeeMapper", value = {
            @Result(property = "attendeeId" , column = "attendee_id"),
            @Result(property = "attendeeName" , column = "attendee_name")
    })
    @Select("""
                SELECT * FROM events_attendees ea
                JOIN attendees a ON ea.attendee_id = a.attendee_id
                WHERE event_id = #{eventId}
            """)
    List<AttendeeResponse> getAllAttendeeByEventId(Long eventId);

    @ResultMap("eventAttendeeMapper")
    @Insert("""
                INSERT INTO events_attendees VALUES (DEFAULT, #{eventId}, #{attendeeId})
                RETURNING *;
            """)
    void insertAttendeeIdAndEventId(@Param("eventId") Long eventId,@Param("attendeeId") Long attendeeId);

    @ResultMap("eventAttendeeMapper")
    @Delete("""
                DELETE FROM events_attendees WHERE event_id = #{eventId}
            """)
    void deleteAttendeeIdAndEventId(Long eventId);
}
