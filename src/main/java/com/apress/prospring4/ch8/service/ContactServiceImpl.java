package com.apress.prospring4.ch8.service;


import com.apress.prospring4.ch8.components.Contact;
import com.apress.prospring4.ch8.components.Contaact_;
import com.apress.prospring4.ch8.service.interfaces.ContactService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.List;

@Service("jpaContactService")
@Repository
@Transactional
public class ContactServiceImpl implements ContactService {
    private static final Logger LOG = Logger.getLogger(ContactServiceImpl.class);

    private static final String ALL_CONTACT_NATIVE_QUERY = "SELECT* FROM contact;";

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
        if(contact.getId() == null){
            LOG.info("Insert a new contact ");
            em.persist(contact);
        }else {
            em.merge(contact);
            LOG.info("Update exist contact");
        }
        LOG.info("Saved object with id "+contact.getId());

        return contact;
    }

    public void delete(Contact contact) {
        em.remove(em.merge(contact));
        LOG.info("Contact with id: "+contact.getId()+" was delete");
    }

    @Transactional(readOnly = true)
    public List<Contact> findAllByNativeQuery() {
        return em.createNativeQuery(ALL_CONTACT_NATIVE_QUERY,"contactResult").getResultList();
    }

    @Transactional(readOnly = true)
    public List<Contact> findByCriteriaQuery(String firstName, String lastName) throws NullPointerException{
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Contact> query = criteriaBuilder.createQuery(Contact.class);
//       Generates a typed request

        Root<Contact> contactRoot = query.from(Contact.class);
//       The request root object (the Root <Contact> interface) corresponding to the specified entity.
//  The query root object forms the basis for the path expressions within the query.

        contactRoot.fetch(Contaact_.contactTelDetailsSet, JoinType.LEFT);
        contactRoot.fetch(Contaact_.hobbies,JoinType.LEFT);
//       Provide a selection of associations related to telephones and hobbies.
//  The JoinType.LEFT argument specifies an external connection.
//  Calling the method is equivalent to specifying the left join fetch join operation in JPQL.

        query.select(contactRoot).distinct(true);
//       Transfer object as the resultant type.
//  A distinct () with true means that duplicate entries must be eliminated.

        Predicate criteria = criteriaBuilder.conjunction();
//       Here the predicate is a constraint that indicates the sampling criterion defined by the expression.

        if(firstName != null){
            Predicate predicate = criteriaBuilder.equal(contactRoot.get(Contaact_.firstName), firstName);
//        The equal () method is used to specify the equality constraint

            criteria = criteriaBuilder.and(criteria,predicate);
//        The constructed predicate is combined with the existing predicate

        }
        if(lastName != null){
            Predicate predicate = criteriaBuilder.equal(contactRoot.get(Contaact_.lastName),lastName);
            criteria = criteriaBuilder.and(criteria,predicate);
        }
        query.where(criteria);

        return em.createQuery(query).getResultList();
    }
}
