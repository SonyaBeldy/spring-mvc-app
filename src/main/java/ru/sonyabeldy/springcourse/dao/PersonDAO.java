package ru.sonyabeldy.springcourse.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.sonyabeldy.springcourse.models.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

    }

    @Transactional(readOnly = true)
    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();

        List<Person> people = session.createQuery("select p from Person p", Person.class)
                .getResultList();
        return people;
    }

    @Transactional(readOnly = true)
    public Optional<Person> show(String email) {
        Session session = sessionFactory.getCurrentSession();
        Optional<Person> person = session.createQuery("select p from Person p where p.email=:email", Person.class)
                .setParameter("email", email)
                .stream().findAny();
        return person;
    }

    @Transactional(readOnly = true)
    public Person show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class, id);
    }

    @Transactional
    public void save(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        Session session = sessionFactory.getCurrentSession();

        Person person = session.get(Person.class, id);
        person.update(updatedPerson);
        session.update(person);
    }

    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, id);
        session.delete(person);
    }

    private List<Person> create1000People() {
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            people.add(new Person(i, "Name" + i, 30, "test" + i + "mail.ru", " some address"));
        }

        return people;
    }
}
