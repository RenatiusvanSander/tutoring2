package edu.remad.tutoring2.security.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import edu.remad.tutoring2.appconstants.AppStageConstants;
import edu.remad.tutoring2.appconstants.JavaAppConstants;
import edu.remad.tutoring2.appconstants.PackagesAppConstants;
import edu.remad.tutoring2.systemenvironment.SystemEnvironment;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@ComponentScan({ PackagesAppConstants.EDU_REMAD_TUTORING2 })
@EnableJpaRepositories(basePackages = PackagesAppConstants.EDU_REMAD_TUTORING2_REPOSITORIES)
public class JPASecurityConfig {

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(SystemEnvironment systemEnvironment) {
		final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource(systemEnvironment));
		entityManagerFactoryBean.setPackagesToScan(new String[] { PackagesAppConstants.EDU_REMAD_TUTORING2_MODELS });

		final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
		entityManagerFactoryBean.setJpaProperties(additionalProperties());

		return entityManagerFactoryBean;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
		final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setPackagesToScan(new String[] { PackagesAppConstants.EDU_REMAD_TUTORING2_MODELS });
		sessionFactory.setHibernateProperties(additionalProperties());

		return sessionFactory;
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
	public PlatformTransactionManager transactionManager(final LocalSessionFactoryBean sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory.getObject());

		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
}
