package edu.remad.tutoring2.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * A real user in universe of the tutoring app.
 */
//@Entity
@Table(name = "User")
public class User {

  /**
   * userNo, as synonym unique primary key, for a data set
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  /**
   * user's name
   */
  private String userName;

  /**
   * user's password
   */
  private String userPassword;

  /**
   * the user's e-mail
   */
  private String userEmail;

  /**
   * creation date of this data set
   */
  @Column(name = "user_creation_date", columnDefinition = "TIMESTAMP")
  private LocalDateTime userCreationDate;

  /**
   * Constructor
   */
  public User() {
  }

  /**
   * Constructor
   *
   * @param userName         user's name
   * @param userPassword     use's password
   * @param userEmail        user's e-mail
   * @param userCreationDate user's creation date
   */
  public User(String userName, String userPassword, String userEmail,
      LocalDateTime userCreationDate) {
    this.userName = userName;
    this.userPassword = userPassword;
    this.userEmail = userEmail;
    this.userCreationDate = userCreationDate;
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", userName='" + userName + '\'' +
        ", userPassword='" + userPassword + '\'' +
        ", userEmail='" + userEmail + '\'' +
        ", userCreationDate=" + userCreationDate +
        '}';
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getUserName(), getUserPassword(), getUserEmail(),
        getUserCreationDate());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof User)) {
      return false;
    }

    User user = (User) o;
    return getId() == user.getId() && getUserName().equals(user.getUserName())
        && getUserPassword().equals(user.getUserPassword()) && getUserEmail().equals(
        user.getUserEmail()) && getUserCreationDate().equals(user.getUserCreationDate());
  }

  /**
   * Gets user's number
   *
   * @return user's number as primary key
   */
  public long getId() {
    return this.id;
  }

  /**
   * Gets user's name
   *
   * @return user's name
   */
  public String getUserName() {
    return userName;
  }

  /**
   * Sets user's name
   *
   * @param userName user's name to set
   */
  public void setUserName(String userName) {
    this.userName = userName;
  }

  /**
   * Gets user's password
   *
   * @return user's password
   */
  public String getUserPassword() {
    return userPassword;
  }

  /**
   * Sets user's password
   *
   * @param userPassword user's password to set
   */
  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }

  /**
   * Gets user's e-mail
   *
   * @return user's e-mail
   */
  public String getUserEmail() {
    return userEmail;
  }

  /**
   * Sets user's email
   *
   * @param userEmail user's email to set
   */
  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  /**
   * Gets user's creation date
   *
   * @return user's creation date
   */
  public LocalDateTime getUserCreationDate() {
    return userCreationDate;
  }

  /**
   * Sets user's creation date
   *
   * @param userCreationDate user's creation date to set as timestamp
   */
  public void setUserCreationDate(LocalDateTime userCreationDate) {
    this.userCreationDate = userCreationDate;
  }
}
