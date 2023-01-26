package br.com.project.api.controllers;

import br.com.project.api.models.Client;
import br.com.project.api.models.Person;
import br.com.project.api.repository.Repositories;
import br.com.project.api.services.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    private Repositories action;

    @Autowired
    private Services service;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Person obj) {
        return service.register(obj);
    }

    @GetMapping("/register")
    public ResponseEntity<?> select() {
        return service.selectAll();
    }

    @GetMapping("/selectbycode/{code}")
    public ResponseEntity<?> selectByCode(@PathVariable int code){
        return service.selectByCode(code);
    }

    @GetMapping("/selectbyname/{name}")
    public List<Person> selectByName(@PathVariable String name){
        return action.findByName(name);
    }

    @PutMapping("/edit")
    public ResponseEntity<?> edit(@RequestBody Person obj){
        return service.edit(obj);
    }

    @DeleteMapping("/remove/{code}")
    public ResponseEntity<?> remove(@PathVariable int code){
        return service.remove(code);
    }

    @GetMapping("/count")
    public long count(){
        return action.count();
    }

    @GetMapping("/orderbyname")
    public List<Person> orderByName(){
        return action.findByOrderByName();
    }

    @GetMapping("/orderbynamedesc")
    public List<Person> orderByNameDesc(){
        return action.findByOrderByNameDesc();
    }

    @GetMapping("/selectbynameorderbyagedesc/{name}")
    public List<Person> findByNameOrderByAgeDesc(@PathVariable String name){
        return action.findByNameOrderByAgeDesc(name);
    }

    @GetMapping("/selectbynamecontaining/{term}")
    public List<Person> nameContaining(@PathVariable String term){
        return action.findByNameContaining(term);
    }

    @GetMapping("/selectbynamestartswith/{term}")
    public List<Person> nameStartsWith(@PathVariable String term){
        return action.findByNameStartsWith(term);
    }

    @GetMapping("/selectbynameendswith/{term}")
    public List<Person> nameEndsWith(@PathVariable String term){
        return action.findByNameEndsWith(term);
    }

    @GetMapping("/agesum")
    public int ageSum(){
        return action.ageSum();
    }

@GetMapping("/agegreaterthan/{age}")
public List<Person> ageGreaterThan(@PathVariable int age){
        return action.ageGreaterThan(age);
}

@GetMapping("/status")
public ResponseEntity<?> status(){
        return new ResponseEntity<>(HttpStatus.CREATED);
}

@PostMapping("/client")
public void client(@Valid @RequestBody Client obj){

}

















    @GetMapping("")
    public String message() {
        return "Hello World";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome";
    }

    @GetMapping("/welcome/{name}")
    public String welcome(@PathVariable String name) {
        return "Welcome " + name;
    }

    @PostMapping("/person")
    public Person person(@RequestBody Person p) {
        return p;
    }

}
