package com.kshrd.homework003.service.serviceImp;

import com.kshrd.homework003.exception.NotFoundException;
import com.kshrd.homework003.model.entity.Event;
import com.kshrd.homework003.model.mapper.EventMapper;
import com.kshrd.homework003.model.request.EventRequest;
import com.kshrd.homework003.model.response.EventResponse;
import com.kshrd.homework003.repository.EventAttendeeRepository;
import com.kshrd.homework003.repository.EventRepository;
import com.kshrd.homework003.service.AttendeeService;
import com.kshrd.homework003.service.EventService;
import com.kshrd.homework003.service.VenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImp implements EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final AttendeeService attendeeService;
    private final VenueService venueService;
    private final EventAttendeeRepository eventAttendeeRepository;

    @Override
    public List<EventResponse> getAllEvents(Integer offset, Integer limit) {
        offset = (offset - 1) * limit;
        return eventRepository
                .getAllEvents(offset, limit)
                .stream()
                .map(eventMapper::toEventResponse)
                .toList();
    }

    @Override
    public EventResponse getEventById(Long eventId) {
        Event event = eventRepository.getEventById(eventId);
        if (event == null) {
            throw new NotFoundException("Event with id " + eventId + " not found");
        }
        return eventMapper.toEventResponse(event);
    }

    @Override
    public EventResponse addEvent(EventRequest request) {
        if (venueService.getVenueById(request.getVenuesId()) == null) {
            throw new NotFoundException("Venue with id " + request.getVenuesId() + " not found");
        }
        request.getAttendeesId().forEach(attendeesId -> {
            if(attendeeService.getAttendeeById(attendeesId) == null) {
                throw new NotFoundException("Attendee with id " + attendeesId + " not found");
            }
        });

        Event event = eventRepository.addEvent(request);
        request.getAttendeesId().forEach(attendeesId ->
                eventAttendeeRepository.insertAttendeeIdAndEventId(event.getEventId(), attendeesId));
        if (event == null) {
            throw new NotFoundException("Failed to add event");
        }
        Event fullEvent = eventRepository.getEventById(event.getEventId());
        return eventMapper.toEventResponse(fullEvent);
    }

    @Override
    public void deleteEventById(Long eventId) {
        int rowEffect = eventRepository.deleteEventById(eventId);
        if (rowEffect == 0) {
            throw new NotFoundException("Event with id " + eventId + " not found");
        }
    }

    @Override
    public EventResponse updateEventById(Long eventId, EventRequest eventRequest) {
        Event isUpdated = eventRepository.updateEventById(eventId,eventRequest);
        if (isUpdated == null) {
            throw new NotFoundException("Event with id " + eventId + " not found");
        }
        eventRequest.getAttendeesId().forEach(attendeesId -> {
            if(attendeeService.getAttendeeById(attendeesId) == null) {
                throw new NotFoundException("Attendee with id " + attendeesId + " not found");
            }
        });
        if (venueService.getVenueById(eventRequest.getVenuesId()) == null) {
            throw new NotFoundException("Venue with id " + eventRequest.getVenuesId() + " not found");
        }
        eventAttendeeRepository.deleteAttendeeIdAndEventId(eventId);
        eventRequest.getAttendeesId().forEach(attendeesId -> eventAttendeeRepository.insertAttendeeIdAndEventId(eventId, attendeesId));

        return eventMapper.toEventResponse(eventRepository.getEventById(eventId));
    }
}
