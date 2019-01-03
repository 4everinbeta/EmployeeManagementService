package com.foureverinbeta.employeemanagementservice.employee;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;

public class EmployeeControllerTest {

    private Employee testEmployee = null;

    @Before
    public void setUp() throws Exception {
        testEmployee = new Employee("Kurt",
                "D",
                "Cobain",
                new SimpleDateFormat("yyyy-mm-dd").parse("1967-02-20"),
                new SimpleDateFormat("yyyy-mm-dd").parse("1990-01-01"),
                EmployeeStatus.ACTIVE.name());
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAllActiveEmployees() {
    }

    @Test
    public void getEmployeeById() {
    }

    @Test
    public void createEmployee() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void updateEmployeeById() {
    }
}