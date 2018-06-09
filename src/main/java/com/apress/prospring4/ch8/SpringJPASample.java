package com.apress.prospring4.ch8;


import com.apress.prospring4.ch8.components.Contact;
import com.apress.prospring4.ch8.components.ContactTelDetail;
import com.apress.prospring4.ch8.components.Hobby;
import com.apress.prospring4.ch8.service.ContactService;
import com.apress.prospring4.ch8.service.ContactSummaryUntypeImpl;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class SpringJPASample {
    private static final Logger LOGGER = Logger.getLogger(SpringJPASample.class);

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("app-context-annotation.xml");

        ContactSummaryUntypeImpl summaryUntype = context.getBean("contactSummaryUntype", ContactSummaryUntypeImpl.class);

        summaryUntype.displayAllContactSummary();

//        listAll(service.findAll());

//        listWithDetail(service.finaAllWithDetail());


    }
    private static void listAll(List<Contact> contacts){
        for(Contact c: contacts){
            LOGGER.info(c);
        }
    }
    private static void listWithDetail(List<Contact> contacts){
        for(Contact c: contacts){
            LOGGER.info(c);
            if(c.getContactTelDetailSet() != null){
                for(ContactTelDetail ctd: c.getContactTelDetailSet()){
                    LOGGER.info("-*- "+ctd);
                }
            }
            if(c.getHobbies() != null){
                for(Hobby h: c.getHobbies()){
                    LOGGER.info("-*- "+h);
                }
            }
        }
    }
}
