package com.apress.prospring4.ch8.service;


import com.apress.prospring4.ch8.components.Contact;
import com.apress.prospring4.ch8.service.interfaces.ContactRepository;
import com.apress.prospring4.ch8.service.interfaces.ContactServiceSpringData;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("springJpaContactService")
@Repository
@Transactional
public class ContactServiceSpringDataImpl implements ContactServiceSpringData{

    @Autowired
    private ContactRepository contactRepository;

    @Transactional(readOnly = true)
    public List<Contact> findAll() {
        return Lists.newArrayList(contactRepository.findAll());
    }

    @Transactional(readOnly = true)
    public List<Contact> findByFirstName(String firstName) {
        return contactRepository.findByFirstName(firstName);
    }

    public List<Contact> findByFirstNameAndLastName(String fistName, String lastName) {
        return contactRepository.findByFirstNameAndLastName(fistName, lastName);
    }
}
