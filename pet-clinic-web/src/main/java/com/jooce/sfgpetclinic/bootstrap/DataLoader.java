package com.jooce.sfgpetclinic.bootstrap;

import com.jooce.sfgpetclinic.model.*;
import com.jooce.sfgpetclinic.services.OwnerService;
import com.jooce.sfgpetclinic.services.PetTypeService;
import com.jooce.sfgpetclinic.services.SpecialityService;
import com.jooce.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
    }

    @Override
    public void run(String... args) throws Exception {
        int countPets = petTypeService.findAll().size();

        if (countPets == 0) {
            loadData();
        }
    }

    private void loadData() {
        System.out.println("Load data");

        PetType dog = addPetType("Dog");
        PetType cat = addPetType("Cat");

        Speciality radiology = addSpeciality("radiology");
        Speciality surgery = addSpeciality("surgery");
        Speciality dentistry = addSpeciality("dentistry");

        Owner gabriel = addOwner("Gabriel", "Matshabe", "Roodeport", "Roodeport", "0832465689");
        Pet gabsPet = addPet("Rosco", dog, gabriel, LocalDate.now());
        gabriel.getPets().add(gabsPet);

        Owner mika = addOwner("Mika", "Zindagi", "Bucleugh", "Bucleugh", "08432123491");
        Pet buddy = addPet("Buddy", dog, mika, LocalDate.now());
        buddy.getVisits().add(addVisit(buddy, LocalDate.now(), "Sleepy dog"));
        mika.getPets().add(buddy);

        Pet frisky = addPet("Frisky", cat, mika, LocalDate.now());
        frisky.getVisits().add(addVisit(frisky, LocalDate.now(), "Sneezing Cat"));
        mika.getPets().add(frisky);

        addVet("Gabby", "Vele", radiology);
        addVet("Jin", "Zindagi", surgery);
        addVet("Sam", "Axe", dentistry);
    }

    private Speciality addSpeciality(String description) {
        Speciality speciality = new Speciality();
        speciality.setDescription(description);

        return specialityService.save(speciality);
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

    private void addVet(String firstName, String lastName, Speciality radiology) {
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

    private Visit addVisit(Pet frisky, LocalDate date, String description) {
        Visit visit = new Visit();
        visit.setDate(date);
        visit.setDescription(description);
        visit.setPet(frisky);

        return visit;
    }
}
