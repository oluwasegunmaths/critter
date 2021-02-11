package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pet {
    @Id
    @GeneratedValue
    private Long id;
    private PetType type;
    private String name;
    @ManyToOne

    private Customer owner;
    private LocalDate birthDate;
    private String notes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    protected static PetDTO toPetDTO (Pet pet) {
        PetDTO petDTO= new PetDTO();
        BeanUtils.copyProperties(pet,petDTO,"owner");
        petDTO.setOwnerId(pet.getOwner().getId());
        return petDTO;
    }
    protected static List<PetDTO> toPetDTOList(List<Pet> pets) {
        List<PetDTO> petDTOList= new ArrayList<>();
        for (Pet pet:pets) {
            petDTOList.add(toPetDTO(pet));
        }
        return petDTOList;
    }
}
