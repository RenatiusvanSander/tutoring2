package edu.remad.tutoring2.models;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Entity is concerning the zip code of a location.
 */
@Entity
@Table(name = "Zipcode")
public class ZipCodeEntity {

	/**
	 * primary key for the zip code
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	/**
	 * zip code
	 */
	private String zipCode;

	/**
	 * location of zip code
	 */
	private String zipCodeLocation;

	/**
	 * creation date of zip code
	 */
	@Column(name = "zip_code_creation_date", columnDefinition = "TIMESTAMP")
	private LocalDateTime zipCodeCreationDate;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private AddressEntity address;

	/**
	 * Constructor
	 */
	public ZipCodeEntity() {
	}

	/**
	 * Constructor
	 *
	 * @param zipCode             zip code itself
	 * @param zipCodeLocation     location of belonging zip code
	 * @param zipCodeCreationDate creation time of the zip code
	 * @param address             address of the zip code
	 */
	public ZipCodeEntity(String zipCode, String zipCodeLocation, LocalDateTime zipCodeCreationDate,
			AddressEntity address) {
		this.zipCode = zipCode;
		this.zipCodeLocation = zipCodeLocation;
		this.zipCodeCreationDate = zipCodeCreationDate;
		this.address = address;
	}

	/**
	 * Constructor
	 *
	 * @param zipCodeLocation location of belonging zip code
	 */
	public ZipCodeEntity(String zipCode, String zipCodeLocation, AddressEntity address) {
		this.zipCode = zipCode;
		this.zipCodeLocation = zipCodeLocation;
		this.zipCodeCreationDate = null;
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * From creates {@link ZipCodeEntity}
	 *
	 * @param zipCode zip code to create a zip code to search for
	 * @return zip code
	 */
	public static ZipCodeEntity from(ZipCodeEntity zipCode) {
		return new ZipCodeEntity(zipCode.getZipCode(), zipCode.getZipCodeLocation(), zipCode.getZipCodeCreationDate(),
				zipCode.getAddress());
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

	public AddressEntity getAddress() {
		return address;
	}

	public void setAddress(AddressEntity address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, zipCode, zipCodeCreationDate, zipCodeLocation);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ZipCodeEntity other = (ZipCodeEntity) obj;
		return id == other.id && Objects.equals(zipCode, other.zipCode)
				&& Objects.equals(zipCodeCreationDate, other.zipCodeCreationDate)
				&& Objects.equals(zipCodeLocation, other.zipCodeLocation);
	}

	@Override
	public String toString() {
		return "ZipCode [id=" + id + ", zipCode=" + zipCode + ", zipCodeLocation=" + zipCodeLocation
				+ ", zipCodeCreationDate=" + zipCodeCreationDate + "]";
	}
}
