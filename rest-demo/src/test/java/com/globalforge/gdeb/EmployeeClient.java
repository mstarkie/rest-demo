package com.globalforge.gdeb;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class EmployeeClient {
    public static final String SERVER_URI = "http://localhost:8080/employees";
    private static void testGetEmployee(int badge) {
        RestTemplate restTemplate = new RestTemplate();
        Employee emp = restTemplate.getForObject(SERVER_URI + "/badge/" + badge,
            Employee.class);
        System.out.println("testGetEmployee(): " + emp);
    }

    private static void testCreateEmployee() {
        RestTemplate restTemplate = new RestTemplate();
        Employee emp = new Employee();
        emp.setBadgeNumber(1);
        emp.setFirstName("John");
        emp.setLastName("Doe");
        Employee response = restTemplate.postForObject(SERVER_URI + "/save",
            emp, Employee.class);
        System.out.println("testCreateEmployee(): " + response);
    }

    private static void testUpdateEmployee(int badge) {
        RestTemplate restTemplate = new RestTemplate();
        Employee emp = new Employee();
        emp.setBadgeNumber(badge);
        emp.setFirstName("Jane");
        emp.setLastName("Doe");
        restTemplate.put(SERVER_URI + "/badge/" + badge, emp);
    }

    private static void testDeleteEmployee(int badge) {
        RestTemplate restTemplate = new RestTemplate();
        Employee emp = new Employee();
        emp.setBadgeNumber(1);
        emp.setFirstName("John");
        emp.setLastName("Doe");
        restTemplate.delete(SERVER_URI + "/badge/" + badge);
    }

    private static void testGetAllEmployess() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Employee[]> response = restTemplate
            .getForEntity(SERVER_URI + "/all", Employee[].class);
        Employee[] employees = response.getBody();
        for (Employee emp : employees) {
            System.out.println("testGetAllEmployess(): " +emp);;
        }
    }

    public static void main(String args[]) {
        testGetEmployee(169272);
        testCreateEmployee();
        testGetEmployee(1);
        testUpdateEmployee(1);
        testGetEmployee(1);
        testGetAllEmployess();
        testDeleteEmployee(1);
    }
}
