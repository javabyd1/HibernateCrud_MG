package com.no_company.hibernate.implement;

import com.no_company.hibernate.interfaces.UserDAO;
import com.no_company.hibernate.model.UserEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

import static com.no_company.hibernate.util.HibernateUtility.getConfiguredSession;

public class UserDAOImp implements UserDAO {

    @Override
    public Integer addPerson(UserEntity person) {
        Transaction tx = null;
        Integer personID = null;
        try (Session session = getConfiguredSession()) {
            tx = session.beginTransaction();
            personID = (Integer) session.save(person);
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) tx.rollback();
            System.out.println("Adding person failed");
            he.printStackTrace();
        }
        return personID;
    }

    @Override
    public Integer addPeople(List<UserEntity> people) {
        Integer lastID = null;
        for (UserEntity person : people) {
            lastID = addPerson(person);
        }
        return lastID;
    }

    @Override
    public List<UserEntity> getAllPeople() {
        Transaction tx = null;
        List<UserEntity> people = null;
        try (Session session = getConfiguredSession() ) {
            tx = session.beginTransaction();
            people = session.createQuery("FROM UserEntity").list();
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) tx.rollback();
            he.printStackTrace();
        }
        return people;
    }

    @Override
    public UserEntity getPerson(int personID) {
        Transaction tx = null;
        UserEntity person = null;
        try (Session session = getConfiguredSession() ) {
            tx = session.beginTransaction();
            person = session.get(UserEntity.class, personID);
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) tx.rollback();
            he.printStackTrace();
        }
        return person;
    }

    @Override
    public List<UserEntity> getPeopleByName(String firstName) {
        List<UserEntity> peopleSameNames = new ArrayList<>();
        List<UserEntity> people = getAllPeople();
        for (UserEntity person : people) {
            if (person.getName().contains(firstName)) peopleSameNames.add(person);
        }
        return peopleSameNames;
    }

    @Override
    public List<UserEntity> getPeopleBySurname(String lastName) {
        List<UserEntity> peopleSameNames = new ArrayList<>();
        List<UserEntity> people = getAllPeople();
        for (UserEntity person : people) {
            if (person.getName().contains(lastName)) peopleSameNames.add(person);
        }
        return peopleSameNames;
    }

    @Override
    public int removePerson(int personID) {
        int removedPeopleCounter = 0;
        Transaction tx = null;
        try (Session session = getConfiguredSession() ) {
            tx = session.beginTransaction();

            UserEntity person = session.get(UserEntity.class, personID);
            try {
                session.delete(person);
                removedPeopleCounter = 1;
            } catch (IllegalArgumentException iae) {
                removedPeopleCounter = 0;
            }

            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) tx.rollback();
            he.printStackTrace();
        }
        return removedPeopleCounter;
    }

    @Override
    public int removeAllPeople() {
        Transaction tx = null;
        List<UserEntity> peopleToRemove;
        int removedPeopleCounter = 0;
        try (Session session = getConfiguredSession() ) {
            tx = session.beginTransaction();
            peopleToRemove = session.createQuery("FROM UserEntity").list();

            for (UserEntity person : peopleToRemove) {
                session.delete(person);
                removedPeopleCounter++;
            }

            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) tx.rollback();
            he.printStackTrace();
        }
        return removedPeopleCounter;
    }

    @Override
    public int removePeopleByName(String name) {
        Transaction tx = null;
        List<UserEntity> peopleToRemove;
        int removedPeopleCounter = 0;
        try (Session session = getConfiguredSession() ) {
            tx = session.beginTransaction();
            peopleToRemove = session.createQuery("FROM UserEntity WHERE name = '" + name + "'").list();

            for (UserEntity person : peopleToRemove) {
                session.delete(person);
                removedPeopleCounter++;
            }

            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) tx.rollback();
            he.printStackTrace();
        }
        return removedPeopleCounter;
    }

    @Override
    public int removePeopleBySurname(String surname) {
        Transaction tx = null;
        List<UserEntity> peopleToRemove;
        int removedPeopleCounter = 0;
        try (Session session = getConfiguredSession() ) {
            tx = session.beginTransaction();
            peopleToRemove = session.createQuery("FROM UserEntity WHERE surname = '" + surname + "'").list();

            for (UserEntity person : peopleToRemove) {
                session.delete(person);
                removedPeopleCounter++;
            }

            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) tx.rollback();
            he.printStackTrace();
        }
        return removedPeopleCounter;
    }
}
