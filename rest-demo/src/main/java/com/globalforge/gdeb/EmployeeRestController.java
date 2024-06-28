package com.globalforge.gdeb;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is the CONTROLLER for client/server type calls.
 * https://www.freecodecamp.org/news/how-to-build-a-rest-api-with-spring-boot-
 * using-mysql-and-jpa-f931e348734b.
 * 
 * @author Michael C. Starkie
 */
@RestController
@RequestMapping("/employees")
public class EmployeeRestController {

    /** CRUD Repository. */
    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Obtain an employee from the Employees table given a badge number.
     * http://localhost:8080/employees/badge/169272
     * 
     * @param badgeNumber Employee badge number.
     * @return Employee The employee
     * @throws EmployeeNotFoundException When an employee is not found in the database.
     */
    @GetMapping("/badge/{badge_no}")
    public Employee getByBadge(
        @PathVariable(name = "badge_no", required = true, value = "badge_no") final Integer badgeNumber)
        throws EmployeeNotFoundException {
        return employeeRepository.findById(badgeNumber)
            .orElseThrow(() -> new EmployeeNotFoundException(badgeNumber));
    }

    /**
     * Inserts an Employee record into the Employees table.
     * 
     * @param employee The employee
     * @return The Employee returned.
     */
    @PostMapping("/save")
    public Employee insert(@Valid @RequestBody final Employee employee) {
        return employeeRepository.save(employee);
    }

    /**
     * Updates an Employee record in the Employees table.
     * 
     * @param badgeNumber The employee badge number.
     * @param employeeDetails The updated information
     * @return The updated Employee record returned.
     * @throws EmployeeNotFoundException When an employee is not found in the database.
     */
    @PutMapping("/badge/{badge_no}")
    public Employee update(
        @PathVariable(name = "badge_no", required = true, value = "badge_no") final Integer badgeNumber,
        @Valid @RequestBody final Employee employeeDetails) throws EmployeeNotFoundException {
        Employee employee = employeeRepository.findById(badgeNumber)
            .orElseThrow(() -> new EmployeeNotFoundException(badgeNumber));
        employee.setBadgeNumber(employeeDetails.getBadgeNumber());
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        Employee updatedEmployee = employeeRepository.save(employee);
        return updatedEmployee;
    }

    /**
     * Returns a list of all Employee records from the Employees table.
     * http://localhost:8080/employees/all
     * 
     * @return list of all known employees
     */
    @GetMapping("/all")
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    /**
     * Deletes a row from the Employees table given a badge number.
     * 
     * @param badgeNumber The badge number.
     * @return ResponseEntity The response.
     * @throws EmployeeNotFoundException When an employee is not found in the database.
     */
    @DeleteMapping("/badge/{badge_no}")
    public ResponseEntity<?> delete(
        @PathVariable(name = "badge_no", required = true, value = "badge_no") final Integer badgeNumber)
        throws EmployeeNotFoundException {
        Employee employee = employeeRepository.findById(badgeNumber)
            .orElseThrow(() -> new EmployeeNotFoundException(badgeNumber));
        employeeRepository.delete(employee);
        return ResponseEntity.ok().build();
    }
}
