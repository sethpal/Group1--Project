package com.dev.analytic_service.Repositories;

import com.dev.analytic_service.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {

    @Override
    List<User> findAll();

    @Override
    Optional<User> findById(UUID uuid);
}
