package com.apress.prospring4.ch8.service;


import com.apress.prospring4.ch8.components.Contact;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service("jpaContactService")
@Repository
@Transactional
public class ContactServiceImpl implements ContactService{
    private static final Logger LOG = Logger.getLogger(ContactServiceImpl.class);

    @PersistenceContext
    private EntityManager em;


    @Transactional(readOnly = true)
    public List<Contact> findAll() {
        List<Contact> list = em.createNamedQuery("Contact.findAll",
                Contact.class).getResultList();
    return list;
    }

    public List<Contact> finaAllWithDetail() {
        return null;
    }

    public Contact findById(Long id) {
        return null;
    }

    public Contact save(Contact contact) {
        return null;
    }

    public void delete(Contact contact) {

    }
}
