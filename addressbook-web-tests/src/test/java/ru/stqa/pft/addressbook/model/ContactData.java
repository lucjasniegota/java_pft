package ru.stqa.pft.addressbook.model;

import java.io.File;

public class ContactData {
  private  String firstname;
  private  String lastname;
  private  String email;
  private  String phoneHome;
  private  String phoneMobile;
  private  String phoneWork;
  private  String allPhones;
  private  String email2;
  private  String email3;
  private  String allEmails;
  private  String allName;
  private File photo;
  private  String address;
  private String group;
  private int id = Integer.MAX_VALUE;


  public String getFirstname() {
    return firstname;
  }
  public String getLastname() {    return lastname;
  }
  public String getEmail() {
    return email;
  }
  public String getGroup() { return group;  }
  public String getPhoneHome() { return phoneHome;  }
  public String getPhoneMobile() { return phoneMobile;  }
  public String getPhoneWork() { return phoneWork;  }
  public String getEmail2() { return email2;  }
  public String getEmail3() { return email3;  }
  public String getAllEmails() { return allEmails;  }
  public String getAddress() { return address;  }
  public String getAllPhone() { return allPhones;  }
  public String getAllName() { return allName;  }
  public int getId() {    return id;  }
  public File getPhoto() {    return photo;  }

  public ContactData withPhoto(File photo) {
    this.photo = photo;
    return this;
  }
  public ContactData withId(int id) {
    this.id = id;
    return this;
  }
  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;}
  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;}
  public ContactData withEmail(String email) {
    this.email = email;
    return this;}
  public ContactData withAllPhone(String allPhones) {
    this.allPhones = allPhones;
    return this;}
  public ContactData withGroup(String group) {
    this.group = group;
    return this;}
  public ContactData withPhoneHome(String phoneHome) {
    this.phoneHome = phoneHome;
    return this;}
  public ContactData withPhoneWork(String phoneWork) {
    this.phoneWork = phoneWork;
    return this;}
  public ContactData withPhoneMobile(String phoneMobile) {
    this.phoneMobile = phoneMobile;
    return this;}
  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;}
  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;}
  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;}
  public ContactData withAllName(String allName) {
    this.allName = allName;
    return this;}
  public ContactData withAddress(String address) {
    this.address = address;
    return this;}


  @Override
  public String toString() {
    return "ContactData{" +
            "firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", email='" + email + '\'' +
            ", phoneHome='" + phoneHome + '\'' +
            ", phoneMobile='" + phoneMobile + '\'' +
            ", phoneWork='" + phoneWork + '\'' +
            ", allPhones='" + allPhones + '\'' +
            ", email2='" + email2 + '\'' +
            ", email3='" + email3 + '\'' +
            ", allEmails='" + allEmails + '\'' +
            ", allName='" + allName + '\'' +
            ", address='" + address + '\'' +
            ", group='" + group + '\'' +
            ", id=" + id +
            '}';
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
  }

  @Override
  public int hashCode() {
    int result = firstname != null ? firstname.hashCode() : 0;
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    result = 31 * result + id;
    return result;
  }


          }
