package com.foureverinbeta.employeemanagementservice.employee;

import com.foureverinbeta.employeemanagementservice.Exceptions.EmployeeNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@Api(value = "Employees", tags = "Employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping(path="/employees")
    @ApiOperation(value = "Gets all of the currently active employees")
    public List<Employee> getAllActiveEmployees() {
        return employeeRepository.findAllByStatus(EmployeeStatus.ACTIVE.name());
    }

    @GetMapping(path="/employees/{id}")
    @ApiOperation(value = "Gets an active employee by their employee id")
    public Employee getEmployeeById(@ApiParam(value = "The employee id to lookup", required = true, name = "id") @PathVariable("id") Long empId) throws EmployeeNotFoundException {
        return employeeRepository
                .findByIdAndStatus(empId, EmployeeStatus.ACTIVE.name())
                .orElseThrow(() -> new EmployeeNotFoundException(String.format("Employee with id %d was not found.", empId)));
    }

    @PostMapping(path = "/employees")
    @ApiOperation(value = "Creates an employee")
    public ResponseEntity<Employee> createEmployee(@ApiParam(value = "The employee object to create", required = true) @RequestBody Employee employee) {
        Employee newEmployee = employeeRepository.save(employee);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newEmployee.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/employees/{id}")
    @ApiOperation(value = "Deletes an employee (converts their employment status to INACTIVE")
    public ResponseEntity<Void> deleteById(@ApiParam(value = "The employee id of the employee to delete", required = true) @PathVariable("id") Long empId) {
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
    @ApiOperation(value = "Updates an employee")
    public ResponseEntity<Employee> updateEmployeeById(@ApiParam(value = "The employee id of the employee that is being updated", required = true) @PathVariable("id") Long empId,
                                                       @ApiParam(value = "The updated employee object", required = true) @RequestBody Employee employee) {
        Optional<Employee> updatedEmployee = employeeRepository.findById(empId);

        if (!updatedEmployee.isPresent())
            return ResponseEntity.notFound().build();

        employee.setId(empId);

        employeeRepository.save(employee);

        return ResponseEntity.noContent().build();
    }
}

