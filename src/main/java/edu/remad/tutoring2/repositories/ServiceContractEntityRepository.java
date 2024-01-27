package edu.remad.tutoring2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.remad.tutoring2.models.ServiceContractEntity;

public interface ServiceContractEntityRepository extends JpaRepository<ServiceContractEntity, Long> {

}
