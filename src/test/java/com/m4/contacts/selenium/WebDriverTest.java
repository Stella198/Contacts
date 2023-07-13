package com.m4.contacts.selenium;

import com.m4.contacts.object.model.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WebDriverTest {
    private static WebDriver driver;

    @BeforeAll
    static void init(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver= new ChromeDriver(options);
        driver.get("http://localhost:3000/");
    }
    @AfterAll
    static void teardown(){
        driver.close();
    }

    @Test
    @Order(1)
    void homepageTest(){
        //given
        //when
        HomepageObjectModel homepageObjectModel= new HomepageObjectModel(driver);
        homepageObjectModel.getNextPage().click();
        //then
        assertEquals("http://localhost:3000/", driver.getCurrentUrl(), "Link is incorrect");
    }
    @Test
    @Order(1)
    void addContactTest(){
        driver.findElement(By.linkText("ADD CONTACT")).click();
        AddContactPageModel addContactPageModel=PageFactory.initElements(
                driver,
                AddContactPageModel.class
        );
        addContactPageModel.getFirstNameInput().sendKeys("Jane");
        addContactPageModel.getLastNameInput().sendKeys("Jane");
        addContactPageModel.getPhoneNumberInput().sendKeys("0123456789");
        addContactPageModel.getSubmitButton().click();

        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofMillis(1000));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".add-status")));
        assertEquals("Contact saved successfully!", addContactPageModel.getReturnMessage().getText());
    }
    @Test
    @Order(3)
    void searchContactTest(){
        driver.findElement(By.linkText("SEARCH CONTACT")).click();
        SearchContactPageModel searchContactObjectModel=
                PageFactory.initElements(
                        driver,
                        SearchContactPageModel.class
                );
        searchContactObjectModel.getLastNameInput().sendKeys("Jane");
        searchContactObjectModel.getSubmitButton().click();

        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofMillis(1000));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".search-status")));
        assertEquals("Contact found",searchContactObjectModel.getReturnMessage().getText());
    }
    @Test
    @Order(4)
    void deleteContactTest(){
        driver.findElement(By.linkText("DELETE CONTACT")).click();
        DeleteContactPageModel deleteContactObjectModel =
                PageFactory.initElements(
                        driver,
                        DeleteContactPageModel.class
                );
        deleteContactObjectModel.getContactIdInput().sendKeys("3");
        deleteContactObjectModel.getSubmitButton().click();

        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofMillis(1000));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".delete-status")));
        assertEquals("Contact deleted successfully!",deleteContactObjectModel.getReturnMessage().getText());
    }
    @Test
    @Order(5)
    void contactListTest(){
        driver.findElement(By.linkText("CONTACT LIST")).click();
        ListContactsPageModel listContactsObjectModel= new ListContactsPageModel(driver);
        assertTrue(listContactsObjectModel.getContactList().isDisplayed(), "Contact list is not displayed");
        List<WebElement> contacts = listContactsObjectModel.getContactList().findElements(By.tagName("tr"));
        System.out.println("value = " +listContactsObjectModel.getContactList().getText());
        assertEquals(2, contacts.size(), "Unexpected number of contacts");
        assertEquals("1 ANA ANA 0123456789", contacts.get(0).getText(), "Contact name is incorrect");
    }
}
