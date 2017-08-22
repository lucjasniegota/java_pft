package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactModificationTests extends TestBase{

  @Test
 public void testContactModification(){
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("test1", "test1", "trst1"));
    }
    app.getNavigationHelper().gotoHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact
              (new ContactData("nowa", "nowa", "lucja@gmail.com", "test1"), true);
    }
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Ktos", "Ktosiek", "costama@gmail.com", null), false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before );
  }

}
