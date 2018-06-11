package com.apress.prospring4.ch8.service.interfaces;


import com.apress.prospring4.ch8.components.Contact;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContactRepository extends CrudRepository<Contact,Long>{

    List<Contact> findByFirstName(String firstName);

    List<Contact> findByFirstNameAndLastName(String firstName, String lastName);

}
