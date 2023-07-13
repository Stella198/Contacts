package com.m4.contacts.object.model;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
@Getter
@Setter
public class AddContactPageModel {
    private WebDriver driver;
    @FindBy(name = "firstName")
    private WebElement firstNameInput;
    @FindBy(name = "lastName")
    private WebElement lastNameInput;
    @FindBy(name = "phoneNumber")
    private WebElement phoneNumberInput;
    @FindBy(css = ".button")
    private WebElement submitButton;
    @FindBy(css = ".add-status")
    private WebElement returnMessage;
}
