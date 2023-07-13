package com.m4.contacts.restAssured;

import com.m4.contacts.entity.Contact;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RestAssuredTests {
    private static final String BASE_URL = "http://localhost:8080";
    @Test
    @Order(1)
    public void testAddContact() {
        // prepare request body
        Contact contact = new Contact();
        contact.setFirstName("John");
        contact.setLastName("Doe");
        contact.setPhoneNumber("1234567890");
        // send POST request to add contact

        given()
                .contentType(ContentType.JSON)
                .body(contact)
                .when()
                .post(BASE_URL + "/contact/add")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(2)
    public void testGetAllContacts() {
        // send GET request to get all contacts
        given()
                .when()
                .get(BASE_URL + "/contact/getAll")
                .then()
                .statusCode(200)
                .body("$.size()",Matchers.equalTo(2));
    }
    @Test
    @Order(3)
    public void testGetContactByName() {
        // send GET request to get contact by last name
        given()
                .when()
                .get(BASE_URL + "/contact/get/Doe")
                .then()
                .statusCode(200)
                .body("firstName", Matchers.equalTo("John"))
                .body("lastName", Matchers.equalTo("Doe"));
    }
    @Test
    @Order(4)
    public void testDeleteContactById() {
        // send DELETE request to delete contact by ID
        given()
                .when()
                .delete(BASE_URL + "/contact/delete/3")
                .then()
                .statusCode(200);
    }

}