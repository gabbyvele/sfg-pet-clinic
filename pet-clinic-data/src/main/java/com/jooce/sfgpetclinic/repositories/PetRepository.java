package com.jooce.sfgpetclinic.repositories;

import com.jooce.sfgpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
