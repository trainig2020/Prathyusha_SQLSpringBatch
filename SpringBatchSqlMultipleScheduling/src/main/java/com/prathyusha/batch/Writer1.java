package com.prathyusha.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prathyusha.model.Person;
import com.prathyusha.repository.PersonRepository;

//@Component
public class Writer1 implements ItemWriter<Person>{
	
	  @Autowired
	    private PersonRepository personRepository;

	    @Override
	    public void write(List<? extends Person> persons) throws Exception {

	        System.out.println("Data Saved for Persons: " + persons);
	        personRepository.saveAll(persons);
	    }

}
