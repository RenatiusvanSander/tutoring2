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
 * customer's invoice for a done tutoring appointment.
 */
//@Entity
@Table(name = "Invoice")
public class Invoice {

  /**
   * invoice number of done tutoring and primary key for invoices
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "invoice_no")
  private long invoiceNo;

  /**
   * service contract of invoice
   */
  @OneToOne
  @JoinColumn(name = "service_contract_id", referencedColumnName = "service_contract_no")
  private ServiceContractEntity invoiceServiceContract;

  /**
   * amount of done tutoring hours to pay for
   */
  private float invoiceTutoringHours;

  /**
   * date of invoice
   */
  @Column(name = "invoice_date", columnDefinition = "TIMESTAMP")
  private LocalDateTime invoiceDate;

  /**
   * tutoring date of the invoice
   */
  @Column(name = "invoice_tutoring_date", columnDefinition = "TIMESTAMP")
  private LocalDateTime invoiceTutoringDate;

  /**
   * customer's no of invoice
   */
  @OneToOne
  @JoinColumn(name = "invoice_customer", referencedColumnName = "customer_no")
  private Customer invoiceCustomer;

  /**
   * creation date of invoice
   */
  @Column(name = "invoice_creation_date", columnDefinition = "TIMESTAMP")
  private LocalDateTime invoiceCreationDate;

  /**
   * Constructor
   */
  public Invoice() {
  }

  /**
   * Constructor
   *
   * @param invoiceServiceContract given invoice's service contract
   * @param invoiceTutoringHours   given invoice's tutoring hours
   * @param invoiceDate            given invoice's date
   * @param invoiceTutoringDate    given invoice's tutoring date
   * @param invoiceCustomer        given invoice's customer no
   * @param invoiceCreationDate    given invoice's creation date of this data set
   */
  public Invoice(ServiceContractEntity invoiceServiceContract,
      float invoiceTutoringHours,
      LocalDateTime invoiceDate,
      LocalDateTime invoiceTutoringDate,
      Customer invoiceCustomer,
      LocalDateTime invoiceCreationDate) {
    this.invoiceServiceContract = invoiceServiceContract;
    this.invoiceTutoringHours = invoiceTutoringHours;
    this.invoiceDate = invoiceDate;
    this.invoiceTutoringDate = invoiceTutoringDate;
    this.invoiceCustomer = invoiceCustomer;
    this.invoiceCreationDate = invoiceCreationDate;
  }

  /**
   * Gets number
   *
   * @return invoice's number
   */
  public long getInvoiceNo() {
    return invoiceNo;
  }

  /**
   * Sets number
   *
   * @param invoiceNo invoice's number to set
   */
  public void setInvoiceNo(long invoiceNo) {
    this.invoiceNo = invoiceNo;
  }

  /**
   * Gets service contract
   *
   * @return invoice's service contract
   */
  public ServiceContractEntity getInvoiceServiceContract() {
    return invoiceServiceContract;
  }

  /**
   * Sets service contract
   *
   * @param invoiceServiceContract invoice's service contract to set
   */
  public void setInvoiceServiceContract(ServiceContractEntity invoiceServiceContract) {
    this.invoiceServiceContract = invoiceServiceContract;
  }

  /**
   * Gets tutoring hours
   *
   * @return invoice's tutoring hours
   */
  public float getInvoiceTutoringHours() {
    return invoiceTutoringHours;
  }

  /**
   * Sets tutoring hours
   *
   * @param invoiceTutoringHours invoice's tutoring hours to set
   */
  public void setInvoiceTutoringHours(float invoiceTutoringHours) {
    this.invoiceTutoringHours = invoiceTutoringHours;
  }

  /**
   * Gets date of invoice
   *
   * @return invoice's date
   */
  public LocalDateTime getInvoiceDate() {
    return invoiceDate;
  }

  /**
   * Sets date of invoice
   *
   * @param invoiceDate invoice's date to set
   */
  public void setInvoiceDate(LocalDateTime invoiceDate) {
    this.invoiceDate = invoiceDate;
  }

  /**
   * Gets tutoring date
   *
   * @return invoice's tutoring date
   */
  public LocalDateTime getInvoiceTutoringDate() {
    return invoiceTutoringDate;
  }

  /**
   * Sets tutoring date
   *
   * @param invoiceTutoringDate invoice's tutoring date to set
   */
  public void setInvoiceTutoringDate(LocalDateTime invoiceTutoringDate) {
    this.invoiceTutoringDate = invoiceTutoringDate;
  }

  /**
   * Gets customer number.
   *
   * @return invoice's customer
   */
  public Customer getInvoiceCustomer() {
    return invoiceCustomer;
  }

  /**
   * Sets customer number.
   *
   * @param invoiceCustomer invoice's customer to set
   */
  public void setInvoiceCustomer(Customer invoiceCustomer) {
    this.invoiceCustomer = invoiceCustomer;
  }

  /**
   * Gets creation date
   *
   * @return invoice's creation date
   */
  public LocalDateTime getInvoiceCreationDate() {
    return invoiceCreationDate;
  }

  /**
   * Sets creation date
   *
   * @param invoiceCreationDate invoice's creation date to set
   */
  public void setInvoiceCreationDate(LocalDateTime invoiceCreationDate) {
    this.invoiceCreationDate = invoiceCreationDate;
  }

  @Override
  public int hashCode() {
    return Objects.hash(invoiceNo, invoiceServiceContract, invoiceTutoringHours, invoiceDate,
        invoiceTutoringDate, invoiceCustomer, invoiceCreationDate);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Invoice)) {
      return false;
    }

    Invoice invoice = (Invoice) o;
    return invoiceNo == invoice.invoiceNo
        && invoiceServiceContract == invoice.invoiceServiceContract
        && Float.compare(invoice.invoiceTutoringHours, invoiceTutoringHours) == 0
        && invoiceCustomer == invoice.invoiceCustomer && invoiceDate.equals(invoice.invoiceDate)
        && invoiceTutoringDate.equals(invoice.invoiceTutoringDate) && invoiceCreationDate.equals(
        invoice.invoiceCreationDate);
  }

  @Override
  public String toString() {
    return "Invoice{" +
        "invoiceNo=" + invoiceNo +
        ", invoiceServiceContractNo=" + invoiceServiceContract +
        ", invoiceTutoringHours=" + invoiceTutoringHours +
        ", invoiceDate=" + invoiceDate +
        ", invoiceTutoringDate=" + invoiceTutoringDate +
        ", invoiceCustomerNo=" + invoiceCustomer +
        ", invoiceCreationDate=" + invoiceCreationDate +
        '}';
  }
}
