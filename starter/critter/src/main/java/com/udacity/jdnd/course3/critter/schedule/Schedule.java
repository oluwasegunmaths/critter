package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import com.udacity.jdnd.course3.critter.pet.Pet;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Schedule {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToMany
    private List<Employee> employeesScheduled;
    @ManyToMany
    private List<Pet> petsScheduled;
    private LocalDate date;
    @ElementCollection
    private Set<EmployeeSkill> activities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Employee> getEmployeesScheduled() {
        return employeesScheduled;
    }

    public void setEmployeesScheduled(List<Employee> employeesScheduled) {
        this.employeesScheduled = employeesScheduled;
    }

    public List<Pet> getPetsScheduled() {
        return petsScheduled;
    }

    public void setPetsScheduled(List<Pet> petsScheduled) {
        this.petsScheduled = petsScheduled;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<EmployeeSkill> getActivities() {
        return activities;
    }

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }
    protected static ScheduleDTO toScheduleDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO= new ScheduleDTO();
        BeanUtils.copyProperties(schedule,scheduleDTO,"petsScheduled","employeesScheduled");
        List<Long> petIds=new ArrayList<>();
        List<Long> employeeIds=new ArrayList<>();
        for (Employee employee: schedule.getEmployeesScheduled()) {
            employeeIds.add(employee.getId());
        }
        for (Pet pet: schedule.getPetsScheduled()) {
            petIds.add(pet.getId());
        }
        scheduleDTO.setPetIds(petIds);
        scheduleDTO.setEmployeeIds(employeeIds);

        return scheduleDTO;
    }
    protected static List<ScheduleDTO> toScheduleDTOList(List<Schedule> schedules) {
        List<ScheduleDTO> scheduleDTOList= new ArrayList<>();
        for (Schedule schedule:schedules) {
            scheduleDTOList.add(toScheduleDTO(schedule));
        }
        return scheduleDTOList;
    }

}
