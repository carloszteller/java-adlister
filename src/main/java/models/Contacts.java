package models;

import java.util.List;

// interface to be implemented by DAO
public interface Contacts {
    // list all the contacts List<models.Contact>
    List<Contact> getAllContacts();

    // creating a new contact
    long createContact(Contact contact);

    // deleting a contact
    void deleteContact(long id);

    // get contact by ID
    Contact getContact(long id);
}
