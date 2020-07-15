package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public void addPerson(@Valid @NotNull @RequestBody Person person) {
        personService.addPerson(person);
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAllPeople() {
        return ResponseEntity.ok().body(personService.getAllPeople());
    }

    @GetMapping(path = "/{id}")
    public Person getPersonById(@PathVariable("id") UUID id) {
        return personService.getPersonById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "/{id}")
    public void deletePersonById(@PathVariable("id") UUID id) {
        personService.deletePerson(id);
    }

    @PutMapping(path = "/{id}")
    public void updatePerson(@PathVariable("id") UUID id, @Valid @NotNull @RequestBody Person personToUpdate) {
        personService.updatePerson(id, personToUpdate);
    }

    /**
     * 自己測試玩的！
     **/
    @GetMapping("/single")
    public ResponseEntity<Person> getPersonById2(@RequestParam Map<String, String> queryMap) {
        if (queryMap.containsKey("id")) {
            UUID id = UUID.fromString(queryMap.get("id"));
            Optional<Person> foundPerson = personService.getPersonById(id);
            if (foundPerson.isPresent()) {
                return ResponseEntity.ok().body(foundPerson.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.notFound().build();
    }
}
