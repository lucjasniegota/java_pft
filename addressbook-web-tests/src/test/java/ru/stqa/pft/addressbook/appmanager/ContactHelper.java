package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {


  public ContactHelper(WebDriver wd) {

    super(wd);
  }

  public void returnHomePage() {

    click(By.linkText("home page"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("email"), contactData.getEmail());
    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void gotoNewContact() {
    click(By.linkText("add new"));
  }

  public void initContactModification(int index) {
    wd.findElements(By.cssSelector("img[title = Edit]")).get(index).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void selectContact(int index) {

    wd.findElements(By.name("selected[]")).get(index).click();

  }



  public void deleteContact() {
    click(By.xpath("//input[@value='Delete']"));


  }

  public void submitContactDeletion() {
    wd.switchTo().alert().accept();
  }

  public void submitContactCreation() {
    click(By.name("submit"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public void createContact(ContactData contact, boolean creation) {
    gotoNewContact();
    fillContactForm(contact, true);
    submitContactCreation();
    returnHomePage();
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements){
       int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String firstname = element.findElement(By.xpath("//td[3]")).getText();
      ContactData contact = new ContactData(id, firstname, null, null, null);
      contacts.add(contact);
    }
    return contacts;
}
}
