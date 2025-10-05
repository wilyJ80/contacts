package net.ddns.pray.contact.domain;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import net.ddns.pray.contact.presentation.Page;

@Service
public class ContactServiceImpl implements ContactService {

	private ContactDao contactDao;
	private static final int PAGE_SIZE = 20;

	public ContactServiceImpl(ContactDao contactDao) {
		this.contactDao = contactDao;
	}

	@Override
	public Page<Contact> getContactPage(int pageNumber) {

		List<Contact> contacts = this.contactDao.findPage(pageNumber, PAGE_SIZE);

		int totalItems = this.contactDao.countTotal();

		int totalPages = (int) Math.ceil((double) totalItems / PAGE_SIZE);

		return new Page<>(contacts, pageNumber, totalItems, totalPages, PAGE_SIZE);
	}

	@Override
	public int getContactCount() {

		return this.contactDao.countTotal();
	}

	@Override
	public Page<Contact> getContactByPhoneNo(String phoneNo, int pageNumber) {
		List<Contact> contacts = this.contactDao.selectContactByPhoneNo(phoneNo, pageNumber, PAGE_SIZE);

		int totalItems = this.contactDao.countTotalByPhoneNo(phoneNo);

		int totalPages = (int) Math.ceil((double) totalItems / PAGE_SIZE);

		return new Page<>(contacts, pageNumber, totalItems, totalPages, PAGE_SIZE);
	}

	@Override
	public int addContact(Contact contact) {

		return this.contactDao.addContact(contact);
	}

	@Override
	public int deleteContact(int id) {

		int rows = this.contactDao.deleteContact(id);

		if (rows == 0) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found");
		}

		return rows;
	}

	@Override
	public Contact getContactById(int id) {

		return this.contactDao.selectContactById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found"));
	}

	@Override
	public int deleteContactByPrefix(String prefix) {

		return this.contactDao.deleteContactByPrefix(prefix);
	}
}
