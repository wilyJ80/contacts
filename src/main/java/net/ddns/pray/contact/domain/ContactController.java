package net.ddns.pray.contact.domain;

import java.util.Arrays;

import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import net.ddns.pray.contact.presentation.BreadcrumbItem;
import net.ddns.pray.contact.presentation.Breadcrumbs;
import net.ddns.pray.contact.presentation.Page;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactController {

	private ContactService contactService;

	public ContactController(ContactService contactService) {
		this.contactService = contactService;
	}

	@GetMapping("/")
	public String getIndex(@RequestParam(defaultValue = "1") int pageNumber, Model model) {

		Page<Contact> contactPage = this.contactService.getContactPage(pageNumber);

		PhoneBook phoneBook = new PhoneBook();
		phoneBook.setContacts(contactPage.getContent());

		model.addAttribute("phoneBook", phoneBook);
		model.addAttribute("contactPage", contactPage);
		model.addAttribute("pageTitle", "All contacts");
		return "contact-view/contacts";
	}

	@GetMapping("/search")
	public String getSearch(@RequestParam String phoneNo, @RequestParam(defaultValue = "1") int pageNumber,
			Model model) {

		Breadcrumbs<BreadcrumbItem> breadcrumbs = new Breadcrumbs<>(
				Arrays.asList(new BreadcrumbItem("/search?phoneNo=" + phoneNo, "Search")));

		Page<Contact> contactPage = this.contactService.getContactByPhoneNo(phoneNo, pageNumber);

		PhoneBook phoneBook = new PhoneBook();
		phoneBook.setContacts(contactPage.getContent());

		model.addAttribute("pageTitle", "Search results");
		model.addAttribute("breadcrumbs", breadcrumbs);
		model.addAttribute("phoneNo", phoneNo);
		model.addAttribute("contactPage", contactPage);
		model.addAttribute("phoneBook", phoneBook);
		return "contact-view/contacts";
	}

	@GetMapping("/add")
	public String getAddContactPage(Model model) {

		Breadcrumbs<BreadcrumbItem> breadcrumbs = new Breadcrumbs<>(
				Arrays.asList(new BreadcrumbItem("/add", "Add contact")));

		model.addAttribute("pageTitle", "Add contact");
		model.addAttribute("breadcrumbs", breadcrumbs);
		model.addAttribute("contact", new Contact());

		return "contact-add/add";
	}

	@PostMapping("/add")
	public String addContact(@ModelAttribute @Valid Contact contact, BindingResult bindingResult, Model model,
			RedirectAttributes redirectAttributes) {

		/* Model Error */
		if (bindingResult.hasErrors()) {

			Breadcrumbs<BreadcrumbItem> breadcrumbs = new Breadcrumbs<>(
					Arrays.asList(new BreadcrumbItem("/add", "Add contact")));

			model.addAttribute("pageTitle", "Add contact");
			model.addAttribute("breadcrumbs", breadcrumbs);

			return "contact-add/add";
		}

		try {
			this.contactService.addContact(contact);

		} catch (UncategorizedSQLException exception) {
			/* Possible constraint violation */
			Breadcrumbs<BreadcrumbItem> breadcrumbs = new Breadcrumbs<>(
					Arrays.asList(new BreadcrumbItem("/add", "Add contact")));

			model.addAttribute("pageTitle", "Add contact");
			model.addAttribute("breadcrumbs", breadcrumbs);
			model.addAttribute("sqlException", exception);

			return "contact-add/add";

		} catch (Exception exception) {

			Breadcrumbs<BreadcrumbItem> breadcrumbs = new Breadcrumbs<>(
					Arrays.asList(new BreadcrumbItem("/add", "Add contact")));

			model.addAttribute("pageTitle", "Add contact");
			model.addAttribute("breadcrumbs", breadcrumbs);
			model.addAttribute("exception", exception);

			return "contact-add/add";
		}

		redirectAttributes.addFlashAttribute("addedMessage", "Contact added!");
		return "redirect:/";
	}

	@GetMapping("/delete/{id}")
	public String getDeleteConfirmPage(@PathVariable int id, Model model) {

		Contact contact = this.contactService.getContactById(id);
		model.addAttribute("contact", contact);

		Breadcrumbs<BreadcrumbItem> breadcrumbs = new Breadcrumbs<>(
				Arrays.asList(new BreadcrumbItem("/add", "Delete contact")));

		model.addAttribute("pageTitle", "Delete contact");
		model.addAttribute("breadcrumbs", breadcrumbs);

		return "delete/confirm";
	}

	@PostMapping("/delete/{id}")
	public String deleteContact(@PathVariable int id, RedirectAttributes redirectAttributes, Model model) {

		try {
			this.contactService.deleteContact(id);

		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Contact not found");
			return "redirect:/";

		}

		model.addAttribute("pageTitle", "All contacts");
		redirectAttributes.addFlashAttribute("addedMessage", "Contact deleted successfully!");
		return "redirect:/";
	}

	@GetMapping("/deletebyprefix")
	public String getDeleteByPrefixPage(Model model) {

		Breadcrumbs<BreadcrumbItem> breadcrumbs = new Breadcrumbs<>(
				Arrays.asList(new BreadcrumbItem("/add", "Delete contact")));

		model.addAttribute("pageTitle", "Delete by prefix");
		model.addAttribute("breadcrumbs", breadcrumbs);

		return "deletebyprefix/delete";
	}

	@PostMapping("/deletebyprefix")
	public String deleteByPrefix(@RequestParam String prefix, Model model, RedirectAttributes redirectAttributes) {

		try {
			int rows = this.contactService.deleteContactByPrefix(prefix);

			if (rows > 0) {
				redirectAttributes.addFlashAttribute("addedMessage", "Contacts deleted successfully!");
			} else {
				redirectAttributes.addFlashAttribute("addedMessage", "No contact found for prefix");
			}

		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Operation failed");
			return "redirect:/";

		}

		model.addAttribute("pageTitle", "All contacts");

		return "redirect:/";
	}
}
