package com.stevex.demo;

import java.util.Date;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.web.client.RestTemplate;

public class RestfulWSClient {
	private static final String URL_GET_ALL_CONTACTS = "http://localhost:8080/Spring-Restful-WS/restful/contact/listdata";
	private static final String URL_GET_CONTACT_BY_ID = "http://localhost:8080/Spring-Restful-WS/restful/contact/{id}";
	private static final String URL_CREATE_CONTACT = "http://localhost:8080/Spring-Restful-WS/restful/contact/";
	private static final String URL_UPDATE_CONTACT = "http://localhost:8080/Spring-Restful-WS/restful/contact/{id}";
	private static final String URL_DELETE_CONTACT = "http://localhost:8080/Spring-Restful-WS/restful/contact/{id}";

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:restful-client-app-context.xml");
		ctx.refresh();
		
		Contacts contacts;
		Contact contact;
		RestTemplate restTemplate = ctx.getBean("restTemplate",
				RestTemplate.class);

		// Test retrieve all contacts
		System.out.println("Testing retrieve all contacts:");
		contacts = restTemplate.getForObject(URL_GET_ALL_CONTACTS,
				Contacts.class);
		listContacts(contacts);
		
		// Test retrieve contact by id
		System.out.println("Testing retrieve a contact by id :");
		contact = restTemplate.getForObject(
		URL_GET_CONTACT_BY_ID, Contact.class, 1);
		System.out.println(contact);
		System.out.println("");
		
		// Test update contact
		contact = restTemplate.getForObject(
		URL_UPDATE_CONTACT, Contact.class, 1);
		contact.setFirstName("Rod");
		System.out.println("Testing update contact by id :");
		restTemplate.put(URL_UPDATE_CONTACT, contact, 1);
		System.out.println("Contact update successfully: " + contact);
		System.out.println("");
		
		// Testing delete contact
		restTemplate.delete(URL_DELETE_CONTACT, 1);
		System.out.println("Testing delete contact by id :");
		contacts = restTemplate.getForObject(
		URL_GET_ALL_CONTACTS, Contacts.class);
		listContacts(contacts);
		
		// Testing create contact
		System.out.println("Testing create contact :");
		Contact contactNew = new Contact();
		contactNew.setFirstName("Larry");
		contactNew.setLastName("Page");
		contactNew.setBirthDate(new Date());
		contactNew = restTemplate.postForObject(
		URL_CREATE_CONTACT, contactNew, Contact.class);
		System.out.println("Contact created successfully: " + contactNew);
		contacts = restTemplate.getForObject(URL_GET_ALL_CONTACTS,
				Contacts.class);
		listContacts(contacts);
	}

	private static void listContacts(Contacts contacts) {
		for (Contact contact : contacts.getContacts()) {
			System.out.println(contact);
		}
		System.out.println("");
	}

}
