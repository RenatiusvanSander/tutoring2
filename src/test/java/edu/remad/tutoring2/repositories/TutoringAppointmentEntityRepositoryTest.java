package edu.remad.tutoring2.repositories;


import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import edu.remad.tutoring2.appconstants.AppStageConstants;
import edu.remad.tutoring2.appconstants.JavaAppConstants;
import edu.remad.tutoring2.appconstants.PackagesAppConstants;
import edu.remad.tutoring2.models.TutoringAppointmentEntity;
import edu.remad.tutoring2.systemenvironment.SystemEnvironment;
import edu.remad.tutoring2.systemenvironment.SystemEnvironmentFactory;
public class TutoringAppointmentEntityRepositoryTest extends AbstractJunit5JpaTest {

	@Autowired
	private TutoringAppointmentEntityRepository tutoringAppointmentEntityRepository;

	@Test
	public void saveTest() {
		TutoringAppointmentEntity appointment = createAppointment();
		
		TutoringAppointmentEntity savedAppointment = tutoringAppointmentEntityRepository.saveAndFlush(appointment);
		
		System.out.println(savedAppointment);
	}
}
