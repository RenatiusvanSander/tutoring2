package edu.remad.tutoring2.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.remad.tutoring2.models.InvoiceEntity;
import edu.remad.tutoring2.models.UserEntity;

@Repository
public interface InvoiceEntityRepository extends JpaRepository<InvoiceEntity, Long> {

	List<InvoiceEntity> findByInvoiceUserAndInvoiceDate(@Param("invoiceUser") UserEntity invoiceUser, @Param("invoiceDate") LocalDateTime invoiceDate);

	default List<InvoiceEntity> findAllByInvoiceDate(@Param("invoiceDate") LocalDateTime invoiceDate) {
		return findByInvoiceDateBetween(invoiceDate.toLocalDate().atStartOfDay(), invoiceDate.toLocalDate().plusDays(1).atStartOfDay());
		
	}

	/**
	 * Finds invoices by the date
	 * 
	 * @param atStartOfDay start of given day
	 * @param atEndOfDay end of given day
	 * @return all found invoices between start of day and end of day
	 */
	List<InvoiceEntity> findByInvoiceDateBetween(LocalDateTime atStartOfDay, LocalDateTime atEndOfDay);
}
