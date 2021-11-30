package com.comcast.orion.shipment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.comcast.orion.shipment.config.MDCHystrixConcurrencyStrategy;
import com.comcast.xsp.security.interceptors.PlatformAuthenticationClientInterceptor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.strategy.HystrixPlugins;

import brave.sampler.Sampler;

@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
@RefreshScope
@EnableFeignClients
@ImportResource("classpath:integration.xml")
public class ShipmentApplication {

	@Value("${service.downstream.readTimeout}")
	private int readTimeout;

	@Value("${service.downstream.connectionTimeout}")
	private int connectionTimeout;

	@Autowired
	private PlatformAuthenticationClientInterceptor xspInterceptor;

	public static void main(String[] args) {
		HystrixPlugins.getInstance().registerConcurrencyStrategy(new MDCHystrixConcurrencyStrategy());
		SpringApplication.run(ShipmentApplication.class, args);

	}

	/**
	 * Default sampler.
	 *
	 * @return the always sampler
	 */

	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}

	@Bean
	public FilterRegistrationBean loggingOutFilter() {
		com.comcast.orion.logging.interceptor.RestLoggingOutFilter filter = new com.comcast.orion.logging.interceptor.RestLoggingOutFilter();
		filter.setSleuthEnabled(Boolean.valueOf(true));
		FilterRegistrationBean frb = new FilterRegistrationBean();
		frb.setFilter(filter);
		frb.setUrlPatterns(Arrays.asList("/shipmentService/*"));
		//frb.setUrlPatterns(Arrays.asList("/shipment/*"));
		return frb;
	}

	/**
	 * New intercepting template.
	 *
	 * @param restTemplateBuilder
	 *            the rest template builder
	 * @return the rest template
	 */

	@Bean(name = "compsRestTemplate")
	public RestTemplate newInterceptingTemplateComps(RestTemplateBuilder restTemplateBuilder) {
		// Buffer the stream
		RestTemplate restTemplate = restTemplateBuilder.build();

		// Don't use SimpleClientHttpConnectionFactory as it will throw
		// IOException on ClientHttpResponse.getBody.
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setReadTimeout(readTimeout);
		requestFactory.setConnectTimeout(connectionTimeout);
		restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(requestFactory));
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		//interceptors.add(new RestTemplateLoggingInterceptor());
		interceptors.add(new com.comcast.orion.logging.interceptor.RestTemplateLoggingInterceptor());
		// interceptors.add(xspInterceptor);
		restTemplate.setInterceptors(interceptors);
		return restTemplate;
	}

	@Bean(name = "vmsRestTemplate")
	public RestTemplate newInterceptingTemplateVms(RestTemplateBuilder restTemplateBuilder) {
		// Buffer the stream
		RestTemplate restTemplate = restTemplateBuilder.build();

		// Don't use SimpleClientHttpConnectionFactory as it will throw
		// IOException on ClientHttpResponse.getBody.
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setReadTimeout(readTimeout);
		requestFactory.setConnectTimeout(connectionTimeout);
		restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(requestFactory));
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		//interceptors.add(new RestTemplateLoggingInterceptor());
		interceptors.add(new com.comcast.orion.logging.interceptor.RestTemplateLoggingInterceptor());
		interceptors.add(xspInterceptor);
		restTemplate.setInterceptors(interceptors);
		return restTemplate;
	}

	@Bean(name = "shipmentDataRestTemplate")
	public RestTemplate newInterceptingTemplateOnpShipmentData(RestTemplateBuilder restTemplateBuilder) {
		// Buffer the stream
		RestTemplate restTemplate = restTemplateBuilder.build();

		// Don't use SimpleClientHttpConnectionFactory as it will throw
		// IOException on ClientHttpResponse.getBody.
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setReadTimeout(readTimeout);
		requestFactory.setConnectTimeout(connectionTimeout);
		restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(requestFactory));
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		//interceptors.add(new RestTemplateLoggingInterceptor());
		interceptors.add(new com.comcast.orion.logging.interceptor.RestTemplateLoggingInterceptor());
		System.out.println("shipment token>>>>>>>" + xspInterceptor);
		interceptors.add(xspInterceptor);
		restTemplate.setInterceptors(interceptors);
		return restTemplate;
	}

	@Bean(name = "onpRestTemplate")
	public RestTemplate newInterceptingTemplateOnp(RestTemplateBuilder restTemplateBuilder) {
		// Buffer the stream
		RestTemplate restTemplate = restTemplateBuilder.build();

		// Don't use SimpleClientHttpConnectionFactory as it will throw
		// IOException on ClientHttpResponse.getBody.
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setReadTimeout(readTimeout);
		requestFactory.setConnectTimeout(connectionTimeout);
		restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(requestFactory));
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		//interceptors.add(new RestTemplateLoggingInterceptor());
		interceptors.add(new com.comcast.orion.logging.interceptor.RestTemplateLoggingInterceptor());
		interceptors.add(xspInterceptor);
		restTemplate.setInterceptors(interceptors);
		return restTemplate;
	}

	@Bean(name = "downstreamRestTemplate")
	public RestTemplate newInterceptingTemplateContracts(RestTemplateBuilder restTemplateBuilder) {
		// Buffer the stream
		RestTemplate restTemplate = restTemplateBuilder.build();

		// Don't use SimpleClientHttpConnectionFactory as it will throw
		// IOException on ClientHttpResponse.getBody.
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		// requestFactory.setReadTimeout(readTimeout);
		// requestFactory.setConnectTimeout(connectionTimeout);
		restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(requestFactory));
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		//interceptors.add(new RestTemplateLoggingInterceptor());
		interceptors.add(new com.comcast.orion.logging.interceptor.RestTemplateLoggingInterceptor());
		interceptors.add(xspInterceptor);
		restTemplate.setInterceptors(interceptors);
		return restTemplate;
	}

	/**
	 * Object mapper.
	 *
	 * @return the object mapper
	 */
	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return new ObjectMapper();
	}
}