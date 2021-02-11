package com.udacity.jdnd.course3.critter.user;

import javax.persistence.*;

@MappedSuperclass
//no polymorphic queries
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
