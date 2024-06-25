package com.globalforge.gdeb;

@SuppressWarnings("serial")
public class EmployeeNotFoundException extends Exception {
    public EmployeeNotFoundException(final int employeeId) {
        super(
            String.format("Employee is not found with id : '%s'", employeeId));
    }
}
