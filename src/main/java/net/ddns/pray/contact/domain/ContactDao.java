package net.ddns.pray.contact.domain;

import java.util.List;
import java.util.Optional;

public interface ContactDao {

	/* WARNING: Naive implementation with offset/limit */
	List<Contact> findPage(int pageNumber, int pageSize);

	int countTotal();

	int countTotalByPhoneNo(String phoneNo);

	List<Contact> selectContactByPhoneNo(String phoneNo, int pageNumber, int pageSize);

	int addContact(Contact contact);

	int deleteContact(int id);

	Optional<Contact> selectContactById(int id);

	int deleteContactByPrefix(String prefix);
}
