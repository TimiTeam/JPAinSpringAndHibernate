package com.apress.prospring4.ch8.components;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

@StaticMetamodel(Contact.class)
//This annotation specifies that this abstract class is a mapped essential class.
public abstract class Contaact_ {
    public static volatile SingularAttribute<Contact,Long> id;
    public static volatile SetAttribute<Contact,ContactTelDetail> contactTelDetailsSet;
    public static volatile SingularAttribute<Contact,String> firstName;
    public static volatile SingularAttribute<Contact,String> lastName;
    public static volatile SingularAttribute<Contact, Date> birthDate;
    public static volatile SetAttribute<Contact,Hobby> hobbies;
    public static volatile SingularAttribute<Contact, Integer> version;
}
