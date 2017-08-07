package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() {
    app.gotoNewContact();
    app.fillContactForm(new ContactData("Łucja", "Śniegota", "lucjasniegota@gmail.com"));
    app.returnHomePage();
  }

}


