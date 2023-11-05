package com.dev.analytic_service.Models;


import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Employees")
public class Employee extends User{

    String work_dept;
    double salary;

//    @Column(columnDefinition = "DATETIME")

    Date hire_date;

//    @Column(columnDefinition = "DATETIME")
    Date birth_date;

}
