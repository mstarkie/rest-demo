package com.globalforge.gdeb;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * https://www.freecodecamp.org/news/how-to-build-a-rest-api-with-spring-boot-
 * using-mysql-and-jpa-f931e348734b/
 */
@Controller
/**
 * This is the CONTROLLER in the MVC pattern which is accessed by a view.
 * @author Michael C. Starkie
 */
public class EmployeeViewController {
    @Autowired
    EmployeeRepository employeeRepository;
    @GetMapping("/view/addEmployee")
    public String showAddEmployeeForm(Employee emp) {
        return "add-employee";
    }

    @PostMapping("/view/removeEmployee")
    public String showRemoveEmployeeForm(@ModelAttribute("badge_no") Integer badgeNumber) {
        Employee employee;
        try {
            employee = employeeRepository.findById(badgeNumber)
                .orElseThrow(() -> new EmployeeNotFoundException(badgeNumber));
            employeeRepository.delete(employee);
        } catch (EmployeeNotFoundException e) {
            e.printStackTrace();
        }
        return "redirect:/view/employees";
    }

    @PostMapping("/view/save")
    public String insert(@ModelAttribute("employee") Employee employee) {
        employeeRepository.save(employee);
        return "redirect:/view/employees";
    }

    /**
     * Returns a list of all Employee records from the Employees table.
     * http://localhost:8080/view/employees/all
     * @return List<Employee>
     */
    @GetMapping("/view/employees")
    public String getAll(Model model) {
        List<Employee> all = employeeRepository.findAll();
        model.addAttribute("employees", all);
        return "list-all-employees";
    }
}
