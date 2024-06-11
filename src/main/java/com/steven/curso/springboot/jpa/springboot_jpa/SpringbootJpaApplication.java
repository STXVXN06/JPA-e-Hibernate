package com.steven.curso.springboot.jpa.springboot_jpa;

import java.util.List;
import java.util.Optional;

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
		//findOne();
		create();
	}

	public void findOne(){
		// Person person = null;
		// Optional<Person> optionalPerson = repository.findById(1L);
		// if (!optionalPerson.isEmpty()) {
		// 	person = optionalPerson.get();
			
		// }
		// System.out.println(person);
		

		repository.findOne(1L).ifPresent(person -> System.out.println(person));

		 List<Person> persons = (List<Person>) repository.findByNameContaining("mi");
		 persons.stream().forEach(person -> System.out.println(person));

	}
	
	public void create(){
		Person person = new Person(null, "Sara","Lopez","Excel");
		Person personNew = repository.save(person);
		System.out.println(personNew);
	}







	public void list() {
		// List<Person> persons = (List<Person>) repository.findAll();
		// List<Person> persons = (List<Person>)
		// repository.buscarByProgrammingLanguage("C#");
		List<Person> persons = (List<Person>) repository.findByProgrammingLanguageAndName("C#", "Emily");
		persons.stream().forEach(person -> System.out.println(person));

		List<Object[]> personData = (List<Object[]>) repository.obtenerPersonData();
		personData.stream().forEach(person -> System.out.println(person[0] + " es experto en " + person[1]));

		List<Object[]> personDataWhere = (List<Object[]>) repository.obtenerPersonData("C#", "Emily");
		personDataWhere.stream().forEach(person -> System.out.println(person[0] + " es experto en " + person[1]));

	}

}
