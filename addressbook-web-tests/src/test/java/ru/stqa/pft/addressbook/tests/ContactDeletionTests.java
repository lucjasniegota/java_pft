package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase{

    @Test (enabled = false)
    public void testContactDeletion(){
        app.goTo().homePage();
      List<ContactData> before = app.contact().getContactList();
        if (! app.contact().isThereAContact()){
            app.contact().createContact(new ContactData("nowa", "nowa", "lucja@gmail.com", "test1"), true);
        }
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
