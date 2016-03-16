package org.aspasibu.logitest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.ReflectiveLoadTimeWeaver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaDialect;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = { "org.aspasibu.logitest.repository" })
@EnableTransactionManagement
public class DataConfig {

	@Bean
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("org.postgresql.Driver");
		ds.setUrl("jdbc:postgresql://127.0.0.1:5432/logi");
		ds.setUsername("postgres");
		ds.setPassword("masterkey");
		return ds;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();

		emf.setPersistenceXmlLocation("classpath:META-INF/persistence.xml");
		emf.setDataSource(dataSource());
		emf.setPersistenceUnitName("drivers");
		emf.setPackagesToScan("org.aspasibu.logitest.entity");

		AbstractJpaVendorAdapter vendor = new EclipseLinkJpaVendorAdapter();
		vendor.setShowSql(true);
		vendor.setGenerateDdl(true);
		vendor.setDatabasePlatform("org.eclipse.persistence.platform.database.PostgreSQLPlatform");

		emf.setJpaVendorAdapter(vendor);
		emf.setJpaDialect(new EclipseLinkJpaDialect());
		emf.setLoadTimeWeaver(new ReflectiveLoadTimeWeaver());

		return emf;
	}

	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

		return transactionManager;
	}
}
