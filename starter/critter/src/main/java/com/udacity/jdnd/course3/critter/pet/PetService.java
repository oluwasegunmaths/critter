package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional

public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private CustomerRepository  customerRepository;
    public Pet savePet(Pet pet) {
          return petRepository.save(pet);
    }

    public Pet getPet(long petId) {
        return petRepository.getOne(petId);
    }



    public List<Pet> getPets() {
       return petRepository.findAll();

    }

    public List<Pet> getPetsByOwner(long ownerId) {
        Customer customer= customerRepository.getOne(ownerId);
       return petRepository.findPetsByOwner(customer);
    }

    public Pet getOne(Long id) {
        return petRepository.getOne(id);
    }
}
