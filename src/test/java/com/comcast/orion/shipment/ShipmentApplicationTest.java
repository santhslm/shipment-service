package com.comcast.orion.shipment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.comcast.orion.logging.interceptor.RestTemplateLoggingInterceptor;
import com.comcast.orion.shipment.config.MDCHystrixConcurrencyStrategy;
import com.comcast.xsp.security.interceptors.PlatformAuthenticationClientInterceptor;
import com.netflix.hystrix.strategy.HystrixPlugins;

@SpringBootTest
public class ShipmentApplicationTest {

	@Autowired
	private PlatformAuthenticationClientInterceptor xspInterceptor;

	public static void main(String[] args) {
		HystrixPlugins.getInstance().registerConcurrencyStrategy(new MDCHystrixConcurrencyStrategy());
		SpringApplication.run(ShipmentApplication.class, args);
	}

	/**
	 * New intercepting template.
	 *
	 * @param restTemplateBuilder
	 *            the rest template builder
	 * @return the rest template
	 */

	@Bean(name = "shipmentDataRestTemplateTest")
	public static RestTemplate pnlInterceptingTemplateshipmentdata(RestTemplateBuilder restTemplateBuilder) {
		// Buffer the stream
		RestTemplate restTemplate = restTemplateBuilder.build();

		// Don't use SimpleClientHttpConnectionFactory as it will throw
		// IOException on ClientHttpResponse.getBody.
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(requestFactory));
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new RestTemplateLoggingInterceptor());
		restTemplate.setInterceptors(interceptors);
		return restTemplate;
	}

	@Bean(name = "vmsRestTemplateTest")
	public static RestTemplate pnlInterceptingTemplate(RestTemplateBuilder restTemplateBuilder) {
		// Buffer the stream
		RestTemplate restTemplate = restTemplateBuilder.build();

		// Don't use SimpleClientHttpConnectionFactory as it will throw
		// IOException on ClientHttpResponse.getBody.
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(requestFactory));
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new RestTemplateLoggingInterceptor());
		restTemplate.setInterceptors(interceptors);
		return restTemplate;
	}

	@Bean(name = "onpRestTemplateTest")
	public RestTemplate onpInterceptingTemplate(RestTemplateBuilder restTemplateBuilder) {
		// Buffer the stream
		RestTemplate restTemplate = restTemplateBuilder.build();

		// Don't use SimpleClientHttpConnectionFactory as it will throw
		// IOException on ClientHttpResponse.getBody.
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(requestFactory));
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new RestTemplateLoggingInterceptor());
		interceptors.add(xspInterceptor);
		restTemplate.setInterceptors(interceptors);
		return restTemplate;
	}

}
