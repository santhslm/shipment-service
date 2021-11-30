package com.comcast.orion.shipment.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.cloud.config.java.ServiceScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

@Configuration
@Profile("cloud")
@ServiceScan
public class CloudConfiguration extends AbstractCloudConfig {

	//rivate String serviceInstanceId = "shipment-rabbitmq";
	private String serviceInstanceId = "OMW-RabbitMQ";
	

	private String exchange = "auditlog.exchange";

	private String routingKey = "shipment-service";

	private String queue = "shipment-service";

	private static final Logger log = LoggerFactory.getLogger(CloudConfiguration.class);

	@Bean(name = "cachingConnectionFactory")
	@Primary
	public ConnectionFactory rabbitConnectionFactory() {
		log.info("Creating the Cloud Connection Factory for Orion RabbitMQ Service --- serviceInstanceId : "
				+ serviceInstanceId);

		System.out.println("Creating the Cloud Connection Factory for Orion RabbitMQ Service --- serviceInstanceId : "
				+ serviceInstanceId);

		if (serviceInstanceId == null) {
			serviceInstanceId = "shipment-rabbitmq";
			log.info("Overriding the Default Service Instance for Orion RabbitMQ Service --- serviceInstanceId : "
					+ serviceInstanceId);
		}
		return connectionFactory().rabbitConnectionFactory();
	}

	@Bean(name = "rabbitTemplate")
	@Primary
	public RabbitTemplate rabbitTemplate() {
		/*
		 * final CachingConnectionFactory factory = new CachingConnectionFactory();
		 * factory.setHost(host); factory.setPort(Integer.parseInt(port));
		 * factory.setUsername(username); factory.setPassword(password);
		 */
		/* final RabbitTemplate rabbitTemplate = new RabbitTemplate(factory); */

		final RabbitTemplate rabbitTemplate = new RabbitTemplate(rabbitConnectionFactory());
		//rabbitTemplate.setExchange(exchange);
		//rabbitTemplate.setRoutingKey(routingKey + ".service");
		rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
		log.info("Bean --- rabbitTemplate - created");

		log.info(" rabbitConnectionFactory() " + rabbitConnectionFactory().getHost());
		log.info(" rabbitConnectionFactory() " + rabbitConnectionFactory().getPort());
		log.info(" rabbitConnectionFactory() " + rabbitConnectionFactory().getUsername());
		log.info(" rabbitConnectionFactory() " + rabbitConnectionFactory().getVirtualHost());
		return rabbitTemplate;
	}

	@Bean
	@Primary
	public RabbitAdmin admin() {
		RabbitAdmin rabbitAdmin = new RabbitAdmin(rabbitConnectionFactory());
		System.out.println(" Inside RabbitAdmin : " + rabbitAdmin);
		
		System.out.println(" Before Declar Queue ");
		
		rabbitAdmin.declareQueue(queue());
		
		System.out.println(" Before Declar Exchange ");
		
		rabbitAdmin.declareExchange(exchange());
		Binding binding = BindingBuilder.bind(queue()).to(exchange()).with(routingKey + ".service");
		
		System.out.println(" Declaring Binding ");
		
		rabbitAdmin.declareBinding(binding);
		return rabbitAdmin;
	}

	@Bean
	DirectExchange exchange() {
		log.info("exchange --- rabbitTemplate - " + exchange);
		System.out.println("exchange --- rabbitTemplate - " + exchange);
		return new DirectExchange(exchange);
	}

	@Bean
	public Queue queue() {
		log.info("queue --- rabbitTemplate - " + "auditlog." + queue+ ".queue");
		System.out.println("queue --- rabbitTemplate - " + "auditlog." + queue+ ".queue");
		return new Queue("auditlog." + queue+ ".queue");
	}

	@Bean
	Binding binding(Queue queue, DirectExchange exchange) {
		log.info("routingKey --- rabbitTemplate - " + routingKey + ".service");
		System.out.println("routingKey --- rabbitTemplate - " + routingKey + ".service");
		return BindingBuilder.bind(queue()).to(exchange()).with(routingKey + ".service");
	}

	@Bean
	public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
		return new MappingJackson2MessageConverter();
	}

	@Bean
	public DefaultMessageHandlerMethodFactory myRabbitHandlerMethodFactory() {
		DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
		factory.setMessageConverter(consumerJackson2MessageConverter());
		return factory;
	}

	@Bean
	public SimpleRabbitListenerContainerFactory myRabbitListenerContainerFactory() {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(rabbitConnectionFactory());
		factory.setMaxConcurrentConsumers(5);
		factory.setConcurrentConsumers(1);
		factory.setPrefetchCount(1);
		return factory;
	}

	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}