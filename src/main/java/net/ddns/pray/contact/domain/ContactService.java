package net.ddns.pray.contact.domain;

import net.ddns.pray.contact.presentation.Page;

public interface ContactService {

	Page<Contact> getContactPage(int pageNumber);

	int getContactCount();

	Page<Contact> getContactByPhoneNo(String phoneNo, int pageNumber);

	int addContact(Contact contact);

	int deleteContact(int id);

	Contact getContactById(int id);

	int deleteContactByPrefix(String prefix);
}
