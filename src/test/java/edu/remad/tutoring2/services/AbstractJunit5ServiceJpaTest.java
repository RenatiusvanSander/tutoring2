package edu.remad.tutoring2.services;

import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import edu.remad.tutoring2.repositories.AbstractJunit5JpaTest;
import edu.remad.tutoring2.tutoringconfig.FreeMarkerConfig;
import edu.remad.tutoring2.tutoringconfig.SpringJavaMailConfig;

@SpringJUnitWebConfig(classes = {AbstractJunit5JpaTest.Config.class, SpringJavaMailConfig.class, ServicesTestConfig.class, AbstractJunit5ServiceJpaTest.Config.class, FreeMarkerConfig.class})
public abstract class AbstractJunit5ServiceJpaTest extends AbstractJunit5JpaTest {

	protected AbstractJunit5ServiceJpaTest() {
	}
}
