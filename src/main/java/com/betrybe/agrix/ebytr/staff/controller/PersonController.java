package com.betrybe.agrix.ebytr.staff.controller;

import com.betrybe.agrix.ebytr.staff.dto.PersonDto;
import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Person controller.
 */
@RestController
@RequestMapping(value = "persons")
public class PersonController {

  @Autowired
  private PersonService personService;

  /**
   * Creating person response entity.
   *
   * @param person the person
   * @return the response entity
   */
  @PostMapping
  public ResponseEntity<PersonDto> creatingPerson(@RequestBody Person person) {
    var personCreated = this.personService.create(person);
    return ResponseEntity.status(HttpStatus.CREATED).body(new PersonDto(person.getId(),
        person.getUsername(), personCreated.getRole().name()));
  }

}
