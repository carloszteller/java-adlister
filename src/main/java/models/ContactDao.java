package models;

import java.util.ArrayList;
import java.util.List;

public class ContactDao implements Contacts {
    private List<Contact> contacts = new ArrayList<>();

    @Override
    public List<Contact> getAllContacts() {
        return contacts;
    }

    @Override
    public long createContact(Contact contact) {
        if(contact.getId() == 0) {
            // setting up a contact that has't been assigned an ID: add 1 - SQL starts at 1 and not 0
            contact.setId(contacts.size() + 1);
            contacts.add(contact);
        } else {
            // setting up a contact where the ID has been assigned
            contacts.set((int) (contact.getId() - 1), contact);
        }

        // return the id of the newly created contact
        return contact.getId();
    }

    @Override
    public void deleteContact(long id) {
        contacts.remove((int) id);
    }

    @Override
    public Contact getContact(long id) {
        return contacts.get((int) id);
    }
}
