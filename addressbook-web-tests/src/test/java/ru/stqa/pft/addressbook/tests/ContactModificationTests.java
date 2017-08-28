package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase{

  @Test
 public void testContactModification(){
     app.goTo().groupPage();
     if (! app.group().isThereAGroup()){
        app.group().create(new GroupData().withName("test5"));
     }
    app.goTo().homePage();
    if (!app.contact().isThereAContact()) {
      app.contact().createContact
              (new ContactData("nowa", "nowa", "lucja@gmail.com", "test5"), true);
    }
    List<ContactData> before = app.contact().getContactList();
    app.contact().initContactModification(before.size()-1);
    ContactData contact = new ContactData(before.get(before.size()-1).getId(),"zzzz", "zzzz", "zz@zzz.com", null);
    app.contact().fillContactForm(contact, false);
    app.contact().submitContactModification();
    app.contact().returnHomePage();
    List<ContactData> after = app.contact().getContactList();
    Assert.assertEquals(after.size(), before.size());
    before.remove(before.size()-1);
    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId() );
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
  }
}
