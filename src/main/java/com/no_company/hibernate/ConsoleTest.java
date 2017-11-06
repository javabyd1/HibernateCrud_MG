package com.no_company.hibernate;

import com.no_company.hibernate.implement.UserDAOImp;
import com.no_company.hibernate.interfaces.UserDAO;
import com.no_company.hibernate.model.UserEntity;
import com.no_company.hibernate.util.HibernateUtility;

import java.util.ArrayList;
import java.util.List;

public class ConsoleTest {

    public static void main(String[] args) {

        UserEntity person1 = new UserEntity();
        person1.setName("Zwierz");
        person1.setSurname("Zdrow");

        UserEntity person2 = new UserEntity();
        person2.setName("Abdul");
        person2.setSurname("Alhazred");

        List<UserEntity> testSubjects = new ArrayList<>();
        testSubjects.add(person1);
        testSubjects.add(person2);
        
        UserDAO dao = new UserDAOImp();


        HibernateUtility.closeSessionFactory();
    }
}
