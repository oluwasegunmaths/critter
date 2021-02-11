package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional

public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;
    public Employee saveEmployee(Employee employee) {
       return employeeRepository.save(employee);

    }

    public Employee getEmployee(long employeeId) {
        return employeeRepository.getOne(employeeId);

    }

    public void setAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
           Employee employee=employeeRepository.getOne(employeeId);
           employee.setDaysAvailable(daysAvailable);
           employeeRepository.save(employee);

    }

    public List<Employee> findEmployeesForService(DayOfWeek dayOfWeek, Set<EmployeeSkill> skills) {
        List<Employee> employees=employeeRepository.findByDaysAvailableContaining(dayOfWeek);
        List<Employee> filteredEmployees=new ArrayList<>();
        for (Employee employee: employees) {
            if(employee.getSkills().containsAll(skills)){
                filteredEmployees.add(employee);
            }
        }
       return filteredEmployees;

    }

    public Employee getOne(Long id) {
        return employeeRepository.getOne(id);
    }
}
