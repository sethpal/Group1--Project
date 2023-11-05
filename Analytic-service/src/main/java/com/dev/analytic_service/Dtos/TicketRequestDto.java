package com.dev.analytic_service.Dtos;

import com.dev.analytic_service.Enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TicketRequestDto {

    String description;
    State state;
    String raised_by_email;
    String assigned_to_email;
    Date generated_on;
    Date last_updated;
}
