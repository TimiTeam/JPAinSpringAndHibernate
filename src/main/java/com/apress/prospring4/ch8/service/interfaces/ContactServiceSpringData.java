package com.apress.prospring4.ch8.service.interfaces;


import com.apress.prospring4.ch8.components.Contact;

import java.util.List;

public interface ContactServiceSpringData {

    List<Contact> findAll();

    List<Contact> findByFirstName(String firstName);

    List<Contact> findByFirstNameAndLastName(String fistName, String lastName);

}
