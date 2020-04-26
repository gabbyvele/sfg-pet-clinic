package com.jooce.sfgpetclinic.services.springdatajpa;

import com.jooce.sfgpetclinic.model.Visit;
import com.jooce.sfgpetclinic.repositories.VisitRepository;
import com.jooce.sfgpetclinic.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class VisitSDJService implements VisitService {

    private final VisitRepository visitRepository;

    public VisitSDJService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Override
    public Set<Visit> findAll() {
        Set<Visit> vets = new HashSet<>();

        visitRepository.findAll().forEach(vets::add);

        return vets;
    }

    @Override
    public Visit findById(Long id) {
        return visitRepository.findById(id).orElse(null);
    }

    @Override
    public Visit save(Visit object) {
        return visitRepository.save(object);
    }

    @Override
    public void delete(Visit object) {
        visitRepository.save(object);
    }

    @Override
    public void deleteById(Long id) {
        visitRepository.deleteById(id);
    }
}
