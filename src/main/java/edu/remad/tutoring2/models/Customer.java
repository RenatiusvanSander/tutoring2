package edu.remad.tutoring2.models;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * all functions concerning a customer like storing data ofr name, address, birthdate, e-mail, phone
 * number and creation date of data set
 */
//@Entity
@Table(name = "Customer")
public class Customer {

  /**
   * customer's numbers, is a primary key for database.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "customer_no")
  private long customerNo;

  /**
   * customer's first name, for example Herbert.
   */
  private String customerFirstName;

  /**
   * customer's lastname
   */
  private String customerLastName;

  /**
   * customer's birthday
   */
  @Column(name = "customer_birthday", columnDefinition = "TIMESTAMP")
  private LocalDateTime customerBirthday;

  /**
   * customer's zip code
   */
  @OneToOne
  @JoinColumn(name = "customer_zip_code_id", referencedColumnName = "id")
  private ZipCodeEntity customerZipCode;

  /**
   * customer's address
   */
  @OneToOne
  @JoinColumn(name = "address_id", referencedColumnName = "id")
  private AddressEntity customerAddress;

  /**
   * customer's phone number
   */
  private String customerTelephoneNo;

  /**
   * customer's e-mail
   */
  private String customerEmail;

  /**
   * customer's creation date
   */
  @Column(name = "customer_creation_date", columnDefinition = "TIMESTAMP")
  private LocalDateTime customerCreationDate;

  /**
   * Constructor
   */
  public Customer() {
  }

  /**
   * Customer
   *
   * @param customerFirstName   customer's first name
   * @param customerLastName    customer's last name
   * @param customerBirthday    customer's birthday
   * @param customerAddress     customer's address
   * @param customerZipCode     customer's zip code
   * @param customerTelephoneNo customer's telephone number
   * @param customerEmail       customer's e-mail
   * @param customerCreationDate customer's creation date
   */
  public Customer(String customerFirstName, String customerLastName,
      LocalDateTime customerBirthday,
      AddressEntity customerAddress, ZipCodeEntity customerZipCode, String customerTelephoneNo,
      String customerEmail, LocalDateTime customerCreationDate) {
    this.customerFirstName = customerFirstName;
    this.customerLastName = customerLastName;
    this.customerBirthday = customerBirthday;
    this.customerAddress = customerAddress;
    this.customerZipCode = customerZipCode;
    this.customerTelephoneNo = customerTelephoneNo;
    this.customerEmail = customerEmail;
    this.customerCreationDate = customerCreationDate;
  }

  /**
   * Gets zip code
   *
   * @return zip code
   */
  public ZipCodeEntity getCustomerZipCode() {
    return customerZipCode;
  }

  /**
   * Sets zip code
   *
   * @param customerZipCode customer's zip code to set
   */
  public void setCustomerZipCode(ZipCodeEntity customerZipCode) {
    this.customerZipCode = customerZipCode;
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCustomerNo(), getCustomerFirstName(), getCustomerLastName(),
        getCustomerBirthday(), customerZipCode, getCustomerAddress(), getCustomerTelephoneNo(),
        getCustomerEmail(), getCustomerCreationDate());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Customer)) {
      return false;
    }
    Customer customer = (Customer) o;
    return getCustomerNo() == customer.getCustomerNo() && getCustomerFirstName().equals(
        customer.getCustomerFirstName()) && getCustomerLastName().equals(
        customer.getCustomerLastName()) && getCustomerBirthday().equals(
        customer.getCustomerBirthday()) && customerZipCode.equals(customer.customerZipCode)
        && getCustomerAddress().equals(customer.getCustomerAddress())
        && getCustomerTelephoneNo().equals(customer.getCustomerTelephoneNo())
        && getCustomerEmail().equals(customer.getCustomerEmail())
        && getCustomerCreationDate().equals(
        customer.getCustomerCreationDate());
  }

  @Override
  public String toString() {
    return "Customer{" +
        "customerNo=" + customerNo +
        ", customerFirstName='" + customerFirstName + '\'' +
        ", customerLastName='" + customerLastName + '\'' +
        ", customerBirthday=" + customerBirthday +
        ", customerZipCode=" + customerZipCode +
        ", customerAddress=" + customerAddress +
        ", customerTelephoneNo='" + customerTelephoneNo + '\'' +
        ", customerEmail='" + customerEmail + '\'' +
        ", customerCreationDate=" + customerCreationDate +
        '}';
  }

  /**
   * Gets identifier here customer number
   *
   * @return customer's number
   */
  public long getCustomerNo() {
    return customerNo;
  }

  /**
   * Sets customer number as primary key.
   *
   * @param customerNo customer's number to set
   */
  public void setCustomerNo(long customerNo) {
    this.customerNo = customerNo;
  }

  /**
   * Gets firstname
   *
   * @return customer's firstname
   */
  public String getCustomerFirstName() {
    return customerFirstName;
  }

  /**
   * Sets first-name
   *
   * @param customerFirstName customer's first name to set
   */
  public void setCustomerFirstName(String customerFirstName) {
    this.customerFirstName = customerFirstName;
  }

  /**
   * Gets lastname
   *
   * @return customer's lastname
   */
  public String getCustomerLastName() {
    return customerLastName;
  }

  /**
   * Sets lastname
   *
   * @param customerLastName customer's lastname to set
   */
  public void setCustomerLastName(String customerLastName) {
    this.customerLastName = customerLastName;
  }

  /**
   * Gets birthday
   *
   * @return customer's birthday
   */
  public LocalDateTime getCustomerBirthday() {
    return customerBirthday;
  }

  /**
   * Sets birthday
   *
   * @param customerBirthday customer's birthday to set
   */
  public void setCustomerBirthday(LocalDateTime customerBirthday) {
    this.customerBirthday = customerBirthday;
  }

  /**
   * Gets zip code
   *
   * @return customer's address
   */
  public AddressEntity getCustomerAddress() {
    return customerAddress;
  }

  /**
   * Sets zip
   *
   * @param customerAddress customer's address to set
   */
  public void setCustomerAddress(AddressEntity customerAddress) {
    this.customerAddress = customerAddress;
  }

  /**
   * Gets phone number
   *
   * @return customer's phone number
   */
  public String getCustomerTelephoneNo() {
    return customerTelephoneNo;
  }

  /**
   * Sets phone number
   *
   * @param customerTelephoneNo customer's phone number to set
   */
  public void setCustomerTelephoneNo(String customerTelephoneNo) {
    this.customerTelephoneNo = customerTelephoneNo;
  }

  /**
   * Gets e-mail
   *
   * @return customer's e-mail
   */
  public String getCustomerEmail() {
    return customerEmail;
  }

  /**
   * Sets e-mail
   *
   * @param customerEmail customer's e-mail to set
   */
  public void setCustomerEmail(String customerEmail) {
    this.customerEmail = customerEmail;
  }

  /**
   * Gets creation date
   *
   * @return customer's creation date
   */
  public LocalDateTime getCustomerCreationDate() {
    return customerCreationDate;
  }

  /**
   * Sets creation
   *
   * @param customerCreationDate customer's creation date to set
   */
  public void setCustomerCreationDate(LocalDateTime customerCreationDate) {
    this.customerCreationDate = customerCreationDate;
  }
}
