package ru.stqa.pft.addressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() {
    gotoNewContact();
    fillContactForm(new ContactData("Łucja", "Śniegota", "lucjasniegota@gmail.com"));
    returnHomePage();
  }

}


