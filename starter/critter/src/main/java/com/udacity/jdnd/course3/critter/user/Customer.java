package com.udacity.jdnd.course3.critter.user;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import com.udacity.jdnd.course3.critter.pet.Pet;
import org.springframework.beans.BeanUtils;

//used the 'User' class as a mappedsuperclass because there are no queries needing to return the superclass
@Entity
public class Customer extends User{
    private String phoneNumber;
    private String notes;
     @OneToMany
             (mappedBy = "owner")
     private List<Pet> petsOwned= new ArrayList<>();

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Pet> getPetsOwned() {
        return petsOwned;
    }

    public void setPetsOwned(List<Pet> petsOwned) {
        this.petsOwned = petsOwned;
    }
    protected static CustomerDTO toCustomerDTO(Customer customer) {
        CustomerDTO customerDTO= new CustomerDTO();
        BeanUtils.copyProperties(customer,customerDTO,"petsOwned");
        List<Long> petIds=new ArrayList<>();

        for (Pet pet: customer.getPetsOwned()) {
            petIds.add(pet.getId());
        }

        customerDTO.setPetIds(petIds);
        return customerDTO;
    }
    protected static List<CustomerDTO> toCustomerDTOList(List<Customer> customers) {
        List<CustomerDTO> customerDTOList= new ArrayList<>();
        for (Customer customer:customers) {
            customerDTOList.add(toCustomerDTO(customer));
        }
        return customerDTOList;
    }
}
