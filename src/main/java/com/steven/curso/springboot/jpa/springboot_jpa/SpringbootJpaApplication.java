package com.steven.curso.springboot.jpa.springboot_jpa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.steven.curso.springboot.jpa.springboot_jpa.dto.PersonDto;
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
		// deleteById();
		// delete2();
		// getNameById();
		// obtenerPersonDataList();
		// personalizedQueries();
		// personalizedQueriesDistinct();
		// personalizedQueriesConcatUpperLowerLike();
		// between();
		// orderBy();
		// countMaxMin();
		// length();
		// getResumenFuncionesAgregacion();
		// subQuery();
		// whereIn();
	}

	@Transactional(readOnly = true)
	private void whereIn() {
		linea();
		list();
		linea();

		System.out.println("------------- Consulta WHERE IN  -------------");
		List<Person> persons = repository.getPersonsByIds(Arrays.asList(1L, 2L, 5L, 10L));
		persons.forEach(p -> System.out.println(p.getName() + " || ID: " + p.getId()));
		linea();

	}

	@Transactional(readOnly = true)
	private void subQuery() {
		linea();
		list();
		linea();

		System.out.println("------------- Consulta Length  -------------");
		List<Object[]> personLength = repository.getAllLength();
		personLength.forEach(p -> System.out.println(p[0] + " || Length: " + p[1]));
		linea();

		System.out.println("------------- Subquery  Nombre mas Largo-------------");
		List<Object[]> result = repository.getNameMaxLengthSubQuery();
		result.forEach(res -> System.out.println(res[0] + " - Length: " + res[1]));
		linea();

		System.out.println("------------- Subquery Ultimo Registro  -------------");
		Optional<Person> person = repository.getUltimoRegistro();
		person.ifPresent(p -> System.out.println(p));
		linea();
	}

	@Transactional(readOnly = true)
	private void getResumenFuncionesAgregacion() {
		linea();
		list();
		linea();

		System.out.println("------------- Resumen Consultas de Agregacion  -------------");
		Object[] result = (Object[]) repository.getResumenFuncionesAgregacion();
		System.out.println("MIN ID: " + result[0]);
		System.out.println("MAX ID: " + result[1]);
		System.out.println("SUM ID: " + result[2]);
		System.out.println("Promedio cantidad de caracteres en Name: " + result[3]);
		System.out.println("Cantidad de registros: " + result[4]);
		linea();
	}

	@Transactional(readOnly = true)
	private void length() {
		linea();
		list();
		linea();

		System.out.println("------------- Consulta Length  -------------");
		List<Object[]> personLength = repository.getAllLength();
		personLength.forEach(p -> System.out.println(p[0] + " || Length: " + p[1]));
		linea();

		System.out.println("------------- Consulta Name Max Length  -------------");
		Integer maxLength = repository.getNameMaxLength();
		System.out.println("Mayor cantidad de caracteres en un Nombre: " + maxLength);
		linea();

		System.out.println("------------- Consulta Name Min Length  -------------");
		Integer minLength = repository.getNameMinLength();
		System.out.println("Menor cantidad de caracteres en un Nombre: " + minLength);
		linea();
	}

	@Transactional(readOnly = true)
	private void countMaxMin() {
		linea();
		list();
		linea();

		System.out.println("------------- Consulta COUNT  -------------");
		Long countReg = repository.countReg();
		System.out.println("Cantidad de registros: " + countReg);
		linea();

		System.out.println("------------- Consulta MIN  -------------");
		Long minId = repository.findMinId();
		System.out.println("Id menor: " + minId);
		linea();

		System.out.println("------------- Consulta MAX  -------------");
		Long maxId = repository.findMaxId();
		System.out.println("Id mayor: " + maxId);
		linea();
	}

	@Transactional(readOnly = true)
	private void orderBy() {
		linea();
		list();
		linea();

		System.out.println("------------- Consulta Order by  -------------");
		List<Person> persons = repository.findAllOrderBy();
		persons.forEach(person -> System.out.println(person));
		linea();

		System.out.println("------------- Consulta Order by Query Method  -------------");
		persons = repository.findAllByOrderByNameAscLastnameDesc();
		persons.forEach(person -> System.out.println(person));
		linea();
	}

	@Transactional(readOnly = true)
	private void between() {
		linea();
		list();
		linea();

		System.out.println("------------- Consulta BETWEEN ID -------------");
		List<Person> persons = repository.findAllBetweenId();
		persons.forEach(person -> System.out.println(person));
		linea();

		System.out.println("------------- Consulta BETWEEN name -------------");
		persons = repository.findAllBetweenName();
		persons.forEach(person -> System.out.println(person));
		linea();

		System.out.println("------------- Consulta BETWEEN con Query Method -------------");
		persons = repository.findByNameBetween("K", "SS");
		persons.forEach(person -> System.out.println(person));
		linea();
	}

	@Transactional(readOnly = true)
	private void personalizedQueriesConcatUpperLowerLike() {
		linea();
		list();
		linea();

		System.out.println("------------- Consulta Concat Name y Lastname -------------");
		List<String> names = repository.findAllFullNameConcat();
		names.forEach(name -> System.out.println(name));
		linea();

		System.out.println("------------- FullName Upper -------------");
		names = repository.findAllFullNameConcatUpper();
		names.forEach(name -> System.out.println(name));
		linea();

		System.out.println("------------- FullName Lower -------------");
		names = repository.findAllFullNameConcatLower();
		names.forEach(name -> System.out.println(name));
		linea();

		System.out.println("------------- FullName Case -------------");
		List<Person> personsCase = repository.findAllPersonCase();
		personsCase.forEach(person -> System.out.println(person));
		linea();

	}

	@Transactional(readOnly = true)
	private void personalizedQueriesDistinct() {
		linea();
		list();
		linea();

		System.out.println("------------- Consulta Nombres -------------");
		List<String> names = repository.findAllNames();
		names.forEach(name -> System.out.println(name));
		linea();

		System.out.println("------------- Consulta Lenguajes -------------");
		List<String> languages = repository.findAllProgrammingLanguages();
		languages.forEach(language -> System.out.println(language));
		linea();

		System.out.println("------------- Consulta Cantidad Nombres -------------");
		Long countNames = repository.findAllNamesCount();
		System.out.println("Cantidad de nombres: " + countNames);
		linea();
	}

	@Transactional(readOnly = true)
	private void personalizedQueries() {
		System.out.println("------------- Consulta por objeto Persona y Lenguaje de Programacion -------------");
		List<Object[]> persons = repository.findAllMixPerson();
		persons.forEach(person -> System.out.println("Lenguaje: " + person[1] + " || Objeto: " + person[0]));

		System.out.println("------------- Consulta instanciando entidad en el query -------------");
		List<Person> personsEntity = repository.findAllPerson();
		personsEntity.forEach(person -> System.out.println(person));

		System.out.println("------------- Consulta instanciando DTO en el query -------------");
		List<PersonDto> personsDto = repository.findAllPersonDto();
		personsDto.forEach(person -> System.out.println(person));
	}

	@Transactional(readOnly = true)
	private void obtenerPersonDataList() {
		linea();
		list();
		linea();
		List<Object[]> persons = repository.obtenerPersonDataList();
		persons.forEach(per -> System.out.println("Id: " + per[0] + " Name: " + per[1] +
				" Programming Language: " + per[2]));
		linea();
	}

	@Transactional(readOnly = true)
	private void getNameById() {
		list();
		linea();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el ID del registro para ver el nombre");
		Long id = scanner.nextLong();
		scanner.close();

		String name = repository.getFullName(id);
		if (name == null) {
			System.out.println("No existe un registro con el ID '" + id + "'");
		} else {
			System.out.println("El ID '" + id + "' pertenece a " + name);
		}
		linea();
	}

	@Transactional
	public void delete2() {
		list();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el ID del registro a eliminar");
		Long id = scanner.nextLong();
		Optional<Person> optionalPerson = repository.findById(id);
		optionalPerson.ifPresentOrElse(person -> {

			System.out.println("Registro a eliminar");
			System.out.println(person);
			repository.delete(person);
			System.out.println("Registro eliminado");

		}, () -> {
			System.out.println("No existe el registro con el ID: " + id);
		});
		list();
		scanner.close();
	}

	@Transactional
	public void deleteById() {
		list();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el ID del registro a eliminar");
		Long id = scanner.nextLong();
		Optional<Person> optionalPerson = repository.findById(id);
		optionalPerson.ifPresentOrElse(person -> {

			System.out.println("Registro a eliminar");
			System.out.println(person);
			repository.deleteById(id);
			System.out.println("Registro eliminado");
			list();
		}, () -> {
			System.out.println("No existe el registro con el ID: " + id);
		});
		scanner.close();
	}

	@Transactional
	public void update() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el ID del registro a actualizar");
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
		/*
		 * List<Person> persons = (List<Person>)
		 * repository.findByProgrammingLanguageAndName("C#", "Emily");
		 * persons.stream().forEach(person -> System.out.println(person));
		 * 
		 * List<Object[]> personData = (List<Object[]>) repository.obtenerPersonData();
		 * personData.stream().forEach(person -> System.out.println(person[0] +
		 * " es experto en " + person[1]));
		 * 
		 * List<Object[]> personDataWhere = (List<Object[]>)
		 * repository.obtenerPersonData("C#", "Emily");
		 * personDataWhere.stream().forEach(person -> System.out.println(person[0] +
		 * " es experto en " + person[1]));
		 */
		System.out.println("------------- LISTA COMPLETA -------------");
		List<Person> persons = (List<Person>) repository.findAll();
		persons.stream().forEach(person -> System.out.println(person));
	}

	public void linea() {

		System.out.println("------------------------------------------------");
	}

}
