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

/**
 * a data set entry for tutoring appointment
 */
//@Entity
public class TutoringAppointment {

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
  @JoinColumn(name = "tutoring_appointment_customer_id", referencedColumnName = "customer_no")
  private Customer tutoringAppointmentCustomer;

  /**
   * date of tutoring appointment
   */
  @Column(name = "tutoring_appointment_date", columnDefinition = "TIMESTAMP")
  private LocalDateTime tutoringAppointmentDate;

  /**
   * tutoring appointment's time as date time
   */
  @Column(name = "tutoring_appointment_date_time", columnDefinition = "TIMESTAMP")
  private LocalDateTime tutoringAppointmentDateTime;

  /**
   * tutoring appointment's creation date
   */
  @Column(name = "tutoring_appointment_creation_date", columnDefinition = "TIMESTAMP")
  private LocalDateTime tutoringAppointmentCreationDate;

  /**
   * Constructor
   */
  public TutoringAppointment() {
  }

  /**
   * Constructor
   *
   * @param tutoringAppointmentCustomer     tutoring appointment's customer number
   * @param tutoringAppointmentDate         tutoring appointment's date
   * @param tutoringAppointmentDateTime     tutoring appointment's date time
   * @param tutoringAppointmentCreationDate tutoring appointment's creation date
   */
  public TutoringAppointment(Customer tutoringAppointmentCustomer,
      LocalDateTime tutoringAppointmentDate, LocalDateTime tutoringAppointmentDateTime,
      LocalDateTime tutoringAppointmentCreationDate) {
    this.tutoringAppointmentCustomer = tutoringAppointmentCustomer;
    this.tutoringAppointmentDate = tutoringAppointmentDate;
    this.tutoringAppointmentDateTime = tutoringAppointmentDateTime;
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
   * Gets customer.
   *
   * @return tutoring appointment's customer
   */
  public Customer getTutoringAppointmentCustomer() {
    return tutoringAppointmentCustomer;
  }

  /**
   * Sets customer
   *
   * @param tutoringAppointmentCustomer tutoring appointment's customer to set
   */
  public void setTutoringAppointmentCustomer(Customer tutoringAppointmentCustomer) {
    this.tutoringAppointmentCustomer = tutoringAppointmentCustomer;
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
   * Gets appointment date time
   *
   * @return tutoring appointment's date time
   */
  public LocalDateTime getTutoringAppointmentDateTime() {
    return tutoringAppointmentDateTime;
  }

  /**
   * Sets appointment date time
   *
   * @param tutoringAppointmentDateTime tutoring appointment's date time to set
   */
  public void setTutoringAppointmentDateTime(LocalDateTime tutoringAppointmentDateTime) {
    this.tutoringAppointmentDateTime = tutoringAppointmentDateTime;
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
   * @param tutoringAppointmentCreationDate tutoring appointment's creation date to set
   */
  public void setTutoringAppointmentCreationDate(LocalDateTime tutoringAppointmentCreationDate) {
    this.tutoringAppointmentCreationDate = tutoringAppointmentCreationDate;
  }

  @Override
  public int hashCode() {
    return Objects.hash(tutoringAppointmentNo, tutoringAppointmentCustomer,
        tutoringAppointmentDate,
        tutoringAppointmentDateTime, tutoringAppointmentCreationDate);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TutoringAppointment)) {
      return false;
    }
    TutoringAppointment that = (TutoringAppointment) o;
    return tutoringAppointmentNo == that.tutoringAppointmentNo
        && tutoringAppointmentCustomer == that.tutoringAppointmentCustomer
        && tutoringAppointmentDate.equals(that.tutoringAppointmentDate)
        && tutoringAppointmentDateTime.equals(that.tutoringAppointmentDateTime)
        && tutoringAppointmentCreationDate.equals(that.tutoringAppointmentCreationDate);
  }

  @Override
  public String toString() {
    return "TutoringAppointment{" +
        "tutoringAppointmentNo=" + tutoringAppointmentNo +
        ", tutoringAppointmentCustomerNo=" + tutoringAppointmentCustomer +
        ", tutoringAppointmentDate=" + tutoringAppointmentDate +
        ", tutoringAppointmentDateTime=" + tutoringAppointmentDateTime +
        ", tutoringAppointmentCreationDate=" + tutoringAppointmentCreationDate +
        '}';
  }
}
