package com.steven.curso.springboot.jpa.springboot_jpa.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.steven.curso.springboot.jpa.springboot_jpa.dto.PersonDto;
import com.steven.curso.springboot.jpa.springboot_jpa.entities.Person;
import java.util.List;
import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Long> {

    @Query("select new Person(upper(p.name),lower(p.lastname)) from Person p ")
    List<Person> findAllPersonCase();

    @Query("select lower( p.name || ' ' || p.lastname ) from Person p")
    List<String> findAllFullNameConcatLower();

    @Query("select upper(concat(p.name, ' ', p.lastname )) from Person p")
    List<String> findAllFullNameConcatUpper();

    // @Query("select concat(p.name, ' ', p.lastname ) from Person p")
    @Query("select p.name || ' ' || p.lastname from Person p")
    List<String> findAllFullNameConcat();

    @Query("select count(distinct(p.name)) from Person p")
    Long findAllNamesCount();

    @Query("select distinct(p.programmingLanguage) from Person p")
    List<String> findAllProgrammingLanguages();

    @Query("select distinct(p.name) from Person p")
    List<String> findAllNames();

    @Query("select new com.steven.curso.springboot.jpa.springboot_jpa.dto.PersonDto(p.name, p.lastname) from Person p ")
    List<PersonDto> findAllPersonDto();

    @Query("select new Person(p.name, p.lastname) from Person p ")
    List<Person> findAllPerson();

    @Query("select p, p.programmingLanguage from Person p ")
    List<Object[]> findAllMixPerson();

    @Query("select p.id, p.name,  p.programmingLanguage from Person p ")
    List<Object[]> obtenerPersonDataList();

    @Query("select concat(p.name, ' ', p.lastname) from Person p where p.id = ?1")
    String getFullName(Long id);

    @Query("select p.name from Person p where p.id = ?1")
    String getNameById(Long id);

    @Query("select p from Person p where p.id=?1")
    Optional<Person> findOne(Long id);

    @Query("select p from Person p where p.name=?1")
    List<Person> findOne(String name);

    @Query("select p from Person p where p.name like %?1%")
    List<Person> findLikeName(String name);

    List<Person> findByNameContaining(String name);

    List<Person> findByProgrammingLanguage(String programmingLanguage);

    @Query("select p from  Person p where p.programmingLanguage=?1")
    List<Person> buscarByProgrammingLanguage(String programmingLanguage);

    List<Person> findByProgrammingLanguageAndName(String programmingLanguage, String name);

    @Query("select p.name, p.programmingLanguage from Person p")
    List<Object[]> obtenerPersonData();

    @Query("select p.name, p.programmingLanguage from Person p where p.programmingLanguage=?1 and p.name=?2")
    List<Object[]> obtenerPersonData(String pLanguage, String name);

}
