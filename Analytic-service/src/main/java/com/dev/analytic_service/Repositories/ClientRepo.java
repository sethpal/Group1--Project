package com.dev.analytic_service.Repositories;

import com.dev.analytic_service.Models.Client;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

public interface ClientRepo extends JpaRepository<Client, UUID> {

    @Override
    <S extends Client> S save(S entity);

    @Override
    List<Client> findAll();

    @Override
    Optional<Client> findById(UUID uuid);

    Client findByEmailEqualsIgnoreCase(String email);
}
