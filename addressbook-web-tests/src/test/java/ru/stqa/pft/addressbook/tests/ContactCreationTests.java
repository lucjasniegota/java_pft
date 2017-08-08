package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() {
    app.getContactHelper().gotoNewContact();
    app.getContactHelper().fillContactForm(new ContactData("Łucja", "Śniegota", "lucjasniegota@gmail.com"));
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnHomePage();
  }

}


