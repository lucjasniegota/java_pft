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

  public void initContactModification() {
    click(By.xpath("//td[8]/a/img"));
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
    List<WebElement> elements = wd.findElements(By.xpath("//table[@id='maintable']//td[3]"));
    for (WebElement element : elements){
      String firstname = element.getText();
       int id = Integer.parseInt(element.findElement(By.xpath("//table[@id='maintable']//td/input")).getAttribute("value"));
      ContactData contact = new ContactData(id, firstname, null, null, null);
      contacts.add(contact);
    }
    return contacts;
}
}
