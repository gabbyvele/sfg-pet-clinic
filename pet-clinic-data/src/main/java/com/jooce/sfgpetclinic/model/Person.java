package com.jooce.sfgpetclinic.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public class Person extends BaseEntity {

    public String firstName;
    public String lastName;
}
