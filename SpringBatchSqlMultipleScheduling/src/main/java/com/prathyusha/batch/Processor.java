package com.prathyusha.batch;

import java.util.Date;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.prathyusha.model.Person;

//@Component
public class Processor  implements ItemProcessor<Person, Person> {

	@Override
	public Person process(Person person) throws Exception {
		 //person.setTime(new Date());
	       System.out.println("entered into the processor");
	        return person;
	}

}
