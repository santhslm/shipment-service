package com.comcast.orion.shipment.config;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

/**
 * The Class RequestTraceFilter.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RequestTraceFilter extends GenericFilterBean {

	/** The Constant LOGGER. */
	private static final Logger log = LoggerFactory.getLogger(RequestTraceFilter.class);

	/** The token manager. */
	// @Autowired
	// private TokenManager tokenManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HystrixRequestContext context = null;
		try {
			context = HystrixRequestContext.initializeContext();
			if (httpServletRequest.getServletPath().contains("shipmentService")) {
				log.info("EnteringIntoFilter");
				setRequestHeaderParams(httpServletRequest);
				chain.doFilter(request, response);
				log.info("ExitingFromFilter");
			} else {
				chain.doFilter(request, response);
			}
		} finally {
			if (context != null) {
				context.close();
			}
		}
	}

	/**
	 * Sets the request header params.
	 *
	 * @param request
	 *            the new request header params
	 */
	public void setRequestHeaderParams(HttpServletRequest httpServletRequest) {
		String correlationId = null;
		String mdcData = null;
		String clientId = getClient(httpServletRequest);

		correlationId = clientId + UUID.randomUUID().toString();
		String sourceTrackingID = httpServletRequest.getHeader("trackingId");
		if (null != sourceTrackingID) {
			mdcData = String.format("[%s][%s] ", "TrackingID ", sourceTrackingID);
		} else {
			mdcData = String.format("[%s][%s] ", "OrionTrackingID ", UUID.randomUUID().toString());
		}
		MDC.put("mdcData", mdcData);

		String serviceName = null;
		String serviceVersion = null;
		String operationName = null;
		log.info("RequestTraceFilter--- >httpServletRequest.getRequestURI() () :"+httpServletRequest.getRequestURI());
		String[] serviceDetails = httpServletRequest.getRequestURI().split("/");
		
		if (serviceDetails != null && serviceDetails.length >= 3) {
			//serviceName = serviceDetails[1];
			//serviceVersion = serviceDetails[2];
			//operationName = serviceDetails[3];
			serviceName = serviceDetails[1] != null ? serviceDetails[1] : "";
			serviceVersion = serviceDetails[2] != null ? serviceDetails[2] : "";
			operationName = serviceDetails[3] != null ? serviceDetails[3] : "";
			log.info("RequestTraceFilter--- >serviceName : "+serviceName+" serviceVersion : "+serviceVersion+" operationName : "+operationName);
		}

		MDC.put("mdcData", mdcData);
		MDC.put("trackingId", sourceTrackingID);
		MDC.put("parentId", sourceTrackingID);
		MDC.put("correlationId", correlationId);
		MDC.put("cspUserId", clientId);
		MDC.put("envName", System.getenv("SPRING_PROFILES_ACTIVE"));
		MDC.put("hostName", httpServletRequest.getServerName());
		MDC.put("serverInstance", System.getenv("CF_INSTANCE_INDEX"));
		MDC.put("serviceName", serviceName);
		MDC.put("serviceVersion", serviceVersion);
		//MDC.put("operationName", httpServletRequest.getRequestURI());
		MDC.put("operationName", operationName);
		MDC.put("businessParam", "NA");
	}

	/**
	 * Returns the clientId from bearer token.
	 *
	 * @param httpServletRequest
	 *            the http servlet request
	 * @return the client
	 */
	public String getClient(HttpServletRequest httpServletRequest) {
		StringBuilder sb = new StringBuilder();
		String clientId = null;
		String token = httpServletRequest.getHeader("Authorization");
		if (null != token && !StringUtils.isEmpty(token)) {
			sb.append(token).delete(0, 6);
			// clientId = tokenManager.decodeToken((sb.toString().trim()));
		}
		return clientId;
	}

}
