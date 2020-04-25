package com.jooce.sfgpetclinic.services;

import com.jooce.sfgpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);
}
