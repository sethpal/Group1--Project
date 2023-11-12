package com.scaler.greivance.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserCommentDTO {

    long id;
    String comment;
    String createdBy;
    String createdAt;
}
