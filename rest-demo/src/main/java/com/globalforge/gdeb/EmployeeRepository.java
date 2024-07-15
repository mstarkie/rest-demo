package com.globalforge.gdeb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Implementation supplied by spring.<br>
 * Spring data JPA provides a set of persistence queries out of the box. <br>
 * Supports GET, POST, PUT, DELETE RESTFul services. <br>
 * https://springjava.com/spring-data-jpa/how-to-use-jpa-repository-in-spring-boot/
 * 
 * @author Michael C. Starkie
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
