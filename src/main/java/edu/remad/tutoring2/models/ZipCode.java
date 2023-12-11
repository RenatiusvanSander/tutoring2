package edu.remad.tutoring2.models;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity is concerning the zip code of a location.
 */
//@Entity
@Table(name = "Zipcode")
public class ZipCode {

  /**
   * primary key for the zip code
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  /**
   * location of zip code
   */
  private String zipCodeLocation;

  /**
   * creation date of zip code
   */
  @Column(name = "zip_code_creation_date", columnDefinition = "TIMESTAMP")
  private LocalDateTime zipCodeCreationDate;

  /**
   * Constructor
   */
  public ZipCode() {
  }

  /**
   * Constructor
   *
   * @param zipCodeLocation     location of belonging zip code
   * @param zipCodeCreationDate creation time of the zip code
   */
  public ZipCode(String zipCodeLocation, LocalDateTime zipCodeCreationDate) {
    this.zipCodeLocation = zipCodeLocation;
    this.zipCodeCreationDate = zipCodeCreationDate;
  }

  /**
   * Constructor
   *
   * @param zipCodeLocation location of belonging zip code
   */
  public ZipCode(String zipCodeLocation) {
    this.zipCodeLocation = zipCodeLocation;
    this.zipCodeCreationDate = null;
  }

  /**
   * From creates {@link ZipCode}
   *
   * @param zipCode zip code to create a zip code to search for
   * @return zip code
   */
  public static ZipCode from(ZipCode zipCode) {
    return new ZipCode(zipCode.getZipCodeLocation());
  }

  /**
   * Gets location
   *
   * @return zipcode's location
   */
  public String getZipCodeLocation() {
    return zipCodeLocation;
  }

  /**
   * Sets location
   *
   * @param zipCodeLocation zipcode's location to set
   */
  public void setZipCodeLocation(String zipCodeLocation) {
    this.zipCodeLocation = zipCodeLocation;
  }

  /**
   * Gets zipcode
   *
   * @return zipcode
   */
  public long getId() {
    return id;
  }

  /**
   * Sets zip code
   *
   * @param id zip code to set
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Gets creation date
   *
   * @return zipcode's creation date
   */
  public LocalDateTime getZipCodeCreationDate() {
    return zipCodeCreationDate;
  }

  /**
   * Sets creation date
   *
   * @param zipCodeCreationDate zipcode's creation date to set
   */
  public void setZipCodeCreationDate(LocalDateTime zipCodeCreationDate) {
    this.zipCodeCreationDate = zipCodeCreationDate;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, zipCodeLocation, zipCodeCreationDate);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ZipCode)) {
      return false;
    }
    ZipCode zipCode1 = (ZipCode) o;
    return id == zipCode1.id && zipCodeLocation.equals(zipCode1.zipCodeLocation)
        && zipCodeCreationDate.equals(zipCode1.zipCodeCreationDate);
  }

  @Override
  public String toString() {
    return "ZipCode{" +
        "zipCode=" + id +
        ", zipCodeLocation='" + zipCodeLocation + '\'' +
        ", zipCodeCreationDate=" + zipCodeCreationDate +
        '}';
  }
}
