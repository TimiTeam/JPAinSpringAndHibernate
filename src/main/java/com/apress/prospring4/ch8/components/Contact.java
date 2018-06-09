package com.apress.prospring4.ch8.components;


import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "contact")
@NamedQueries({
        @NamedQuery(name = "Contact.findAll",query = "select c from Contact c"),
        @NamedQuery(name = "Contact.findAllById",
                query = "select distinct c from Contact c left join fetch c.contactTelDetailSet t " +
                        "left join fetch c.hobbies h where c.id = :id"),
        @NamedQuery(name = "Contact.findAllWithDetail",
                query = "select distinct c from Contact c left join fetch c.contactTelDetailSet t " +
                        "left join fetch c.hobbies h")
})
public class Contact {
    private Long id;
    private int version;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private Set<ContactTelDetail> contactTelDetailSet = new HashSet<ContactTelDetail>();
    private Set<Hobby> hobbies = new HashSet<Hobby>();

    public Contact() {
    }

    public Contact(String firstName, String lastName, Date birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public Contact(String firstName, String lastName, String birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.birthDate = format.parse(birthDate);
        } catch (ParseException e) {
            this.birthDate = new Date(GregorianCalendar.getInstance().getTimeInMillis());
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Version
    @Column(name = "version")
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date")
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL,
    orphanRemoval = true)
    public Set<ContactTelDetail> getContactTelDetailSet() {
        return contactTelDetailSet;
    }

    public void setContactTelDetailSet(Set<ContactTelDetail> contactTelDetailSet) {
        this.contactTelDetailSet = contactTelDetailSet;
    }

    @ManyToMany
    @JoinTable(name = "contact_hobby_detail",
    joinColumns =@JoinColumn(name = "contact_id"),
    inverseJoinColumns = @JoinColumn(name = "hobby_id"))
    public Set<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(Set<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    public void addContactTelDetail(ContactTelDetail contactTelDetail){
        contactTelDetail.setContact(this);
        getContactTelDetailSet().add(contactTelDetail);
    }

    public void removeContactTelDetail(ContactTelDetail contactTelDetail){
        getContactTelDetailSet().remove(contactTelDetail);
    }

    @Override
    public String toString() {
        return "Contact with" +
                " ID: " + id +
                ", version - " + version +
                ", name - " + firstName +
                ", last name - " + lastName  +
                ", birthday - " + birthDate;
    }
}
