package com.globalforge.gdeb;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Tests the web service against a live controller and database.
 * @author Michael C. Starkie
 */
public class EmployeeClient {
    /** The URL for testing. */
    public static final String SERVER_URI = "http://localhost:8080/employees";
    /** The test badge number. */
    public static final int TEST_BADGE_NUMBER = 169272;
    /**
     * Test retrieval of an employee.
     * @param badge The badge number of the employee to retrieve.
     */
    private static void testGetEmployee(final int badge) {
        RestTemplate restTemplate = new RestTemplate();
        Employee emp = restTemplate.getForObject(SERVER_URI + "/badge/" + badge, Employee.class);
        System.out.println("testGetEmployee(): " + emp);
    }

    /**
     * Create a new record.
     * @param badge the badge number.
     */
    private static void testCreateEmployee(final int badge) {
        RestTemplate restTemplate = new RestTemplate();
        Employee emp = new Employee();
        emp.setBadgeNumber(badge);
        emp.setFirstName("John");
        emp.setLastName("Doe");
        Employee response = restTemplate.postForObject(SERVER_URI + "/save", emp, Employee.class);
        System.out.println("testCreateEmployee(): " + response);
    }

    /**
     * Update a record given a badge number.
     * @param badge the badge number.
     */
    private static void testUpdateEmployee(final int badge) {
        RestTemplate restTemplate = new RestTemplate();
        Employee emp = new Employee();
        emp.setBadgeNumber(badge);
        emp.setFirstName("Jane");
        emp.setLastName("Doe");
        restTemplate.put(SERVER_URI + "/badge/" + badge, emp);
    }

    /**
     * delete a record given a badge number.
     * @param badge The badge number
     */
    private static void testDeleteEmployee(final int badge) {
        RestTemplate restTemplate = new RestTemplate();
        Employee emp = new Employee();
        emp.setBadgeNumber(1);
        emp.setFirstName("John");
        emp.setLastName("Doe");
        restTemplate.delete(SERVER_URI + "/badge/" + badge);
    }

    /**
     * return all rows from the Employees table.
     */
    private static void testGetAllEmployess() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Employee[]> response = restTemplate.getForEntity(SERVER_URI + "/all",
            Employee[].class);
        Employee[] employees = response.getBody();
        for (Employee emp : employees) {
            System.out.println("testGetAllEmployess(): " + emp);
        }
    }

    public static void main(final String[] args) {
        testGetEmployee(TEST_BADGE_NUMBER);
        testCreateEmployee(1);
        testGetEmployee(1);
        testUpdateEmployee(1);
        testGetEmployee(1);
        testGetAllEmployess();
        testDeleteEmployee(1);
    }
}
