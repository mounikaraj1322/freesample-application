package net.mzouabi.ng2.server.controller;

import net.mzouabi.ng2.server.dto.PersonDTO;
import net.mzouabi.ng2.server.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;


@RestController
@CrossOrigin
@RequestMapping(value = "/api/person")
public class PersonController {

    final static Logger LOG = LoggerFactory.getLogger(PersonController.class);

    @Inject
    PersonService personService;

    @GetMapping()
    public ResponseEntity<Page<PersonDTO>> findAllPerson(Pageable pageable) {
        Page<PersonDTO> page = personService.findPersons(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PersonDTO> getPerson(@PathVariable Long id) {
        return personService.getPerson(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public void createPerson(@RequestBody PersonDTO personDTO) {
        personService.savePerson(personDTO);
    }

    @PutMapping
    public void updatePerson(@RequestBody PersonDTO personDTO) {
        personService.updatePerson(personDTO);
    }

    @DeleteMapping(value = "/{id}")
    public void deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
    }
}


