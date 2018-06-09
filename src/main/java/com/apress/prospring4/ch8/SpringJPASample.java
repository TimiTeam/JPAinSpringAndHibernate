package com.apress.prospring4.ch8;


import com.apress.prospring4.ch8.components.Contact;
import com.apress.prospring4.ch8.service.ContactService;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class SpringJPASample {
    private static final Logger LOGGER = Logger.getLogger(SpringJPASample.class);

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("app-context-annotation.xml");

        ContactService service = context.getBean("jpaContactService",ContactService.class);

        listAll(service.findAll());

    }
    private static void listAll(List<Contact> contacts){
        for(Contact c: contacts){
            LOGGER.info(c);
        }
    }
}
