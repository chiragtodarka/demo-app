package com.example.demoservice.service;

import com.example.demoservice.model.EmployeeModel;
import com.example.demoservice.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import com.example.demoservice.dto.Employee;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        List<EmployeeModel> employeeModelList = employeeRepository.findAll();
        List<Employee> employeeList = new ArrayList<>();
        for (EmployeeModel employeeModel: employeeModelList) {
            employeeList.add(convertEmployeeModelToEmployeeSchema(employeeModel));
        }
        return employeeList;
    }

    public Optional<Employee> getEmployeeById(String employeeId) {
        Optional<EmployeeModel> employeeModel = employeeRepository.findById(Integer.parseInt(employeeId));
        if (employeeModel.isPresent()) {
            return Optional.of(convertEmployeeModelToEmployeeSchema(employeeModel.get()));
        }
        return Optional.empty();
    }

    private Employee convertEmployeeModelToEmployeeSchema(EmployeeModel employeeModel) {
        return Employee.builder()
                .employeeId(employeeModel.getEmployeeId())
                .name(employeeModel.getName())
                .manager(employeeModel.getManager())
                .build();
    }

    public Employee createEmployee(Employee employee) {
        EmployeeModel employeeModel = EmployeeModel.builder()
                .name(employee.getName())
                .manager(employee.getManager())
                .build();

        employeeRepository.save(employeeModel);
        employee.setEmployeeId(employeeModel.getEmployeeId());
        return employee;
    }
}
