package com.m4.contacts.object.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class HomepageObjectModel {
    private WebDriver driver;
    private WebElement nextPage;
    public HomepageObjectModel(WebDriver driver) {
        this.driver=driver;
        nextPage =driver.findElement(By.linkText("ADD CONTACT"));
    }
    public WebElement getNextPage() {
        return nextPage;
    }


}
