package edu.remad.tutoring2.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * a data set entry for tutoring appointment
 */
@Entity
@Table(name = "tutoring_appointments")
public class TutoringAppointmentEntity {

	/**
	 * tutoring appointment number as primary key of a tutoring appointment
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tutoring_appointment_no")
	private long tutoringAppointmentNo;

	/**
	 * tutoring appointment's customer number
	 */
	@OneToOne
	@JoinColumn(name = "tutoring_appointment_user_id", referencedColumnName = "userentity_id")
	private UserEntity tutoringAppointmentUser;

	/**
	 * date of tutoring appointment
	 */
	@Column(name = "tutoring_appointment_date", columnDefinition = "TIMESTAMP")
	private LocalDateTime tutoringAppointmentDate;

	/**
	 * tutoring appointment's time as start date time
	 */
	@Column(name = "tutoring_appointment_start_date_time", columnDefinition = "TIMESTAMP")
	private LocalDateTime tutoringAppointmentStartDateTime;

	/**
	 * tutoring appointment's time as end date time
	 */
	@Column(name = "tutoring_appointment_end_date_time", columnDefinition = "TIMESTAMP")
	private LocalDateTime tutoringAppointmentEndDateTime;

	/**
	 * tutoring appointment's creation date
	 */
	@Column(name = "tutoring_appointment_creation_date", columnDefinition = "TIMESTAMP")
	private LocalDateTime tutoringAppointmentCreationDate;

	/**
	 * Constructor
	 */
	public TutoringAppointmentEntity() {
	}

	/**
	 * Constructor
	 *
	 * @param tutoringAppointmentNo            tutoring appointment's number
	 * @param tutoringAppointmentUser          tutoring appointment's user number
	 * @param tutoringAppointmentDate          tutoring appointment's date
	 * @param tutoringAppointmentStartDateTime tutoring appointment's start date
	 *                                         time
	 * @param tutoringAppointmentEndDateTime   tutoring appointment's end date time
	 * @param tutoringAppointmentCreationDate  tutoring appointment's creation date
	 */
	public TutoringAppointmentEntity(long tutoringAppointmentNo, UserEntity tutoringAppointmentUser,
			LocalDateTime tutoringAppointmentDate, LocalDateTime tutoringAppointmentStartDateTime,
			LocalDateTime tutoringAppointmentEndDateTime, LocalDateTime tutoringAppointmentCreationDate) {
		this.tutoringAppointmentNo = tutoringAppointmentNo;
		this.tutoringAppointmentUser = tutoringAppointmentUser;
		this.tutoringAppointmentDate = tutoringAppointmentDate;
		this.tutoringAppointmentStartDateTime = tutoringAppointmentStartDateTime;
		this.tutoringAppointmentEndDateTime = tutoringAppointmentEndDateTime;
		this.tutoringAppointmentCreationDate = tutoringAppointmentCreationDate;
	}

	/**
	 * Gets tutoring appointment's number.
	 *
	 * @return tutoring appointment's number
	 */
	public long getTutoringAppointmentNo() {
		return tutoringAppointmentNo;
	}

	/**
	 * Sets tutoring appointment's number
	 *
	 * @param tutoringAppointmentNo tutoring appointment's number to set
	 */
	public void setTutoringAppointmentNo(long tutoringAppointmentNo) {
		this.tutoringAppointmentNo = tutoringAppointmentNo;
	}

	/**
	 * Gets user.
	 *
	 * @return tutoring appointment's user
	 */
	public UserEntity getTutoringAppointmentUser() {
		return tutoringAppointmentUser;
	}

	/**
	 * Sets user
	 *
	 * @param tutoringAppointmentUser tutoring appointment's user to set
	 */
	public void setTutoringAppointmentUser(UserEntity tutoringAppointmentUser) {
		this.tutoringAppointmentUser = tutoringAppointmentUser;
	}

	/**
	 * Gets date of appointment
	 *
	 * @return tutoring appointment's date
	 */
	public LocalDateTime getTutoringAppointmentDate() {
		return tutoringAppointmentDate;
	}

	/**
	 * Sets appointment date
	 *
	 * @param tutoringAppointmentDate tutoring appointment's date to set
	 */
	public void setTutoringAppointmentDate(LocalDateTime tutoringAppointmentDate) {
		this.tutoringAppointmentDate = tutoringAppointmentDate;
	}

	/**
	 * Gets appointment start date time
	 *
	 * @return tutoring appointment's date time
	 */
	public LocalDateTime getTutoringAppointmentStartDateTime() {
		return tutoringAppointmentStartDateTime;
	}

	/**
	 * Sets appointment start date time
	 *
	 * @param tutoringAppointmentDateTime tutoring appointment's start date time to
	 *                                    set
	 */
	public void setTutoringAppointmentStartDateTime(LocalDateTime tutoringAppointmentDateTime) {
		this.tutoringAppointmentStartDateTime = tutoringAppointmentDateTime;
	}

	/**
	 * @return tutoringAppointmentDateTime tutoring appointment's end date time
	 */
	public LocalDateTime getTutoringAppointmentEndDateTime() {
		return tutoringAppointmentEndDateTime;
	}

	/**
	 * Sets appointment end date time
	 * 
	 * @param tutoringAppointmentEndDateTime tutoringAppointmentDateTime tutoring
	 *                                       appointment's end date time
	 */
	public void setTutoringAppointmentEndDateTime(LocalDateTime tutoringAppointmentEndDateTime) {
		this.tutoringAppointmentEndDateTime = tutoringAppointmentEndDateTime;
	}

	/**
	 * Gets creation date
	 *
	 * @return tutoring appointment's creation date
	 */
	public LocalDateTime getTutoringAppointmentCreationDate() {
		return tutoringAppointmentCreationDate;
	}

	/**
	 * Sets creation date
	 *
	 * @param tutoringAppointmentCreationDate tutoring appointment's creation date
	 *                                        to set
	 */
	public void setTutoringAppointmentCreationDate(LocalDateTime tutoringAppointmentCreationDate) {
		this.tutoringAppointmentCreationDate = tutoringAppointmentCreationDate;
	}
}
