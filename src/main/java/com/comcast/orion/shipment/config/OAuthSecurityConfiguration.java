package com.comcast.orion.shipment.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.comcast.xsp.security.config","com.comcast.xsp.security","com.comcast.xsp.service.core"})
public class OAuthSecurityConfiguration {
}
