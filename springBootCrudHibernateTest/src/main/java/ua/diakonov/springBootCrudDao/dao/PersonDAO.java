package ua.diakonov.springBootCrudDao.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ua.diakonov.springBootCrudDao.models.Person;

import java.util.List;

@Service
public class PersonDAO {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public List<Person> index() {

        return entityManager.createQuery("from " + Person.class.getName(), Person.class).getResultList();
    }

    @Transactional
    public Person show(int id) {
        return entityManager.find(Person.class, id);
    }

    @Transactional
    public void save(Person person) {
        entityManager.persist(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        Person person = entityManager.find(Person.class, id);
        if (person != null) {
            person.setName(updatedPerson.getName());
            person.setAge(updatedPerson.getAge());
            entityManager.merge(person);
        } else return;
    }

    @Transactional
    public void delete(int id) {
        Person person = entityManager.find(Person.class, id);
        if (person != null) {
            entityManager.remove(person);
        } else return;
    }
}
