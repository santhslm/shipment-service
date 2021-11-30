package com.comcast.orion.shipment.config;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.ServletResponseWrapper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponseWrapper;

//import org.apache.cxf.jaxrs.impl.tl.ThreadLocalHttpServletRequest;
//import org.apache.cxf.jaxrs.impl.tl.ThreadLocalHttpServletResponse;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.mock.web.MockFilterChain;

import com.comcast.xsp.security.helpers.TokenManager;
import com.comcast.xsp.security.helpers.XspOauthToken;

public class RequestTraceFilterTest {

	@Mock
	HttpServletRequest httpServletRequest;

	/**
	 * Run the void doFilter(ServletRequest,ServletResponse,FilterChain) method
	 * test.
	 *
	 * @throws Exception
	 */
	@Mock
	private TokenManager tokenManager;

	@Mock
	XspOauthToken xspToken;

	@Mock
	OAuthSecurityConfiguration oAuthSecurityConfiguration;

	StringBuffer url;

	@Test(expected = Exception.class)
	public void testDoFilter() throws Exception {
		RequestTraceFilter filter = new RequestTraceFilter();
		//ServletRequest request = new HttpServletRequestWrapper(new ThreadLocalHttpServletRequest());
		//ServletResponse response = new ServletResponseWrapper(new HttpServletResponseWrapper(new ThreadLocalHttpServletResponse()));
		FilterChain chain = new MockFilterChain();
		//filter.doFilter(request, response, chain);
		//assertNotNull("check", request);
	}

	/*@Test(expected = Exception.class)
	public void testDoFilter1() throws Exception {
		RequestTraceFilter filter = new RequestTraceFilter();
		OAuthSecurityConfiguration oAuthSecurityConfiguration = new OAuthSecurityConfiguration();
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		when(httpServletRequest.getServletPath()).thenReturn("shipmentdata");
		ServletResponse response = new ServletResponseWrapper(
				new HttpServletResponseWrapper(new ThreadLocalHttpServletResponse()));
		FilterChain chain = new MockFilterChain();
		filter.doFilter(httpServletRequest, response, chain);
		//assertNotNull("check", httpServletRequest);
	}*/

	@Test
	public void testSetRequestHeaderParams2() throws Exception {
		RequestTraceFilter filter = new RequestTraceFilter();
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		when(httpServletRequest.getHeader("Authorization")).thenReturn(null);
		when(httpServletRequest.getRequestURI()).thenReturn(
				"shipmentdata-qa.u1.app.cloud.comcast.net/shipmentdata/v1/outOfFootprintProvider?rateCenter=BLOOMFIELD&state=FL");
		url = new StringBuffer();
		url.append(
				"shipmentdata-qa.u1.app.cloud.comcast.net/shipmentdata/v1/outOfFootprintProvider?rateCenter=BLOOMFIELD&state=FL");
		when(httpServletRequest.getRequestURL()).thenReturn(url);
		filter.setRequestHeaderParams(httpServletRequest);
		assertNotNull("check", httpServletRequest);
	}

	@Test
	public void testSetRequestHeaderParams() throws Exception {
		RequestTraceFilter filter = new RequestTraceFilter();
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		when(httpServletRequest.getHeader("Authorization")).thenReturn(null);
		// when(httpServletRequest.getHeader("Authorization")).thenReturn("Bearer
		// eyJhbGciOiJSUzI1NiIsImtpZCI6IkpXVFNpZ25pbmdDZXJ0LTIwMTUifQ.eyJzY29wZSI6WyJvcmlvbjpvcmlvbi1kZXYiXSwiY2xpZW50X2lkIjoib3Jpb25fZGV2IiwiaXNzIjoiaHR0cHM6Ly93ZWJzZWMtaW50LmNhYmxlLmNvbWNhc3QuY29tIiwiZXhwIjoxNTY5ODY0NDk0fQ.W6kGDFNWTpzIUvMMwJvvsJ1t61SskwG1EVxMCuKy2qKJKULh7jSCFcPVdCyUpHfgOWSNyM9mKFHM4Kuj4jbp4B_GkRUGNS0I4O4Y4z-wapQKCmFtRDHf_-Rasvr465cidjm2xWVfRk-DGuDfV2cmNdcw_S5sQhRPE7apw98yNquBbLFwbzF4RxeUg-v9fEQU6XXDx2NtSf00GqIM58fAyRAfoX0es0YyVjMko_3mUgRUVHK__tFiKAHS7_STlgRNhHrmy7Y_mvWTOQoALiRZbtOw0SOtIjH_33rBWukksAnmvtqJMsprnQ5o2svfhCAg5vNwzZyxEXzYNfetNEC_nw");
		when(httpServletRequest.getRequestURI()).thenReturn("shipmentdata");
		filter.setRequestHeaderParams(httpServletRequest);
		assertNotNull("check", httpServletRequest);
	}

	@Test(expected = Exception.class)
	public void testSetRequestHeaderParams1() throws Exception {
		RequestTraceFilter filter = new RequestTraceFilter();
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		when(httpServletRequest.getHeader("Authorization")).thenReturn(null);
		filter.setRequestHeaderParams(httpServletRequest);
		assertNotNull("check", httpServletRequest);
	}

	/*@Test(expected = Exception.class)
	public void testgetClient1() throws Exception {
		RequestTraceFilter filter = new RequestTraceFilter();
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		when(httpServletRequest.getHeader("Authorization")).thenReturn(
				"Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkpXVFNpZ25pbmdDZXJ0LTIwMTUifQ.eyJzY29wZSI6WyJvcmlvbjpvcmlvbi1kZXYiXSwiY2xpZW50X2lkIjoib3Jpb25fZGV2IiwiaXNzIjoiaHR0cHM6Ly93ZWJzZWMtaW50LmNhYmxlLmNvbWNhc3QuY29tIiwiZXhwIjoxNTY5ODY0NDk0fQ.W6kGDFNWTpzIUvMMwJvvsJ1t61SskwG1EVxMCuKy2qKJKULh7jSCFcPVdCyUpHfgOWSNyM9mKFHM4Kuj4jbp4B_GkRUGNS0I4O4Y4z-wapQKCmFtRDHf_-Rasvr465cidjm2xWVfRk-DGuDfV2cmNdcw_S5sQhRPE7apw98yNquBbLFwbzF4RxeUg-v9fEQU6XXDx2NtSf00GqIM58fAyRAfoX0es0YyVjMko_3mUgRUVHK__tFiKAHS7_STlgRNhHrmy7Y_mvWTOQoALiRZbtOw0SOtIjH_33rBWukksAnmvtqJMsprnQ5o2svfhCAg5vNwzZyxEXzYNfetNEC_nw");
		// when(xspToken.getClientId()).thenReturn("1725924");
		// when(tokenManager.decodeToken("eyJhbGciOiJSUzI1NiIsImtpZCI6IkpXVFNpZ25pbmdDZXJ0LTIwMTUifQ.eyJzY29wZSI6WyJvcmlvbjpvcmlvbi1kZXYiXSwiY2xpZW50X2lkIjoib3Jpb25fZGV2IiwiaXNzIjoiaHR0cHM6Ly93ZWJzZWMtaW50LmNhYmxlLmNvbWNhc3QuY29tIiwiZXhwIjoxNTY5ODY0NDk0fQ.W6kGDFNWTpzIUvMMwJvvsJ1t61SskwG1EVxMCuKy2qKJKULh7jSCFcPVdCyUpHfgOWSNyM9mKFHM4Kuj4jbp4B_GkRUGNS0I4O4Y4z-wapQKCmFtRDHf_-Rasvr465cidjm2xWVfRk-DGuDfV2cmNdcw_S5sQhRPE7apw98yNquBbLFwbzF4RxeUg-v9fEQU6XXDx2NtSf00GqIM58fAyRAfoX0es0YyVjMko_3mUgRUVHK__tFiKAHS7_STlgRNhHrmy7Y_mvWTOQoALiRZbtOw0SOtIjH_33rBWukksAnmvtqJMsprnQ5o2svfhCAg5vNwzZyxEXzYNfetNEC_nw")).thenReturn(xspToken);
		filter.getClient(httpServletRequest);
		//assertNotNull("check", httpServletRequest);
	}*/

}