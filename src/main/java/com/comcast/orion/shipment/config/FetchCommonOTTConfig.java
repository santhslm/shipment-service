package com.comcast.orion.shipment.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "service.vms")
@RefreshScope
@Component
public class FetchCommonOTTConfig {

	public Map<String, String> getCommonOTT() {
		return commonOTT;
	}

	public void setCommonOTT(Map<String, String> commonOTT) {
		this.commonOTT = commonOTT;
	}

	private Map<String, String> commonOTT = new HashMap<>();

}
