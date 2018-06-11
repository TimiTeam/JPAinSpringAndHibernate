package com.apress.prospring4.ch8.service.interfaces;

import com.apress.prospring4.ch8.components.Contact;

import java.util.List;

public interface ContactService {

    List<Contact> findAll();

    List<Contact> finaAllWithDetail();

    Contact findById(Long id);

    Contact save(Contact contact);

    void delete(Contact contact);

    List<Contact> findAllByNativeQuery();

    List<Contact> findByCriteriaQuery(String firstName,String lastName);

}
