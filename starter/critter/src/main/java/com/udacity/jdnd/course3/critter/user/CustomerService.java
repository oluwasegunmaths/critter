package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional

public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PetRepository petRepository;

    public Customer saveCustomer(Customer customer) {


        return customerRepository.save(customer);

    }

    public List<Customer> getAllCustomers() {
      return customerRepository.findAll();

    }

    public Customer getOwnerByPet(long petId) {
        return petRepository.getOne(petId).getOwner();

    }

    public Customer getCustomer(long ownerId) {
        return customerRepository.getOne(ownerId);
    }
}
