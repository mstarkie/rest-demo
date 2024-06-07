package com.globalforge.gdeb;

@SuppressWarnings("serial")
public class EmployeeNotFoundException extends Exception {
    public EmployeeNotFoundException(int employee_id) {
        super(
            String.format("Employee is not found with id : '%s'", employee_id));
    }
}
