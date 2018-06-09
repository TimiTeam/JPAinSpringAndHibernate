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

    @Transactional(readOnly = true)
    public List<Contact> finaAllWithDetail() {
        return em.createNamedQuery("Contact.findAllWithDetail",Contact.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Contact findById(Long id) {
        return em.createNamedQuery("Contact.findById",Contact.class).setParameter("id",id).getSingleResult();
    }

    public Contact save(Contact contact) {
        return null;
    }

    public void delete(Contact contact) {

    }
}
