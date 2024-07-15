package com.globalforge.gdeb;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Tests the web service against a live controller and database.
 * 
 * @author Michael C. Starkie
 */
public class EmployeeClient {

    /** The URL for testing. */
    public static final String SERVER_URI = "http://localhost:8080/employees";
    /** The test badge number. */
    public static final int TEST_BADGE_NUMBER = 169272;

    /**
     * Create a new record.
     * 
     * @param badgeNo The badge number.
     * @param firstName The first name of the employee.
     * @param lastName The last name of the employee.
     */
    private static void testCreateEmployee(final int badgeNo, final String firstName,
        final String lastName) {
        RestTemplate restTemplate = new RestTemplate();
        Employee emp = new Employee();
        emp.setBadgeNumber(badgeNo);
        emp.setFirstName(firstName);
        emp.setLastName(lastName);
        Employee response = restTemplate.postForObject(SERVER_URI + "/save", emp, Employee.class);
        System.out.println("testCreateEmployee(): " + response);
    }

    /**
     * Test retrieval of an employee.
     * 
     * @param badgeNo The badge number of the employee to retrieve.
     */
    private static void testGetEmployee(final int badgeNo) {
        RestTemplate restTemplate = new RestTemplate();
        Employee emp = restTemplate.getForObject(SERVER_URI + "/badge/" + TEST_BADGE_NUMBER,
            Employee.class);
        System.out.println("testGetEmployee(): " + emp);
    }

    /**
     * Update a record given a badge number.
     * 
     * @param badgeNo the badge number.
     * @param firstName The first name of the employee.
     * @param lastName The last name of the employee.
     */
    private static void testUpdateEmployee(final int badgeNo, final String firstName,
        final String lastName) {
        RestTemplate restTemplate = new RestTemplate();
        Employee emp = new Employee();
        emp.setBadgeNumber(badgeNo);
        emp.setFirstName(firstName);
        emp.setLastName(lastName);
        restTemplate.put(SERVER_URI + "/badge/" + badgeNo, emp);
    }

    /**
     * delete a record given a badge number.
     * 
     * @param badgeNo The badge number
     */
    private static void testDeleteEmployee(final int badgeNo) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(SERVER_URI + "/badge/" + badgeNo);
    }

    /**
     * return all rows from the Employees table.
     */
    private static void testGetAllEmployees() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Employee[]> response = restTemplate.getForEntity(SERVER_URI + "/all",
            Employee[].class);
        Employee[] employees = response.getBody();
        if (employees == null) {
            System.err.println("employees was null. Check testGetAllEmployees()");
            return;
        }
        for (Employee emp : employees) {
            System.out.println("testGetAllEmployess(): " + emp);
        }
    }

    public static void main(final String[] args) {
        testCreateEmployee(TEST_BADGE_NUMBER, "Michael", "Starkie");
        testGetEmployee(TEST_BADGE_NUMBER);
        testUpdateEmployee(TEST_BADGE_NUMBER, "Miguel", "Starkie");
        testGetEmployee(TEST_BADGE_NUMBER);
        testCreateEmployee(42, "Jane", "Doe");
        testGetAllEmployees();
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
        }
        testDeleteEmployee(TEST_BADGE_NUMBER);
        testGetAllEmployees();
        testDeleteEmployee(42);
        testGetAllEmployees();
    }
}
