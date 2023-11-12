package com.scaler.greivance.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.util.UUID;

@lombok.Getter
@lombok.Setter
@MappedSuperclass
public class BaseClass {
//TODO sequence ID generator
    //TODO Use MYSQL directly
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    long id;

    @CreatedDate
    Instant createdAt;

    @LastModifiedDate
    Instant updatedAt;

    @CreatedBy
    long createdBy;

    @LastModifiedBy
    long updatedBy;

}
