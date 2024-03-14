package edu.remad.tutoring2.models;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import edu.remad.tutoring2.json.customdeserializers.ServiceContractEntityDeserializer;
import edu.remad.tutoring2.json.customserializer.ServiceContractEntitySerializer;

/**
 * Store data concerning the service contract we and customer committed and is
 * part of invoice.
 */
@Entity
@Table(name = "service_contracts")
@JsonSerialize(using = ServiceContractEntitySerializer.class)
@JsonDeserialize(using = ServiceContractEntityDeserializer.class)
public class ServiceContractEntity {

	/**
	 * service contract number as primary key
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "service_contract_no")
	private long serviceContractNo;

	/**
	 * name of service contract
	 */
	private String serviceContractName;

	/**
	 * description of service contract
	 */
	private String serviceContractDescription;

	/**
	 * creation date of service contract
	 */
	@Column(name = "service_contract_creation_date", columnDefinition = "TIMESTAMP")
	private LocalDateTime serviceContractCreationDate;

	/**
	 * Constructor
	 */
	public ServiceContractEntity() {
	}

	/**
	 * Constructor
	 *
	 * @param serviceContractNo           given service contract no
	 * @param serviceContractName         given service contract name
	 * @param serviceContractDescription  given service contract description
	 * @param serviceContractCreationDate creation date of service contract
	 */
	public ServiceContractEntity(Long serviceContractNo, String serviceContractName, String serviceContractDescription,
			LocalDateTime serviceContractCreationDate) {
		this.serviceContractNo = serviceContractNo;
		this.serviceContractName = serviceContractName;
		this.serviceContractDescription = serviceContractDescription;
		this.serviceContractCreationDate = serviceContractCreationDate;
	}

	/**
	 * Gets service contract number
	 *
	 * @return service contract's number
	 */
	public long getServiceContractNo() {
		return serviceContractNo;
	}

	/**
	 * Sets service contract number
	 *
	 * @param serviceContractNo service contract number to set as primary key AND
	 *                          Identifier
	 */
	public void setServiceContractNo(long serviceContractNo) {
		this.serviceContractNo = serviceContractNo;
	}

	/**
	 * Gets service contract name
	 *
	 * @return service contract's name
	 */
	public String getServiceContractName() {
		return serviceContractName;
	}

	/**
	 * Sets name.
	 *
	 * @param serviceContractName service contract's name to set
	 */
	public void setServiceContractName(String serviceContractName) {
		this.serviceContractName = serviceContractName;
	}

	/**
	 * Gets service contract's escription
	 *
	 * @return service contract's description
	 */
	public String getServiceContractDescription() {
		return serviceContractDescription;
	}

	/**
	 * Sets service contract description
	 *
	 * @param serviceContractDescription service contract's description to set
	 */
	public void setServiceContractDescription(String serviceContractDescription) {
		this.serviceContractDescription = serviceContractDescription;
	}

	/**
	 * Gets service contract cration date
	 *
	 * @return service contract's create date
	 */
	public LocalDateTime getServiceContractCreationDate() {
		return serviceContractCreationDate;
	}

	/**
	 * Sets service contract creation dte
	 *
	 * @param serviceContractCreationDate service contract's creation date to set
	 */
	public void setServiceContractCreationDate(LocalDateTime serviceContractCreationDate) {
		this.serviceContractCreationDate = serviceContractCreationDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(serviceContractNo, serviceContractName, serviceContractDescription,
				serviceContractCreationDate);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof ServiceContractEntity)) {
			return false;
		}
		ServiceContractEntity that = (ServiceContractEntity) o;
		return serviceContractNo == that.serviceContractNo && serviceContractName.equals(that.serviceContractName)
				&& serviceContractDescription.equals(that.serviceContractDescription)
				&& serviceContractCreationDate.equals(that.serviceContractCreationDate);
	}

	@Override
	public String toString() {
		return "ServiceContract{" + "serviceContractNo=" + serviceContractNo + ", serviceContractName='"
				+ serviceContractName + '\'' + ", serviceContractDescription='" + serviceContractDescription + '\''
				+ ", serviceContractCreationDate=" + serviceContractCreationDate + '}';
	}
}
