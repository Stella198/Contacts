package com.m4.contacts.object.model;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
@Getter
@Setter
public class SearchContactPageModel {
    private WebDriver driver;
    @FindBy(name = "lastName")
    private WebElement lastNameInput;
    @FindBy(css=".button")
    private  WebElement submitButton;
    @FindBy(css=".search-status")
    private WebElement returnMessage;
    public SearchContactPageModel(WebDriver driver) {
        this.driver = driver;
    }

}
