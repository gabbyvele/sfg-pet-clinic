package com.jooce.sfgpetclinic.services;

import com.jooce.sfgpetclinic.model.Owner;
import com.jooce.sfgpetclinic.model.Vet;

import java.util.Set;

public interface VetService {

    Vet findById(Long id);

    Vet save(Vet vet);

    Set<Owner> findAll();
}
