package net.ddns.pray.contact.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import net.ddns.pray.contact.presentation.Page;

class ContactServiceImplTest {
	private ContactDao contactDao;
	private ContactServiceImpl service;

	@BeforeEach
	void setUp() {
		contactDao = mock(ContactDao.class);
		service = new ContactServiceImpl(contactDao);
	}

	@Test
	void firstPage_withFewerItemsThanPageSize() {
		List<Contact> contacts = List.of(new Contact(1, "Alice", "2000-01-01", "123", "a@test.com"));
		when(contactDao.findPage(1, 20)).thenReturn(contacts);
		when(contactDao.countTotal()).thenReturn(1);
		Page<Contact> page = service.getContactPage(1);
		assertEquals(contacts, page.getContent());
		assertEquals(1, page.getCurrentPage());
		assertEquals(1, page.getTotalItems());
		assertEquals(1, page.getTotalPages());
		assertEquals(20, page.getPageSize());
	}

	@Test
	void totalPages_isRoundedUp() {
		when(contactDao.findPage(1, 20)).thenReturn(List.of());
		when(contactDao.countTotal()).thenReturn(21);
		Page<Contact> page = service.getContactPage(1);
		assertEquals(2, page.getTotalPages());
	}

	@Test
	void exactPageSize_dividesCleanly() {
		when(contactDao.findPage(1, 20)).thenReturn(List.of());
		when(contactDao.countTotal()).thenReturn(40);
		Page<Contact> page = service.getContactPage(1);
		assertEquals(2, page.getTotalPages());
	}

	@Test
	void pageNumberBeyondTotalPages_returnsEmptyContent() {
		when(contactDao.findPage(5, 20)).thenReturn(List.of());
		when(contactDao.countTotal()).thenReturn(10);
		Page<Contact> page = service.getContactPage(5);
		assertTrue(page.getContent().isEmpty());
		assertEquals(1, page.getTotalPages());
		assertEquals(5, page.getCurrentPage());
	}

	@Test
	void getContactByPhoneNo_usesDaoProperly() {
		List<Contact> contacts = List.of(new Contact(1, "Bob", "1999-05-05", "555", "b@test.com"));
		when(contactDao.selectContactByPhoneNo("555", 1, 20)).thenReturn(contacts);
		when(contactDao.countTotalByPhoneNo("555")).thenReturn(1);
		Page<Contact> page = service.getContactByPhoneNo("555", 1);
		assertEquals(contacts, page.getContent());
		assertEquals(1, page.getTotalPages());
	}
}
