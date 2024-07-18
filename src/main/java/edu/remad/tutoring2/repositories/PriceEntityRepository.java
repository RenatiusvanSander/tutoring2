package edu.remad.tutoring2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.remad.tutoring2.models.PriceEntity;
import edu.remad.tutoring2.models.ServiceContractEntity;
import edu.remad.tutoring2.models.UserEntity;

@Repository
public interface PriceEntityRepository  extends JpaRepository<PriceEntity, Long> {

	PriceEntity findByUserAndServiceContract(@Param("user") UserEntity user,
            @Param("serviceContract") ServiceContractEntity serviceContract);
}
