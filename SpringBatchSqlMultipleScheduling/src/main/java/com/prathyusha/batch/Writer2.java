package com.prathyusha.batch;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.prathyusha.model.Person;

//@Component
public class Writer2 implements ItemWriter<String> {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void write(List<? extends String> items) throws Exception {
		for(String query: items) {
			System.out.println("Query is :" +query);
			Connection conn= jdbcTemplate.getDataSource().getConnection();
			Statement stmt= conn.createStatement();
			stmt.executeUpdate(query);
		}
		
	}
	
	

}
