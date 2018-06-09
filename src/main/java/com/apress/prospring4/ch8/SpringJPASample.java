package com.apress.prospring4.ch8;


import com.apress.prospring4.ch8.components.Contact;
import com.apress.prospring4.ch8.components.ContactSummary;
import com.apress.prospring4.ch8.components.ContactTelDetail;
import com.apress.prospring4.ch8.components.Hobby;
import com.apress.prospring4.ch8.service.ContactService;
import com.apress.prospring4.ch8.service.ContactSummaryService;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class SpringJPASample {
    private static final Logger LOGGER = Logger.getLogger(SpringJPASample.class);

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("app-context-annotation.xml");

        ContactSummaryService summaryUntype = context.getBean("contactSummaryUntype", ContactSummaryService.class);
        List<ContactSummary> list = summaryUntype.findAll();
//        for (ContactSummary c: list){
//            LOGGER.info(c);
//        }

        ContactService service = context.getBean("jpaContactService",ContactService.class);

        Contact contact = new Contact("Maria","Trokaz","1995-09-11");
        contact.addContactTelDetail(new ContactTelDetail("Home","5715321"));
        contact.addContactTelDetail(new ContactTelDetail("Mobile","0505124375"));
        service.save(contact);

        listWithDetail(service.finaAllWithDetail());

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
