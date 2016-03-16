package org.aspasibu.logitest.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.aspasibu.logitest.aspect.JmsSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@EnableAspectJAutoProxy
public class JmsConfig {

	private static final String JMS_BROCKER_URL = "tcp://localhost:61616";

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
}
