package com.kshrd.homework003.repository;

import com.kshrd.homework003.model.entity.Attendee;
import com.kshrd.homework003.model.request.AttendeeRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AttendeeRepository {

    @Results(id="attendeeMapper",value = {
            @Result(property = "attendeeId" , column = "attendee_id"),
            @Result(property = "attendeeName" , column = "attendee_name")
    })
    @Select("""
                SELECT * FROM attendees OFFSET #{offset} LIMIT #{limit};
            """)
    List<Attendee> getAllAttendees(Integer offset, Integer limit);

    @ResultMap("attendeeMapper")
    @Select("""
                SELECT * FROM attendees WHERE attendee_id = #{attendeeId};
            """)
    Attendee getAttendeeById(Long attendeeId);

    @ResultMap("attendeeMapper")
    @Select("""
                INSERT INTO attendees VALUES (DEFAULT, #{req.attendeeName}, #{req.email})
                RETURNING *;
            """)
    Attendee addAttendee(@Param("req") AttendeeRequest request);

    @ResultMap("attendeeMapper")
    @Delete("""
                DELETE FROM attendees WHERE attendee_id = #{attendeeId};
            """)
    int deleteAttendeeById(Long attendeeId);

    @ResultMap("attendeeMapper")
    @Select("""
                UPDATE attendees SET attendee_name = #{req.attendeeName}, email = #{req.email} WHERE attendee_id = #{attendeeId}
                RETURNING *;
            """)
    Attendee updateAttendeeById(Long attendeeId, @Param("req") AttendeeRequest request);
}
