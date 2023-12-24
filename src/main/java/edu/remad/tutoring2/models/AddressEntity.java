package edu.remad.tutoring2.models;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Address")
public class AddressEntity {

  /**
   * the primary key for an address
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  /**
   * the street
   */
  private String addressStreet;

  /**
   * house number
   */
  private String addressHouseNo;

  /**
   * customer's zip code
   */
  @OneToOne
  @JoinColumn(name = "zipcode_id", referencedColumnName = "id")
  private ZipCodeEntity addressZipCode;

  /**
   *Constructor
   *
   * @param addressStreet address's street
   * @param addressHouseNo address's house number
   * @param addressZipCode address's zipcode
   */
  public AddressEntity(String addressStreet, String addressHouseNo, ZipCodeEntity addressZipCode) {
    this.addressStreet = addressStreet;
    this.addressHouseNo = addressHouseNo;
    this.addressZipCode = addressZipCode;
  }

  /**
   * Constructor
   */
  public AddressEntity() {
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getAddressStreet(), getAddressHouseNo(), addressZipCode);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AddressEntity)) {
      return false;
    }
    AddressEntity address = (AddressEntity) o;
    return getId() == address.getId() && getAddressStreet().equals(address.getAddressStreet())
        && getAddressHouseNo().equals(address.getAddressHouseNo()) && addressZipCode.equals(
        address.addressZipCode);
  }

  @Override
  public String toString() {
    return "Address{" +
        "id=" + id +
        ", addressStreet='" + addressStreet + '\'' +
        ", addressHouseNo='" + addressHouseNo + '\'' +
        ", addressZipCode=" + addressZipCode +
        '}';
  }

  /**
   * Gets Id
   *
   * @return Adress's Id
   */
  public long getId() {
    return id;
  }

  /**
   * Gets street
   *
   * @return adress's street
   */
  public String getAddressStreet() {
    return addressStreet;
  }

  /**
   * Sets street
   *
   * @param addressStreet address's street to set
   */
  public void setAddressStreet(String addressStreet) {
    this.addressStreet = addressStreet;
  }

  /**
   * Gets house number
   *
   * @return address's house number
   */
  public String getAddressHouseNo() {
    return addressHouseNo;
  }

  /**
   * Sets house number
   *
   * @param addressHouseNo address's house number
   */
  public void setAddressHouseNo(String addressHouseNo) {
    this.addressHouseNo = addressHouseNo;
  }
}
