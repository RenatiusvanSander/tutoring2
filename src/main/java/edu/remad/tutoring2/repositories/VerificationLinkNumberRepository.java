package edu.remad.tutoring2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.remad.tutoring2.models.VerificationLinkNumberEntity;

@Repository
public interface VerificationLinkNumberRepository extends JpaRepository<VerificationLinkNumberEntity, String> {
	VerificationLinkNumberEntity findByEmail(String email);

	VerificationLinkNumberEntity findByVerificationLinkNumber(String verificationLinkNumber);
}
