package com.dev.analytic_service.Controllers;


import com.dev.analytic_service.Dtos.TicketResponseDto;
import com.dev.analytic_service.Enums.State;
import com.dev.analytic_service.Services.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Controller
@RestController
@AllArgsConstructor
public class TicketController {

    TicketService ticketService;


    @GetMapping("/tickets/groupByAssignedTo")
    private Map<UUID,List<TicketResponseDto>> ticketGroupByAssignedTo(){

        return ticketService.ticketGroupByAssignedTo();
    }

    @GetMapping("/tickets/resolvedPerUSer")
    private Map<UUID,List<TicketResponseDto>> ticketsResolvedPerUser(){

        return ticketService.ticketResolvedPerUSer();
    }

    @GetMapping("/tickets/groupByState")
    private Map<State,List<TicketResponseDto>> ticketsGroupByState(){

        return ticketService.ticketGroupByState();
    }

    @GetMapping("/tickets/groupByAgeing")
    private Map<String,List<TicketResponseDto>> ticketsGroupByAgeing(){

        return ticketService.ticketGroupByAgeing();
    }

}
