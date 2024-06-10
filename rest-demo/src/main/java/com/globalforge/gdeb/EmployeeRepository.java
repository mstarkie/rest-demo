package com.globalforge.gdeb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
/**
 * Implementation supplied by spring.
 * @author Michael C. Starkie
 *
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    //Optional<Employee> findByBadgeNumber(Integer badgeNumber);
}
