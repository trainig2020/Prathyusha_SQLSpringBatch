package com.prathyusha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prathyusha.model.Person;

//@Repository
public interface PersonRepository  extends JpaRepository<Person, Integer> {
    void save(List<? extends Person> persons);

}
