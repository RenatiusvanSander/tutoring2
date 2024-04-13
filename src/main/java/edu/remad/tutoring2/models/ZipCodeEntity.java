package edu.remad.tutoring2.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import edu.remad.tutoring2.json.customdeserializers.ZipCodeEntityDeserializer;

/**
 * Entity is concerning the zip code of a location.
 */
@Entity
@Table(name = "Zipcode")
// @JsonSerialize(using = ZipCodeEntitySerializer.class)
@JsonDeserialize(using = ZipCodeEntityDeserializer.class)
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
	 */
	public ZipCodeEntity(String zipCode, String zipCodeLocation, LocalDateTime zipCodeCreationDate) {
		this.zipCode = zipCode;
		this.zipCodeLocation = zipCodeLocation;
		this.zipCodeCreationDate = zipCodeCreationDate;
	}

	/**
	 * Constructor
	 *
	 * @param id                  zip code's identity
	 * @param zipCode             zip code itself
	 * @param zipCodeLocation     location of belonging zip code
	 * @param zipCodeCreationDate creation time of the zip code. When {@code null} date of today with start of day is set.
	 */
	public ZipCodeEntity(Long id, String zipCode, String zipCodeLocation, LocalDateTime zipCodeCreationDate) {
		this.id = id;
		this.zipCode = zipCode;
		this.zipCodeLocation = zipCodeLocation;
		this.zipCodeCreationDate = zipCodeCreationDate != null ? zipCodeCreationDate : LocalDate.now().atStartOfDay().plusHours(0);
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
		return new ZipCodeEntity(zipCode.getZipCode(), zipCode.getZipCodeLocation(), zipCode.getZipCodeCreationDate());
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
