package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.udacity.jdnd.course3.critter.user.Customer.toCustomerDTO;
import static com.udacity.jdnd.course3.critter.user.Customer.toCustomerDTOList;
import static com.udacity.jdnd.course3.critter.user.CustomerDTO.toCustomer;
import static com.udacity.jdnd.course3.critter.user.Employee.toEmployeeDTO;
import static com.udacity.jdnd.course3.critter.user.Employee.toEmployeeDTOList;
import static com.udacity.jdnd.course3.critter.user.EmployeeDTO.toEmployee;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    CustomerService customerService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    PetService petService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        List<Pet> pets= new ArrayList<>();
        if (customerDTO.getPetIds()!=null) {
            for (Long id : customerDTO.getPetIds()) {
                pets.add(petService.getOne(id));
            }
        }
        Customer customer=toCustomer(customerDTO,pets);
        return toCustomerDTO(customerService.saveCustomer(customer));
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        return toCustomerDTOList(customerService.getAllCustomers());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        return toCustomerDTO(customerService.getOwnerByPet(petId));
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee=toEmployee(employeeDTO);

        return toEmployeeDTO(employeeService.saveEmployee(employee));
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        return toEmployeeDTO(employeeService.getEmployee(employeeId));
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
      employeeService.setAvailability(daysAvailable,employeeId);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        return toEmployeeDTOList(employeeService.findEmployeesForService(employeeDTO.getDate().getDayOfWeek(),employeeDTO.getSkills()));
    }

}
