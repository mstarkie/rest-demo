package com.globalforge.gdeb;

import java.time.Instant;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "employee")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
/**
 * One to one mapping of Employee to rows in an Employees table. The is the DATA
 * MODEL in the MVC pattern.
 * @author Michael C. Starkie
 */
public class Employee {
    @Id
    @Column(name = "badge_number")
    private Integer badgeNumber;
    @NotBlank
    @Column(name = "first_name")
    private String firstName;
    @NotBlank
    @Column(name = "last_name")
    private String lastName;
    @UpdateTimestamp
    @Column(name = "last_updated")
    private Instant lastUpdated;
}
