package com.jooce.sfgpetclinic.bootstrap;

import com.jooce.sfgpetclinic.model.Owner;
import com.jooce.sfgpetclinic.model.Pet;
import com.jooce.sfgpetclinic.model.PetType;
import com.jooce.sfgpetclinic.model.Vet;
import com.jooce.sfgpetclinic.services.OwnerService;
import com.jooce.sfgpetclinic.services.PetTypeService;
import com.jooce.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        PetType dog = addPetType("Dog");
        PetType cat = addPetType("Cat");

        Owner gabriel = addOwner("Gabriel", "Matshabe", "Roodeport", "Roodeport", "0832465689");
        Pet gabsPet = addPet("Rosco", dog, gabriel, LocalDate.now());
        gabriel.getPets().add(gabsPet);

        Owner mika = addOwner("Mika", "Zindagi", "Bucleugh", "Bucleugh", "08432123491");
        Pet mikasPet1 = addPet("Buddy", dog, mika, LocalDate.now());
        mika.getPets().add(mikasPet1);
        Pet mikasPet2 = addPet("Buddy", cat, mika, LocalDate.now());
        mika.getPets().add(mikasPet2);

        addVet("Gabby", "Vele");
        addVet("Jin", "Zindagi");
    }

    private Owner addOwner(String firstName, String lastName, String address, String city, String telephone) {
        Owner owner = new Owner();
        owner.setFirstName(firstName);
        owner.setLastName(lastName);
        owner.setAddress(address);
        owner.setCity(city);
        owner.setTelephone(telephone);

       return ownerService.save(owner);
    }

    private void addVet(String firstName, String lastName) {
        Vet vet = new Vet();
        vet.setFirstName(firstName);
        vet.setLastName(lastName);

        vetService.save(vet);
    }

    private PetType addPetType(String name) {
        PetType petType = new PetType();
        petType.setName(name);

        return petTypeService.save(petType);
    }

    private Pet addPet(String name, PetType petType, Owner owner, LocalDate birthDate) {
        Pet pet = new Pet();
        pet.setName(name);
        pet.setPetType(petType);
        pet.setOwner(owner);
        pet.setBirthDate(birthDate);

        return pet;
    }
}
