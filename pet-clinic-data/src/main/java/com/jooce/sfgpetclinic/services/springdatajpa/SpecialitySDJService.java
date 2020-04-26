package com.jooce.sfgpetclinic.services.springdatajpa;

import com.jooce.sfgpetclinic.model.Speciality;
import com.jooce.sfgpetclinic.repositories.SpecialityRepository;
import com.jooce.sfgpetclinic.services.SpecialityService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class SpecialitySDJService implements SpecialityService {

    private final SpecialityRepository specialityRepository;

    public SpecialitySDJService(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }


    @Override
    public Set<Speciality> findAll() {
        Set<Speciality> pets = new HashSet<>();

        specialityRepository.findAll().forEach(pets::add);

        return pets;
    }

    @Override
    public Speciality findById(Long id) {
        return specialityRepository.findById(id).orElse(null);
    }

    @Override
    public Speciality save(Speciality object) {
        return specialityRepository.save(object);
    }

    @Override
    public void delete(Speciality object) {
        specialityRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        specialityRepository.deleteById(id);
    }
}
