package com.dev.analytic_service.Models;

import com.dev.analytic_service.Enums.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class User extends BaseModel {

    String name;

    @Column(unique = true,nullable = false)
    String email;

}
