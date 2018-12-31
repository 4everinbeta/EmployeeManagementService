package com.foureverinbeta.employeemanagementservice.employee;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByIdAndStatus(Long id, String status);
    List<Employee> findAllByStatus(String status);
}
