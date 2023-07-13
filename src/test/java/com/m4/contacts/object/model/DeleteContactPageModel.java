package com.m4.contacts.object.model;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
@Getter
@Setter
public class DeleteContactPageModel {
    private WebDriver driver;

    @FindBy(name = "contactId")
    private WebElement contactIdInput;

    @FindBy(css = ".button")
    private WebElement submitButton;
    @FindBy(css = ".delete-status")
    private WebElement returnMessage;

    public DeleteContactPageModel(WebDriver driver) {

        this.driver = driver;
    }
}
