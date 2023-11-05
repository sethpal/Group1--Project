package com.dev.analytic_service.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientRequestDto {

    String name;
    String email;
    String address;
    Integer pincode;

}
