package ru.sonyabeldy.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.sonyabeldy.springcourse.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    public List<Person> index() {
        return null;
    }

    public Optional<Person> show(String email) {
        return null;
    }

    public Person show(int id) {
        return null;
    }

    public void save(Person person) {
    }

    public void update(int id, Person updatedPerson) {
    }

    public void delete(int id) {
    }

    private List<Person> create1000People() {
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            people.add(new Person(i, "Name" + i, 30, "test" + i + "mail.ru", " some address"));
        }

        return people;
    }
}
