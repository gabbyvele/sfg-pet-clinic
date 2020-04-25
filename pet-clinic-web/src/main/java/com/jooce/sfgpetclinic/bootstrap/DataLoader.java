package com.jooce.sfgpetclinic.bootstrap;

import com.jooce.sfgpetclinic.model.Owner;
import com.jooce.sfgpetclinic.model.Vet;
import com.jooce.sfgpetclinic.services.OwnerService;
import com.jooce.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;

        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {
        addOwner("Gabriel", "Matshabe");
        addOwner("Mika", "Zindagi");

        addVet("Gabby", "Vele");
        addVet("Jin", "Zindagi");
    }

    private void addOwner(String firstName, String lastName) {
        Owner owner = new Owner();
        owner.setFirstName(firstName);
        owner.setLastName(lastName);

        ownerService.save(owner);
    }

    private void addVet(String firstName, String lastName) {
        Vet vet = new Vet();
        vet.setFirstName(firstName);
        vet.setLastName(lastName);

        vetService.save(vet);
    }
}
