package com.m4.contacts.service;

import com.m4.contacts.entity.Contact;
import com.m4.contacts.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
@Service
public class ContactService {
    ContactRepository contactRepository;
    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> getAllContacts(){
        return contactRepository.findAll();
    }
    public void saveContact(Contact contact){
        contactRepository.save(contact);
    }

    public String addContact(Contact contact) throws IOException {
        if (contact.getFirstName()==null||contact.getLastName()==null||contact.getPhoneNumber()==null){
            return "Cannot add contact with missing information.";
        }
        List<Contact>contactList=getAllContacts();
        boolean contactExists = contactList.stream()
                .anyMatch(c -> c.getFirstName().equalsIgnoreCase(contact.getFirstName()) &&
                        c.getLastName().equalsIgnoreCase(contact.getLastName()));
        if (contactExists) {
            return "A contact with the same first name and last name already exists.";
        }
        int nextId =contactList.isEmpty() ? 1 : contactList.get(contactList.size() - 1).getId() + 1;
        contact.setId(nextId);
        contactList.add(contact);
        saveContact(contact);
        return "Contact added successfully!";
    }

    public Contact getContactByName(String lastName) throws IOException {
        Contact contact=contactRepository.findContactBYName(lastName);
        if(contact==null){
            throw new IOException("There are no contacts with name "+lastName);
        }
        return contact;
    }
    public String deleteContactById(int id) {
        boolean exist =contactRepository.existsById(id);
        if (!exist){
            return "Contact with id "+ id+ " does not exists";
        }
        else {
            contactRepository.deleteById(id);
            return "Contact removed successfully!";
        }
    }
}
