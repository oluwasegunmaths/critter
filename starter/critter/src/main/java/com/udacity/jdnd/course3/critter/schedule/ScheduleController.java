package com.udacity.jdnd.course3.critter.schedule;

import com.sun.xml.bind.v2.util.QNameMap;
import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.udacity.jdnd.course3.critter.schedule.Schedule.toScheduleDTO;
import static com.udacity.jdnd.course3.critter.schedule.Schedule.toScheduleDTOList;
import static com.udacity.jdnd.course3.critter.schedule.ScheduleDTO.toSchedule;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private PetService petService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        List<Pet> pets=new ArrayList<>();
        List<Employee> employees=new ArrayList<>();
        for (Long id: scheduleDTO.getEmployeeIds()) {
            employees.add(employeeService.getOne(id));
        }
        for (Long id: scheduleDTO.getPetIds()) {
            pets.add(petService.getOne(id));
        }

        Schedule schedule = toSchedule(scheduleDTO, pets, employees);
        return toScheduleDTO(scheduleService.createSchedule(schedule));
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
       return toScheduleDTOList(scheduleService.getAllSchedules());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
       return toScheduleDTOList(scheduleService.getScheduleForPet(petId));
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        return toScheduleDTOList(scheduleService.getScheduleForEmployee(employeeId));
//        throw new UnsupportedOperationException();
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        return toScheduleDTOList(scheduleService.getScheduleForCustomer(customerId));
    }
}
