package com.foureverinbeta.employeemanagementservice.employee;

import com.foureverinbeta.employeemanagementservice.Exceptions.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping(path="/employees")
    public List<Employee> getAllActiveEmployees() {
        return employeeRepository.findAllByStatus(EmployeeStatus.ACTIVE.name());
    }

    @GetMapping(path="/employees/{id}")
    public Employee getEmployeeById(@PathVariable("id") Long empId) throws EmployeeNotFoundException {
        return employeeRepository
                .findByIdAndStatus(empId, EmployeeStatus.ACTIVE.name())
                .orElseThrow(() -> new EmployeeNotFoundException(String.format("Employee with id %d was not found.", empId)));
    }

    @PostMapping(path = "/employees")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee newEmployee = employeeRepository.save(employee);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newEmployee.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/employees/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long empId) {
        Optional<Employee> employeeToDelete = employeeRepository.findById(empId);

        if (!employeeToDelete.isPresent())
            return ResponseEntity.notFound().build();

        Employee deletedEmployee = employeeToDelete.get();

        deletedEmployee.setId(empId);
        deletedEmployee.setStatus(EmployeeStatus.INACTIVE.name());

        employeeRepository.save(deletedEmployee);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/employees/{id}")
    public ResponseEntity<Employee> updateEmployeeById(@PathVariable("id") Long empId, @RequestBody Employee employee) {
        Optional<Employee> updatedEmployee = employeeRepository.findById(empId);

        if (!updatedEmployee.isPresent())
            return ResponseEntity.notFound().build();

        employee.setId(empId);

        employeeRepository.save(employee);

        return ResponseEntity.noContent().build();
    }
}

