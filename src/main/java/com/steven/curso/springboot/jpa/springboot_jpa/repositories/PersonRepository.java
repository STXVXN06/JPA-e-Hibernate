package com.steven.curso.springboot.jpa.springboot_jpa.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.steven.curso.springboot.jpa.springboot_jpa.dto.PersonDto;
import com.steven.curso.springboot.jpa.springboot_jpa.entities.Person;
import java.util.List;
import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Long> {

    @Query("select p from Person p where p.id not in ?1")
    List<Person> getPersonsByIds(List<Long> ids);

    @Query("select p from Person p where p.id = (select max(subp.id) from Person subp)")
    Optional<Person> getUltimoRegistro();

    @Query("select p.name, length(p.name) from Person p where length(p.name) = (select max(length(subp.name)) from Person subp)")
    List<Object[]> getNameMaxLengthSubQuery();

    @Query("select min(p.id), max(p.id), sum(p.id), avg(length(p.name)), count(p.id) from Person p")
    Object getResumenFuncionesAgregacion();

    @Query("select min(length(p.name)) from Person p ")
    Integer getNameMinLength();

    @Query("select max(length(p.name)) from Person p ")
    Integer getNameMaxLength();

    @Query("select p.name, length(p.name) from Person p")
    List<Object[]> getAllLength();

    @Query("select max(p.id) from Person p")
    Long findMaxId();

    @Query("select min(p.id) from Person p")
    Long findMinId();

    @Query("select count(p) from Person p")
    Long countReg();

    List<Person> findAllByOrderByNameAscLastnameDesc();

    List<Person> findByIdBetweenOrderByNameDesc(Long id1, Long id2);

    @Query("select p from Person p order by p.name, p.lastname desc")
    List<Person> findAllOrderBy();

    List<Person> findByNameBetween(String rangoInicial, String rangoFinal);

    @Query("select p from Person p where p.name between 'K' and 'T'")
    List<Person> findAllBetweenName();

    @Query("select p from Person p where p.id between 2 and 5")
    List<Person> findAllBetweenId();

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
