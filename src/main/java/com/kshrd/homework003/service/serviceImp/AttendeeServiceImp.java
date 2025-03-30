package com.kshrd.homework003.service.serviceImp;

import com.kshrd.homework003.exception.NotFoundException;
import com.kshrd.homework003.model.entity.Attendee;
import com.kshrd.homework003.model.mapper.AttendeeMapper;
import com.kshrd.homework003.model.request.AttendeeRequest;
import com.kshrd.homework003.model.response.AttendeeResponse;
import com.kshrd.homework003.repository.AttendeeRepository;
import com.kshrd.homework003.service.AttendeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendeeServiceImp implements AttendeeService {
    private final AttendeeMapper attendeeMapper;
    private final AttendeeRepository attendeeRepository;


    @Override
    public List<AttendeeResponse> getAllAttendees(Integer offset, Integer limit) {
        offset = (offset - 1) * limit;
        return attendeeRepository
                .getAllAttendees(offset,limit)
                .stream()
                .map(attendeeMapper::toAttendeeResponse)
                .toList();
    }

    @Override
    public AttendeeResponse getAttendeeById(Long attendeeId) {
        Attendee attendee = attendeeRepository.getAttendeeById(attendeeId);
        if (attendee == null) {
            throw new NotFoundException("The attendee with id " + attendeeId + " has not been founded");
        }
        return attendeeMapper.toAttendeeResponse(attendee);
    }

    @Override
    public AttendeeResponse addAttendee(AttendeeRequest request) {
        Attendee attendee = attendeeRepository.addAttendee(request);
        if (attendee == null) {
            throw new NotFoundException("Failed to add attendee");
        }
        return attendeeMapper.toAttendeeResponse(attendee);
    }

    @Override
    public void deleteAttendeeById(Long attendeeId) {
        int rowEffect = attendeeRepository.deleteAttendeeById(attendeeId);
        if (rowEffect == 0) {
            throw new NotFoundException("The attendee with id " + attendeeId + " has not been founded");
        }
    }

    @Override
    public AttendeeResponse updateAttendeeById(Long attendeeId, AttendeeRequest request) {
        Attendee attendee = attendeeRepository.getAttendeeById(attendeeId);
        if (attendee == null) {
            throw new NotFoundException("The attendee with id " + attendeeId + " has not been founded");
        }
        Attendee updateAttendee = attendeeRepository.updateAttendeeById(attendeeId, request);
        if (updateAttendee == null) {
            throw new NotFoundException("Failed to update attendee");
        }
        return attendeeMapper.toAttendeeResponse(updateAttendee);
    }
}
