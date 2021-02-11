package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional

public class ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    PetRepository petRepository;
    
    public List<Schedule> getScheduleForCustomer(long customerId) {
        Customer customer= customerRepository.getOne(customerId);
        return scheduleRepository.findByPetsScheduledIn(customer.getPetsOwned());


    }
   

    public List<Schedule> getScheduleForEmployee(long employeeId) {
        Employee employee=employeeRepository.getOne(employeeId);
         return scheduleRepository.findAllByEmployeesScheduledContaining(employee);


    }

    public List<Schedule> getScheduleForPet(long petId) {
        Pet pet= petRepository.getOne(petId);
        return scheduleRepository.findAllByPetsScheduledContaining(pet);

    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public Schedule createSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }
}
