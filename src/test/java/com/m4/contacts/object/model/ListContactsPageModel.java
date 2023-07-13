package com.m4.contacts.object.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ListContactsPageModel {
    WebDriver driver;
    WebElement contactList;

    public ListContactsPageModel(WebDriver driver) {
        this.driver = driver;
        contactList= driver.findElement(By.cssSelector(".contact"));
    }

    public WebElement getContactList() {
        return contactList;
    }
}
