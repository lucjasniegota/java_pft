package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String firstname;
  private final String lastname;
  private final String email;
  private String group;
  private int id;

  public ContactData(String firstname, String lastname, String email, String group) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
    this.group = group;
    this.id = Integer.MAX_VALUE;
  }
  public ContactData(int id, String firstname, String lastname, String email, String group) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
    this.group = group;

  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getEmail() {
    return email;
  }
  public String getGroup() { return group;  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    return firstname != null ? firstname.equals(that.firstname) : that.firstname == null;
  }

  @Override
  public int hashCode() {
    return firstname != null ? firstname.hashCode() : 0;
  }

  public int getId() {

    return id;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "firstname='" + firstname + '\'' +
            ", id='" + id + '\'' +
            '}';
  }

  public void setId(int id) {
    this.id = id;
  }
}
