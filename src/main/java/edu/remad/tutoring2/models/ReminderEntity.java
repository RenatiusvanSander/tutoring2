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
  @JoinColumn(name = "reminder_customer_no", referencedColumnName = "customer_no")
  private Customer reminderCustomer;

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
   * @param reminderCustomer              reminder's customer number
   * @param reminderDate                  reminder's date
   * @param reminderCreationDate          reminder's creation date
   */
  public ReminderEntity(TutoringAppointmentEntity reminderTutoringAppointmentNo,
      Customer reminderCustomer,
      LocalDateTime reminderDate, LocalDateTime reminderCreationDate) {
    this.reminderTutoringAppointment = reminderTutoringAppointmentNo;
    this.reminderCustomer = reminderCustomer;
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
   * @param reminderTutoringAppointment given Reminder's tutoring appointment to set
   */
  public void setReminderTutoringAppointment(TutoringAppointmentEntity reminderTutoringAppointment) {
    this.reminderTutoringAppointment = reminderTutoringAppointment;
  }

  /**
   * Gets customer
   *
   * @return Reminder's customer
   */
  public Customer getReminderCustomer() {
    return reminderCustomer;
  }

  /**
   * Sets customer
   *
   * @param reminderCustomer given Reminder's customer to set
   */
  public void setReminderCustomer(Customer reminderCustomer) {
    this.reminderCustomer = reminderCustomer;
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
    return Objects.hash(reminderNo, reminderTutoringAppointment, reminderCustomer, reminderDate,
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
    return reminderNo == reminder.reminderNo
        && reminderTutoringAppointment == reminder.reminderTutoringAppointment
        && reminderCustomer == reminder.reminderCustomer && reminderDate.equals(
        reminder.reminderDate) && reminderCreationDate.equals(reminder.reminderCreationDate);
  }

  @Override
  public String toString() {
    return "Reminder{" +
        "reminderNo=" + reminderNo +
        ", reminderTutoringAppointmentNo=" + reminderTutoringAppointment +
        ", reminderCustomerNo=" + reminderCustomer +
        ", reminderDate=" + reminderDate +
        ", reminderCreationDate=" + reminderCreationDate +
        '}';
  }
}
