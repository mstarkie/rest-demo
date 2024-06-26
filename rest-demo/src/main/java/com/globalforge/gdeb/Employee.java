package com.globalforge.gdeb;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * One to one mapping of Employee to rows in an Employees table. The is the DATA
 * MODEL in the MVC pattern.
 * 
 * @author Michael C. Starkie
 */
@Entity
@Table(name = "employee")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    /** Employee badge number. */
    @Id
    @Column(name = "badge_number")
    private Integer badgeNumber;
    /** Employee first name. */
    @NotBlank
    @Column(name = "first_name")
    private String firstName;
    /** Employee last name. */
    @NotBlank
    @Column(name = "last_name")
    private String lastName;
    /** Time stamp of last update. */
    @UpdateTimestamp
    @Column(name = "last_updated")
    private Instant lastUpdated;
}
