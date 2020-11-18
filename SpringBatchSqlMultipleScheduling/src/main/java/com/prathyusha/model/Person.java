package com.prathyusha.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Person {
	
	    @Id
	    private Integer id;
	    private String name;
	    private String phoneNo;
	  
	    
	    
		public Person(Integer id, String name, String phoneNo) {
			super();
			this.id = id;
			this.name = name;
			this.phoneNo = phoneNo;
		
		}
		
		
		public Person() {
			
		}

		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPhoneNo() {
			return phoneNo;
		}
		public void setPhoneNo(String phoneNo) {
			this.phoneNo = phoneNo;
		}


		@Override
		public String toString() {
			return "Person [id=" + id + ", name=" + name + ", phoneNo=" + phoneNo + "]";
		}

	

}
