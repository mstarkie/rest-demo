package com.globalforge.gdeb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    //Optional<Employee> findByBadgeNumber(Integer badgeNumber);
}
