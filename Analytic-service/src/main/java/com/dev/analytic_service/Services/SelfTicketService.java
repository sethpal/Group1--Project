package com.dev.analytic_service.Services;

import com.dev.analytic_service.Dtos.EmployeeResponseDto;
import com.dev.analytic_service.Dtos.TicketRequestDto;
import com.dev.analytic_service.Dtos.TicketResponseDto;
import com.dev.analytic_service.Enums.State;
import com.dev.analytic_service.Models.Employee;
import com.dev.analytic_service.Models.Ticket;
import com.dev.analytic_service.Repositories.ClientRepo;
import com.dev.analytic_service.Repositories.EmployeeRepo;
import com.dev.analytic_service.Repositories.TicketRepo;
import lombok.AllArgsConstructor;
import org.hibernate.boot.beanvalidation.GroupsPerOperation;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;

@Service
@AllArgsConstructor
public class SelfTicketService implements TicketService{

    TicketRepo ticketRepo;
    EmployeeRepo employeeRepo;
    ClientRepo clientRepo;

    public Ticket ticketRequestDtoToTicket
            (TicketRequestDto ticketRequestDto) {

        Ticket ticket = new Ticket();

        ticket.setDescription(ticketRequestDto.getDescription());
        ticket.setState(ticketRequestDto.getState());
        ticket.setAssigned_to(
                employeeRepo.
                        findByEmailEqualsIgnoreCase(
                                ticketRequestDto.getAssigned_to_email()
                        )
        );

        ticket.setRaised_by(
                clientRepo.
                        findByEmailEqualsIgnoreCase(ticketRequestDto.getRaised_by_email())
        );

        ticket.setGenerated_on(ticketRequestDto.getGenerated_on());
        ticket.setLast_updated(ticketRequestDto.getLast_updated());

        return ticket;
    }

    public TicketResponseDto ticketToTicketResponseDto
            (Ticket ticket) {

        TicketResponseDto ticketResponseDto = new TicketResponseDto();

        ticketResponseDto.setUuid(ticket.getId());
        ticketResponseDto.setState(ticket.getState());
        ticketResponseDto.setDescription(ticket.getDescription());
        ticketResponseDto.setLast_updated(ticket.getLast_updated());
        ticketResponseDto.setGenerated_on(ticket.getGenerated_on());
        ticketResponseDto.setRaised_by(ticket.getRaised_by().getEmail());
        ticketResponseDto.setAssigned_to(ticket.getAssigned_to().getEmail());

        return ticketResponseDto;
    }

    @Override
    public TicketResponseDto save(TicketRequestDto ticketRequestDto) {
        return null;
    }

    @Override
    public TicketResponseDto findById(UUID uuid) {
        return ticketToTicketResponseDto(ticketRepo.findById(uuid).get());
    }

    @Override
    public Map<UUID, List<TicketResponseDto>> ticketGroupByAssignedTo() {

        List<Ticket> tickets = ticketRepo.findByStateIsNot(State.RESOLVED);

        System.out.println(tickets.size());

        Map<UUID, List<TicketResponseDto>> response = new HashMap<>();

        tickets.forEach(ticket -> {
            UUID key = ticket.getAssigned_to().getId();
            List<TicketResponseDto> value = response.getOrDefault(key,new ArrayList<>());
            value.add(ticketToTicketResponseDto(ticket));
            response.put(key,value);
        });

        return response;
    }

    @Override
    public Map<State, List<TicketResponseDto>> ticketGroupByState() {

        List<Ticket> tickets = ticketRepo.findAll();
        Map<State, List<TicketResponseDto>> response = new HashMap<>();

        tickets.forEach(ticket -> {

            State key = ticket.getState();
            List<TicketResponseDto> value = response.getOrDefault(key,new ArrayList<>());

            value.add(ticketToTicketResponseDto(ticket));

            response.put(key,value);


        });
        return response;
    }

    @Override
    public Map<String, List<TicketResponseDto>> ticketGroupByAgeing() {

//        Groups
//                1. 0-5 Days
//                2. 6-10 Days
//                3. 11-15 Days
//                4. 16+ Days

        List<Ticket> tickets = ticketRepo.findAll();
        Map<String, List<TicketResponseDto>> response = new HashMap<>();

        tickets.forEach(ticket -> {
            long days1 = ticket.getGenerated_on().getTime()/(1000*60*60*24);
            long days2 = new Date().getTime()/(1000*60*60*24);
            String key;

            if(days2 - days1 >= 16){

                key = "16+ Days";

            } else if (days2 - days1 >= 11) {

                key = "11-15 Days";

            } else if (days2 - days1 >= 6) {

                key = "6-10 Days";

            }else {

                key = "0-5 Days";

            }

            List<TicketResponseDto> value = response.getOrDefault(key,new ArrayList<>());

            value.add(ticketToTicketResponseDto(ticket));

            response.put(key,value);


        });

        return response;
    }

    @Override
    public Map<UUID, List<TicketResponseDto>> ticketResolvedPerUSer() {

        List<Ticket> tickets = ticketRepo.findByStateIs(State.RESOLVED);

        Map<UUID, List<TicketResponseDto>> response = new HashMap<>();

        tickets.forEach(ticket -> {
            UUID key = ticket.getAssigned_to().getId();

            List<TicketResponseDto> value = response.getOrDefault(key,new ArrayList<>());

            value.add(ticketToTicketResponseDto(ticket));

            response.put(key,value);
        });

        return response;
    }
}
