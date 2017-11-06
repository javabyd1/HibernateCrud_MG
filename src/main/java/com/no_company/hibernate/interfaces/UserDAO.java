package com.no_company.hibernate.interfaces;

import com.no_company.hibernate.model.UserEntity;

import java.util.List;

public interface UserDAO {

    Integer addPerson(UserEntity person);
    Integer addPeople(List<UserEntity> people);

    UserEntity getPerson(int personID);
    List<UserEntity> getAllPeople();
    List<UserEntity> getPeopleByName(String name);
    List<UserEntity> getPeopleBySurname(String surname);

    int removePerson(int personID);
    int removeAllPeople();
    int removePeopleByName(String name);
    int removePeopleBySurname(String surname);

}
