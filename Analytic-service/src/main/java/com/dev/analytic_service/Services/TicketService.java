package com.dev.analytic_service.Services;

import com.dev.analytic_service.Dtos.EmployeeResponseDto;
import com.dev.analytic_service.Dtos.TicketRequestDto;
import com.dev.analytic_service.Dtos.TicketResponseDto;
import com.dev.analytic_service.Enums.State;
import com.dev.analytic_service.Models.Ticket;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public interface TicketService {

    public TicketResponseDto save(TicketRequestDto ticketRequestDto);

    public TicketResponseDto findById(UUID uuid);

    public Map<UUID,List<TicketResponseDto>> ticketGroupByAssignedTo();

    public Map<State,List<TicketResponseDto>> ticketGroupByState();

    public Map<String,List<TicketResponseDto>> ticketGroupByAgeing();

    public Map<UUID,List<TicketResponseDto>> ticketResolvedPerUSer();

}
