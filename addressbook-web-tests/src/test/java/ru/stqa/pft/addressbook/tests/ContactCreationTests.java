package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws InterruptedException {
    app.goTo().groupPage();
    if (! app.group().isThereAGroup()){
      app.group().create(new GroupData().withName("test5"));
    }
    app.goTo().homePage();
    Thread.sleep(2000);
    List<ContactData> before = app.contact().getContactList();
    ContactData contact = new ContactData("testtt", "ttt", "lucjasniegota@gmail.com", "test1");
    app.contact().createContact(contact, true);
    List<ContactData> after = app.contact().getContactList();
    Assert.assertEquals(after.size(), before.size() +1);
    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId() );
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }}




