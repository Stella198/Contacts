package com.m4.contacts.controller;

import com.m4.contacts.entity.Contact;
import com.m4.contacts.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class ContactController {

    private ContactService contactService;
    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/")
    public String welcomePage(){
        return "Welcome!";
    }
    @PostMapping("/contact/add")
    public Map<String, Object> addContact(@RequestBody Contact contact) throws IOException {
        Map<String,Object> response=new HashMap<>();
        String result= contactService.addContact(contact);
        if(result.equals("A contact with the same first name and last name already exists.") || result.equals("Cannot add contact with missing information.")){
            response.put("Failure",result);
        }else {
            response.put("Success",result);
        }
        return response;
    }
    @GetMapping("contact/getAll")
    public List<Contact> getAll(){
        return contactService.getAllContacts();
    }
    @GetMapping(path ="contact/get/{lastName}")
    public Contact getContactByName(@PathVariable("lastName") String lastName) throws IOException {
        return contactService.getContactByName(lastName);
    }

    @DeleteMapping (path ="contact/delete/{id}")
    public Map<String, Object> deleteContactById(@PathVariable("id") int id) {
        Map<String, Object> response = new HashMap<>();
        String result = contactService.deleteContactById(id);
        if (result.equals("Contact with id "+ id+ " does not exists")) {
            response.put("Failure", result);
        } else{
            response.put("Success", result);
        }
        return response;
    }
}
