package org.trwib.service;

import org.trwib.dto.PersonDTO;
import org.trwib.dto.SearchDTO;
import org.trwib.dto.SearchType;
import org.trwib.model.Person;
import org.trwib.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * This implementation of the PersonService interface communicates with
 * the database by using a Spring Data JPA repository.
 * @author Petri Kainulainen
 */
@Service
public class RepositoryPersonService implements PersonService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryPersonService.class);
    
    @Resource
    private PersonRepository personRepository;

    @Transactional
    @Override
    public Person create(PersonDTO created) {
        LOGGER.debug("Creating a new person with information: " + created);
        
        Person person = Person.getBuilder(created.getFirstName(), created.getLastName()).build();
        
        return personRepository.save(person);
    }

    @Transactional(rollbackFor = PersonNotFoundException.class)
    @Override
    public Person delete(Long personId) throws PersonNotFoundException {
        LOGGER.debug("Deleting person with id: " + personId);
        
        Person deleted = personRepository.findOne(personId);
        
        if (deleted == null) {
            LOGGER.debug("No person found with id: " + personId);
            throw new PersonNotFoundException();
        }
        
        personRepository.delete(deleted);
        return deleted;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Person> findAll() {
        LOGGER.debug("Finding all persons");
        return personRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Person findById(Long id) {
        LOGGER.debug("Finding person by id: " + id);
        return personRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Person> search(SearchDTO searchCriteria) {
        LOGGER.debug("Searching persons with search criteria: " + searchCriteria);
        
        String searchTerm = searchCriteria.getSearchTerm();
        SearchType searchType = searchCriteria.getSearchType();
        
        if (searchType == null) {
            throw new IllegalArgumentException();
        }
         
        return findPersonsBySearchType(searchTerm, searchType);
    }
    
    private List<Person> findPersonsBySearchType(String searchTerm, SearchType searchType) {
        List<Person> persons;

        if (searchType == SearchType.METHOD_NAME) {
            LOGGER.debug("Searching persons by using method name query creation.");
            persons = personRepository.findByLastName(searchTerm);
        }
        else if (searchType == SearchType.NAMED_QUERY) {
            LOGGER.debug("Searching persons by using named query");
            persons = personRepository.findByName(searchTerm);
        }
        else {
            LOGGER.debug("Searching persons by using query annotation");
            persons = personRepository.find(searchTerm);
        }

        return persons;
    }

    @Transactional(rollbackFor = PersonNotFoundException.class)
    @Override
    public Person update(PersonDTO updated) throws PersonNotFoundException {
        LOGGER.debug("Updating person with information: " + updated);
        
        Person person = personRepository.findOne(updated.getId());
        
        if (person == null) {
            LOGGER.debug("No person found with id: " + updated.getId());
            throw new PersonNotFoundException();
        }
        
        person.update(updated.getFirstName(), updated.getLastName());

        return person;
    }

    /**
     * This setter method should be used only by unit tests.
     * @param personRepository
     */
    protected void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
}
