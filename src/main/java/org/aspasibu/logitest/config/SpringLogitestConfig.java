package org.aspasibu.logitest.config;

import javax.persistence.EntityManagerFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.aspasibu.logitest.aspect.JmsSender;
import org.aspasibu.logitest.service.DriverService;
import org.aspasibu.logitest.service.DutyService;
import org.aspasibu.logitest.service.HosService;
import org.aspasibu.logitest.service.impl.DriverServiceImpl;
import org.aspasibu.logitest.service.impl.DutyServiceImpl;
import org.aspasibu.logitest.service.impl.HosServiceImpl;
import org.aspasibu.logitest.webservices.DriverWebService;
import org.aspasibu.logitest.webservices.DutyWebService;
import org.aspasibu.logitest.webservices.HosWebService;
import org.aspasibu.logitest.webservices.impl.DriverWebServiceImpl;
import org.aspasibu.logitest.webservices.impl.DutyWebServiceImpl;
import org.aspasibu.logitest.webservices.impl.HosWebServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.ReflectiveLoadTimeWeaver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaDialect;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author aspasibu
 * 
 * Spring configuration class
 *
 */
@Configuration
@EnableAspectJAutoProxy
@EnableJpaRepositories(basePackages = { "org.aspasibu.logitest.repository" })
@ImportResource("WEB-INF/beans.xml")
@EnableTransactionManagement
public class SpringLogitestConfig {

	private static final String JMS_BROCKER_URL = "tcp://localhost:61616";

	/**
	 * Services (and Web services)
	 */

	@Bean
	public DriverService driverService() {
		return new DriverServiceImpl();
	}

	@Bean
	public DriverWebService driverBean() {
		return new DriverWebServiceImpl();
	}

	@Bean
	public DutyService dutyService() {
		return new DutyServiceImpl();
	}

	@Bean
	public DutyWebService dutyBean() {
		return new DutyWebServiceImpl();
	}

	@Bean
	public HosService hosService() {
		return new HosServiceImpl();
	}

	@Bean
	public HosWebService hosBean() {
		return new HosWebServiceImpl();
	}

	/**
	 * JMS
	 */
	@Bean
	public JmsSender jmsSender() {
		return new JmsSender();
	}

	@Bean
	public ActiveMQConnectionFactory amqConnectionFactory() {
		return new ActiveMQConnectionFactory(JMS_BROCKER_URL);
	}

	@Bean
	public CachingConnectionFactory connectionFactory() {
		return new CachingConnectionFactory(amqConnectionFactory());
	}

	@Bean
	public ActiveMQQueue defaultDestination() {
		return new ActiveMQQueue("Send2Recv");
	}

	@Bean
	public JmsTemplate jmsTemplate() {
		JmsTemplate jmsTemplate = new JmsTemplate();

		jmsTemplate.setConnectionFactory(connectionFactory());
		jmsTemplate.setDefaultDestination(defaultDestination());

		return jmsTemplate;
	}
	
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
}
