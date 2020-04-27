package com.jooce.sfgpetclinic.services.map;

import com.jooce.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OwnerMapServiceTest {

    private final Long ownerId = 1L;
    OwnerMapService ownerMapService;

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());

        ownerMapService.save(Owner.builder().id(ownerId).lastName("Matshabe").build());
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerMapService.findAll();

        assertEquals(owners.size(), 1);
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(ownerId);

        assertEquals(ownerId, owner.getId());
    }

    @Test
    void save() {
        Owner owner = ownerMapService.save(Owner.builder().id(2L).build());

        assertEquals(2L, owner.getId());
    }

    @Test
    @Disabled
    void deleteById() {
        ownerMapService.deleteById(ownerId);

        Set<Owner> owners = ownerMapService.findAll();

        assertEquals(owners.size(), 1);
    }

    @Test
    @Disabled
    void delete() {
        ownerMapService.deleteById(ownerId);

        Set<Owner> owners = ownerMapService.findAll();

        assertEquals(owners.size(), 1);
    }

    @Test
    void findByLastName() {
        Owner owner = ownerMapService.findByLastName("Matshabe");

        assertNotNull(owner);

        assertEquals("Matshabe", owner.getLastName());
    }
}