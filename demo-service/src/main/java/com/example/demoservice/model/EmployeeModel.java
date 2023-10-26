package com.example.demoservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "t_employee")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EmployeeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer employeeId;

    String name;
    Integer manager;
}
