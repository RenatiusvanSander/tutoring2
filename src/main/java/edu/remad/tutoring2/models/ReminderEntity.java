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
 * Reminder represents calendar reminder of a tutoring appointment.
 */
@Entity
@Table(name = "reminder_entities")
//@JsonSerialize(using = ReminderEntitySerializer.class)
//@JsonDeserialize(using = ReminderEntityDeserializer.class)
public class ReminderEntity {

	/**
	 * reminder number as primary key of a data set of 'Reminder'
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "reminder_no")
	private long reminderNo;

	/**
	 * reminder's tutoring appointment number
	 */
	@OneToOne
	@JoinColumn(name = "tutoring_appointment_id", referencedColumnName = "tutoring_appointment_no")
	private TutoringAppointmentEntity reminderTutoringAppointment;

	/**
	 * reminder's customer number
	 */
	@OneToOne
	@JoinColumn(name = "reminder_user_entity_no", referencedColumnName = "id")
	private UserEntity reminderUserEntity;

	/**
	 * reminder's date
	 */
	@Column(name = "reminder_date", columnDefinition = "TIMESTAMP")
	private LocalDateTime reminderDate;

	/**
	 * reminder's creation date of data set
	 */
	@Column(name = "reminder_creation_date", columnDefinition = "TIMESTAMP")
	private LocalDateTime reminderCreationDate;

	/**
	 * Constructor
	 */
	public ReminderEntity() {
	}

	/**
	 * Constructor
	 *
	 * @param reminderTutoringAppointmentNo reminder's tutoring appointment number
	 * @param reminderUserEntity            reminder's user id
	 * @param reminderDate                  reminder's date
	 * @param reminderCreationDate          reminder's creation date
	 */
	public ReminderEntity(TutoringAppointmentEntity reminderTutoringAppointmentNo, UserEntity reminderUserEntity,
			LocalDateTime reminderDate, LocalDateTime reminderCreationDate) {
		this.reminderTutoringAppointment = reminderTutoringAppointmentNo;
		this.reminderUserEntity = reminderUserEntity;
		this.reminderDate = reminderDate;
		this.reminderCreationDate = reminderCreationDate;
	}

	/**
	 * Constructor
	 *
	 * @param reminderNo                    reminder's number
	 * @param reminderTutoringAppointmentNo reminder's tutoring appointment number
	 * @param reminderUserEntity            reminder's user id
	 * @param reminderDate                  reminder's date
	 * @param reminderCreationDate          reminder's creation date
	 */
	public ReminderEntity(Long reminderNo, TutoringAppointmentEntity reminderTutoringAppointmentNo,
			UserEntity reminderUserEntity, LocalDateTime reminderDate, LocalDateTime reminderCreationDate) {
		this.reminderNo = reminderNo;
		this.reminderTutoringAppointment = reminderTutoringAppointmentNo;
		this.reminderUserEntity = reminderUserEntity;
		this.reminderDate = reminderDate;
		this.reminderCreationDate = reminderCreationDate;
	}

	/**
	 * Gets reminder number, here the id
	 *
	 * @return Reminder's number, primary key
	 */
	public long getReminderNo() {
		return reminderNo;
	}

	/**
	 * Gets tutoring appointment
	 *
	 * @return Reminder's tutoring appointment
	 */
	public TutoringAppointmentEntity getReminderTutoringAppointment() {
		return reminderTutoringAppointment;
	}

	/**
	 * Sets appointment
	 *
	 * @param reminderTutoringAppointment given Reminder's tutoring appointment to
	 *                                    set
	 */
	public void setReminderTutoringAppointment(TutoringAppointmentEntity reminderTutoringAppointment) {
		this.reminderTutoringAppointment = reminderTutoringAppointment;
	}

	/**
	 * Gets user
	 *
	 * @return Reminder's user entity
	 */
	public UserEntity getReminderUserEntity() {
		return reminderUserEntity;
	}

	/**
	 * Sets user entity
	 *
	 * @param reminderUserEntity given Reminder's user entity to set
	 */
	public void setReminderUserEntity(UserEntity reminderUserEntity) {
		this.reminderUserEntity = reminderUserEntity;
	}

	/**
	 * Gets date
	 *
	 * @return Reminder's date
	 */
	public LocalDateTime getReminderDate() {
		return reminderDate;
	}

	/**
	 * Sets date
	 *
	 * @param reminderDate Reminder's date to set
	 */
	public void setReminderDate(LocalDateTime reminderDate) {
		this.reminderDate = reminderDate;
	}

	/**
	 * Gets creation date
	 *
	 * @return Reminder's creation date
	 */
	public LocalDateTime getReminderCreationDate() {
		return reminderCreationDate;
	}

	/**
	 * Sets creation date
	 *
	 * @param reminderCreationDate Reminder's creation date to set
	 */
	public void setReminderCreationDate(LocalDateTime reminderCreationDate) {
		this.reminderCreationDate = reminderCreationDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(reminderNo, reminderTutoringAppointment, reminderUserEntity, reminderDate,
				reminderCreationDate);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (!(o instanceof ReminderEntity)) {
			return false;
		}

		ReminderEntity reminder = (ReminderEntity) o;
		return reminderNo == reminder.reminderNo && reminderTutoringAppointment == reminder.reminderTutoringAppointment
				&& reminderUserEntity == reminder.reminderUserEntity && reminderDate.equals(reminder.reminderDate)
				&& reminderCreationDate.equals(reminder.reminderCreationDate);
	}

	@Override
	public String toString() {
		return "ReminderEntity [reminderNo=" + reminderNo + ", reminderTutoringAppointment="
				+ reminderTutoringAppointment + ", reminderUserEntity=" + reminderUserEntity + ", reminderDate="
				+ reminderDate + ", reminderCreationDate=" + reminderCreationDate + "]";
	}
}
