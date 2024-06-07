package com.globalforge.gdeb;

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
import jakarta.validation.Valid;

/*
 * Every request handling method of the controller class automatically
 * serializes return objects into HttpResponse.
 * https://www.freecodecamp.org/news/how-to-build-a-rest-api-with-spring-boot-
 * using-mysql-and-jpa-f931e348734b/
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;
    ////////////////////////
    /// Custom Interface ///
    ////////////////////////
    // http://localhost:8080/employees/badge/169272 
    @GetMapping("/badge/{badge_no}")
    public Employee getByBadge(
        @PathVariable(name = "badge_no", required = true, value = "badge_no") Integer badgeNumber)
        throws EmployeeNotFoundException {
        return employeeRepository.findById(badgeNumber)
            .orElseThrow(() -> new EmployeeNotFoundException(badgeNumber));
    }
    ////////////////////////////
    /// Out of box Interface ///
    ////////////////////////////

    // Create a new Employee
    @PostMapping("/save")
    public Employee insert(@Valid @RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    // Update an Employee
    @PutMapping("/badge/{badge_no}")
    public Employee update(
        @PathVariable(name = "badge_no", required = true, value = "badge_no") Integer employeeId,
        @Valid @RequestBody Employee employeeDetails)
        throws EmployeeNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new EmployeeNotFoundException(employeeId));
        employee.setBadgeNumber(employeeDetails.getBadgeNumber());
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        Employee updatedEmployee = employeeRepository.save(employee);
        return updatedEmployee;
    }

    // http://localhost:8080/employees/all
    @GetMapping("/all")
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    // Delete an Employee
    @DeleteMapping("/badge/{badge_no}")
    public ResponseEntity<?> delete(
        @PathVariable(name = "badge_no", required = true, value = "badge_no") Integer employeeId)
        throws EmployeeNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new EmployeeNotFoundException(employeeId));
        employeeRepository.delete(employee);
        return ResponseEntity.ok().build();
    }
}
