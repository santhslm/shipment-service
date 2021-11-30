/**
 * 
 */
package com.comcast.orion.shipment.springContract;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 512833
 *
 */
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

//@Configuration
//@EnableConfigurationProperties
@Component
// @ConfigurationProperties("contractStubs")
// @ConfigurationProperties("springContract")

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("contractstubs")
public class ContractsConfig {

	private int port;
	private String type;
	// private List<String> hosts = new ArrayList<>();
	private List<Tescase> tescases = new ArrayList<>();

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	public List<Tescase> getTescases() {
		return tescases;
	}

	public void setTescases(List<Tescase> tescases) {
		this.tescases = tescases;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port
	 *            the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/*
	 * public List<String> getHosts() { return hosts; }
	 * 
	 * public void setHosts(List<String> hosts) { this.hosts = hosts; }
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ConfigProp [port=" + port + ", type=" + type + ", tescases=" + tescases + ", getType()=" + getType()
				+ ", getTescases()=" + getTescases() + ", getPort()=" + getPort() + "]";
	}

}
