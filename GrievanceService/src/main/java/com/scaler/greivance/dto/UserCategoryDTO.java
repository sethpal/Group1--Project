package com.scaler.greivance.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserCategoryDTO {
    long id;
    String name;
    String description;
    long department;
}
