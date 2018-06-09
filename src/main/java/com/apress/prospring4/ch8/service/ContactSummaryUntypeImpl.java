package com.apress.prospring4.ch8.service;


import com.apress.prospring4.ch8.components.ContactSummary;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Iterator;
import java.util.List;

@Service("contactSummaryUntype")
@Repository
@Transactional
public class ContactSummaryUntypeImpl implements ContactSummaryService{

    private static final Logger LOG = Logger.getLogger(ContactSummaryUntypeImpl.class);

    @PersistenceContext
    private EntityManager manager;

    @Transactional(readOnly = true)
    public void displayAllContactSummary() {

        List result = manager.createQuery("select c.firstName, c.lastName, t.telNumber from Contact c left join c.contactTelDetailSet t " +
                "where t.telType = 'Home'").getResultList();
        int count = 0;
        for (Iterator i = result.iterator(); i.hasNext(); ) {
            Object[] values = (Object[]) i.next();
            LOG.info(++count + ": " + values[0] + ", " + values[1] + ", " + values[2]);
        }
    }

    public List<ContactSummary> findAll() {
        return manager.createQuery("select new com.apress.prospring4.ch8.components.ContactSummary(c.firstName," +
                "c.lastName, t.telNumber) from Contact c left join c.contactTelDetailSet t where t.telType = 'Home'"
                ,ContactSummary.class).getResultList();
    }
}
