package com.m4.contacts.jUnit;

import com.m4.contacts.controller.ContactController;
import com.m4.contacts.entity.Contact;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JUnitTests {
    @Autowired
    private ContactController contactController;

    @Test
    @Order(1)
    public void testWelcomePage() {
        String response = contactController.welcomePage();
        assertEquals("Welcome!", response);
    }

    @Test
    @Order(2)
    public void testAddContact() throws IOException {
        Contact contact = new Contact();
        contact.setFirstName("John");
        contact.setLastName("Doe");
        contact.setPhoneNumber("1234567890");
        Map<String, Object> response = contactController.addContact(contact);
        assertEquals("Success", response.keySet().iterator().next());

        List<Contact> contacts = contactController.getAll();
        assertEquals(2, contacts.size());
        Contact addedContact = contacts.get(1);
        assertEquals(contact.getFirstName(), addedContact.getFirstName());
        assertEquals(contact.getLastName(), addedContact.getLastName());
        assertEquals(contact.getPhoneNumber(), addedContact.getPhoneNumber());
        assertNotEquals(0, addedContact.getId());
    }

    @Test
    @Order(3)
    public void testAddContactWithMissingFields() throws IOException {
        Contact contact = new Contact();
        contact.setFirstName("John");
        Map<String, Object> response = contactController.addContact(contact);
        assertEquals("Failure", response.keySet().iterator().next());

        List<Contact> contacts = contactController.getAll();
        assertEquals(2, contacts.size());
    }

    @Test
    @Order(4)
    public void testGetAllContacts(){
        List<Contact> contacts = contactController.getAll();
        for (Contact contact : contacts) {
            assertNotEquals(0, contact.getId());
            assertNotNull(contact.getFirstName());
            assertNotNull(contact.getLastName());
            assertNotNull(contact.getPhoneNumber());
            assertEquals(10, contact.getPhoneNumber().length());
        }
        assertEquals(2, contacts.size());

    }
    @Test
    @Order(5)
    public void testGetContactByName() throws IOException {
        Contact contact = contactController.getContactByName("Doe");
        assertNotNull(contact);
        assertEquals("Doe", contact.getLastName());
        assertEquals("John", contact.getFirstName());

        Contact nonExistentContact = contactController.getContactByName("Smith");
        assertNull(nonExistentContact);
    }

    @Test
    @Order(6)
    public void testDeleteContactById(){
        // get the first contact from the list
        List<Contact> contacts = contactController.getAll();
        int idToDelete = contacts.get(1).getId();

        // delete the contact
        Map<String, Object> response = contactController.deleteContactById(idToDelete);
        assertEquals("Success", response.keySet().iterator().next());

        // make sure the contact was deleted
        contacts = contactController.getAll();
        boolean found = false;
        for (Contact contact : contacts) {
            if (contact.getId() == idToDelete) {
                found = true;
                break;
            }
        }
        assertFalse(found);

        // try to delete a non-existent contact
        Map<String, Object> nonExistentResponse = contactController.deleteContactById(100);
        assertEquals("Failure", nonExistentResponse.keySet().iterator().next());
    }
}
