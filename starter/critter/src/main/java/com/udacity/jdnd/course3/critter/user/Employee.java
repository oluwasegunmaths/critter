package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.BeanUtils;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
//used the 'User' class as a mappedsuperclass because there are no queries needing to return the superclass
@Entity
public class Employee extends User{
    @ElementCollection
    private Set<EmployeeSkill> skills;
    @ElementCollection
    private Set<DayOfWeek> daysAvailable;

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }
    protected static EmployeeDTO toEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO= new EmployeeDTO();
        BeanUtils.copyProperties(employee,employeeDTO);
        return employeeDTO;
    }
    protected static List<EmployeeDTO> toEmployeeDTOList(List<Employee> employees) {
        List<EmployeeDTO> employeeDTOList= new ArrayList<>();
        for (Employee employee:employees) {
            employeeDTOList.add(toEmployeeDTO(employee));
        }
        return employeeDTOList;
    }
}
