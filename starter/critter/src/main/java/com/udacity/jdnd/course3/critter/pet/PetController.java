package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.udacity.jdnd.course3.critter.pet.Pet.toPetDTO;
import static com.udacity.jdnd.course3.critter.pet.Pet.toPetDTOList;
import static com.udacity.jdnd.course3.critter.pet.PetDTO.toPet;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    private PetService petService;
    @Autowired
    CustomerService customerService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Customer customer= customerService.getCustomer(petDTO.getOwnerId());
        Pet pet = toPet(petDTO, customer);

        return toPetDTO(petService.savePet(pet));
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return toPetDTO(petService.getPet(petId));

    }

    @GetMapping
    public List<PetDTO> getPets(){
        return toPetDTOList(petService.getPets());

    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        return toPetDTOList(petService.getPetsByOwner(ownerId));

    }
}
