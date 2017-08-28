package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactDeletionTests extends TestBase{

    @Test
    public void testContactDeletion(){
      app.goTo().groupPage();
      if (! app.group().isThereAGroup()){
        app.group().create(new GroupData().withName("test5"));
      }
      app.goTo().homePage();
        if (! app.contact().isThereAContact()){
            app.contact().createContact(new ContactData("nowa", "nowa", "lucja@gmail.com", "test5"), true);
        }
      List<ContactData> before = app.contact().getContactList();
        app.contact().selectContact(before.size() -1);
        app.contact().deleteContact();
        app.contact().submitContactDeletion();
      app.goTo().homePage();
      List<ContactData> after = app.contact().getContactList();
      Assert.assertEquals(after.size(), before.size() -1);
      before.remove(before.size() -1);
      Assert.assertEquals(before, after);

    }

}
