package com.dev.analytic_service.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeRequestDto {

    String name;
    String email;
    String work_dept;
    double salary;
    Date hire_date;
    Date birth_date;
}
