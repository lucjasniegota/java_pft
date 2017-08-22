package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase{

    @Test
    public void testContactDeletion(){
        app.getNavigationHelper().gotoHomePage();
      int before = app.getContactHelper().getContactCount();
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("nowa", "nowa", "lucja@gmail.com", "test1"), true);
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
        app.getContactHelper().submitContactDeletion();
      app.getNavigationHelper().gotoHomePage();
      int after = app.getContactHelper().getContactCount();
      Assert.assertEquals(after, before -1);


    }

}
