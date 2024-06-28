package com.globalforge.gdeb;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * This is the CONTROLLER in the MVC pattern which is accessed by a view.
 * https://www.freecodecamp.org/news/how-to-build-a-rest-api-with-spring-boot-
 * using-mysql-and-jpa-f931e348734b.
 * 
 * @author Michael C. Starkie
 */
@Controller
public class EmployeeViewController {

    /** The CRUD repository. */
    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Redirects to add-employee.html.
     * 
     * @param emp The employee to add.
     * @return The redirect page.
     */
    @GetMapping("/view/addEmployee")
    public String showAddEmployeeForm(final Employee emp) {
        return "add-employee";
    }

    /**
     * Removes an employee from the repository.
     * 
     * @param badgeNumber Identifies the employee to remove.
     * @return String Redirects to the list-all-employees.html file.
     */
    @PostMapping("/view/removeEmployee")
    public String showRemoveEmployeeForm(@ModelAttribute("badge_no") final Integer badgeNumber) {
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

    /**
     * Inserts a new employee into the database.
     * 
     * @param employee The employee to insert.
     * @return String Redirects to the list-all-employees.html file.
     */
    @PostMapping("/view/save")
    public String insert(@ModelAttribute("employee") final Employee employee) {
        employeeRepository.save(employee);
        return "redirect:/view/employees";
    }

    /**
     * Returns a list of all Employee records from the Employees table.
     * 
     * @param model The model.
     * @return Redirects to the list-all-employees.html file.
     */
    @GetMapping("/view/employees")
    public String getAll(final Model model) {
        List<Employee> all = employeeRepository.findAll();
        model.addAttribute("employees", all);
        return "list-all-employees";
    }
}
