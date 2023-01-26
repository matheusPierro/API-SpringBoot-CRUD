package br.com.project.api.repository;

import br.com.project.api.models.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Repositories extends CrudRepository<Person, Integer> {

    List<Person> findAll();

    Person findByCode(int code);
    List<Person> findByName(String name);

    List<Person> findByOrderByName();

    List<Person> findByOrderByNameDesc();

    List<Person> findByNameOrderByAgeDesc(String name);

    List<Person> findByNameContaining(String term);

    List<Person> findByNameStartsWith(String term);

    List<Person>findByNameEndsWith(String term);

    @Query(value = "SELECT SUM(age) FROM people", nativeQuery = true)
    int ageSum();

    @Query(value = "SELECT * FROM people WHERE age >= :age", nativeQuery = true)
    List<Person> ageGreaterThan(int age);

//    @Query(value = "SELECT COUNT(*) FROM people WHERE code = :code", nativeQuery = true)
    int countByCode(int code);

}
