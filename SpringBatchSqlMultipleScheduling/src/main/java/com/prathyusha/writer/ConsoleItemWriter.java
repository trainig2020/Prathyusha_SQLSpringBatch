package com.prathyusha.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.prathyusha.model.Person;



public class ConsoleItemWriter <T> implements ItemWriter<T> {
    @Override
    public void write(List<? extends T> items) throws Exception {
        for (T item : items) {
            Person itemnew = (Person) item;
            System.out.println(itemnew.toString());
        }
    }
}


