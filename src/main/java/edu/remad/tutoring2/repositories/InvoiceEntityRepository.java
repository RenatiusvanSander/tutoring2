package edu.remad.tutoring2.repositories;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.remad.tutoring2.models.InvoiceEntity;
import edu.remad.tutoring2.models.UserEntity;

@Repository
public interface InvoiceEntityRepository extends JpaRepository<InvoiceEntity, Long> {

	List<InvoiceEntity> findByUserAndInvoiceDate(@Param("user") UserEntity user, @Param("invoiceDate") LocalDateTime invoiceDate);
}
