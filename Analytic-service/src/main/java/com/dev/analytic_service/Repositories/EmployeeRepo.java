package com.dev.analytic_service.Repositories;

import com.dev.analytic_service.Models.Employee;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

public interface EmployeeRepo extends JpaRepository<Employee, UUID> {

    @Override
    <S extends Employee> S save(S entity);

    @Override
    List<Employee> findAll();

    @Override
    Optional<Employee> findById(UUID uuid);

    Employee findByEmailEqualsIgnoreCase(String email);
}
