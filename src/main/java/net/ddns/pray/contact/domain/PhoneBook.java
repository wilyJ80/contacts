package net.ddns.pray.contact.domain;

import java.util.List;

public class PhoneBook {

    private List<Contact> contacts;

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
