package com.steven.curso.springboot.jpa.springboot_jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.steven.curso.springboot.jpa.springboot_jpa.entities.Person;
import com.steven.curso.springboot.jpa.springboot_jpa.repositories.PersonRepository;

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner {

	@Autowired
	private PersonRepository repository;
	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		// List<Person> persons = (List<Person>) repository.findAll();	
		// List<Person> persons = (List<Person>) repository.buscarByProgrammingLanguage("C#");	
		List<Person> persons = (List<Person>) repository.findByProgrammingLanguageAndName("C#", "Emily");	
		persons.stream().forEach(person -> System.out.println(person));	
		
		
		List<Object[]> personData = (List<Object[]>) repository.obtenerPersonData();	
		personData.stream().forEach(person -> System.out.println(person[0] + " es experto en "+ person[1]));	
	
		List<Object[]> personDataWhere = (List<Object[]>) repository.obtenerPersonData("C#","Emily");	
		personDataWhere.stream().forEach(person -> System.out.println(person[0] + " es experto en "+ person[1]));	
	}


}
