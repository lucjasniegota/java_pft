package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordForUser extends TestBase {
  @BeforeMethod
  public void startMailServer() throws IOException, MessagingException {
    app.mail().start();
    if (app.hBConnectionHelper().users().size() == 0) {
      long now = System.currentTimeMillis();
      String email = String.format("user%s@localhost.localdomain", now);
      String user = String.format("user%s", now);
      String password = "password";
      app.registration().start(user, email);
      List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
      String confirmationLink = findConfirmationLink(mailMessages, email);
      app.registration().finish(confirmationLink, password);
      assertTrue(app.newSession().login(user, password));
    }
  }

  @Test
  public void testChangePassword() throws IOException, MessagingException {
    Users users = app.hBConnectionHelper().users();
    UserData userTochange = users.iterator().next();
    String userToClick = userTochange.getUsername();
    app.changePassword().start(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
    String email = String.format("%s@localhost.localdomain", userToClick);
    String password = "NEWpassword";
    app.changePassword().manageUsers();
    app.changePassword().chooseUser(userToClick);
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String confirmationLink = findConfirmationLinkPassword(mailMessages);
    app.changePassword().finish(confirmationLink, password);
    assertTrue(app.newSession().login(userToClick, password));
  }

  private String findConfirmationLinkPassword(List<MailMessage> mailMessages) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.text.contains("password change")).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}
