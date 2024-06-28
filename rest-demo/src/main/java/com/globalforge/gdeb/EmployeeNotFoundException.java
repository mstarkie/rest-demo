package com.globalforge.gdeb;

/**
 * Checked exception thrown when no employee record is contained in the
 * database.
 * 
 * @author Michael C. Starkie
 */
@SuppressWarnings("serial")
public class EmployeeNotFoundException extends Exception {

    public EmployeeNotFoundException(final int employeeId) {
        super(String.format("Employee is not found with id : '%s'", employeeId));
    }
}
