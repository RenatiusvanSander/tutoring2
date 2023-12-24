package edu.remad.tutoring2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.remad.tutoring2.models.ZipCodeEntity;

@Repository
public interface ZipCodeRepository extends JpaRepository<ZipCodeEntity, Long> {

}
