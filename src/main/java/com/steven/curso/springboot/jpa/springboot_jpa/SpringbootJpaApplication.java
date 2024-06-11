package com.steven.curso.springboot.jpa.springboot_jpa;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

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
		// findOne();
		// list();
		// create();
		update();

	}

	@Transactional
	public void update() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el ID");
		Long id = scanner.nextLong();
		Optional<Person> optionalPerson = repository.findById(id);

		optionalPerson.ifPresentOrElse(person -> {
			System.out.println(person);
			System.out.println("Ingrese el lenguaje de programacion");
			String programmingLanguage = scanner.next();

			person.setProgrammingLanguage(programmingLanguage);
			Person personUpdated = repository.save(person);
			System.out.println("Registro Actualizado");
			System.out.println(personUpdated);
		}, () -> {
			System.out.println("No existe el registro con el ID: " + id);
		});
		scanner.close();

	}

	@Transactional(readOnly = true)
	public void findOne() {
		// Person person = null;
		// Optional<Person> optionalPerson = repository.findById(1L);
		// if (!optionalPerson.isEmpty()) {
		// person = optionalPerson.get();

		// }
		// System.out.println(person);

		repository.findOne(1L).ifPresent(person -> System.out.println(person));

		List<Person> persons = (List<Person>) repository.findByNameContaining("mi");
		persons.stream().forEach(person -> System.out.println(person));

	}

	@Transactional
	public void create() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Input name: ");
		String name = scanner.next();
		System.out.print("Input Lastname: ");
		String lastName = scanner.next();
		System.out.print("Input Programming Language: ");
		String programmingLanguage = scanner.next();
		scanner.close();

		Person person = new Person(null, name, lastName, programmingLanguage);
		Person personNew = repository.save(person);
		System.out.println(personNew);

		repository.findById(personNew.getId()).ifPresent(p -> System.out.println(p));
	}

	@Transactional(readOnly = true)
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
