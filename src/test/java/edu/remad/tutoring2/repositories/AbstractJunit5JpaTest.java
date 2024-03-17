package edu.remad.tutoring2.repositories;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
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
import edu.remad.tutoring2.controllers.AbstractJunit5Test;
import edu.remad.tutoring2.systemenvironment.SystemEnvironment;
import edu.remad.tutoring2.systemenvironment.SystemEnvironmentFactory;

@SpringJUnitWebConfig(AbstractJunit5JpaTest.Config.class)
@Transactional
public abstract class AbstractJunit5JpaTest extends AbstractJunit5Test{

	@Autowired
	private WebApplicationContext webAppContext;

	@Test
	void givenWebAppContext_WhenInjected_ThenItShouldNotBeNull() {
		assertNotNull(webAppContext);
	}
	
	@Configuration
	@EnableJpaRepositories(basePackages = PackagesAppConstants.EDU_REMAD_TUTORING2_REPOSITORIES)
	protected static class Config {

		@Bean
		public SystemEnvironment systemEnvironment() {
			return SystemEnvironmentFactory.getInstance();
		}

		@Bean
		public LocalContainerEntityManagerFactoryBean entityManagerFactory(SystemEnvironment systemEnvironment) {
			final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
			entityManagerFactoryBean.setDataSource(dataSource(systemEnvironment));
			entityManagerFactoryBean
					.setPackagesToScan(new String[] { PackagesAppConstants.EDU_REMAD_TUTORING2_MODELS });

			final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
			entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
			entityManagerFactoryBean.setJpaProperties(additionalProperties());

			return entityManagerFactoryBean;
		}

		private final Properties additionalProperties() {
			final Properties hibernateProperties = new Properties();
			hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
			hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
			hibernateProperties.setProperty("hibernate.cache.use_second_level_cache", JavaAppConstants.FALSE);
			hibernateProperties.setProperty("hibernate.cache.use_query_cache", JavaAppConstants.FALSE);
			hibernateProperties.setProperty("hibernate.show_sql", JavaAppConstants.TRUE);
			hibernateProperties.setProperty("hibernate.format_sql", JavaAppConstants.TRUE);

			return hibernateProperties;
		}

		@Bean
		public DataSource dataSource(SystemEnvironment systemEnvironment) {
			final DriverManagerDataSource dataSource = new DriverManagerDataSource();
			dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

			if (AppStageConstants.DEVELOPMENT.getStatusName().equals(systemEnvironment.getAppStage())) {
				dataSource.setUrl(systemEnvironment.getAppDataDevelopmentSourcesMysqlUrl());
				dataSource.setUsername(systemEnvironment.getAppDevelopmentDataSourcesMysqlUsername());
				dataSource.setPassword(systemEnvironment.getAppDevelopmentDataSourcesMysqlPassword());
			} else {
				dataSource.setUrl(systemEnvironment.getAppDataSourcesMysqlUrl());
				dataSource.setUsername(systemEnvironment.getAppDataSourcesMysqlUsername());
				dataSource.setPassword(systemEnvironment.getAppDataSourcesMysqlPassword());
			}

			return dataSource;
		}

		@Bean
		public PlatformTransactionManager transactionManager(final EntityManagerFactory entityManagerFactory,
				DataSource dataSource) {
			JpaTransactionManager transactionManager = new JpaTransactionManager();
			transactionManager.setEntityManagerFactory(entityManagerFactory);

			return transactionManager;
		}

		@Bean
		public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
			return new PersistenceExceptionTranslationPostProcessor();
		}
	}
}
