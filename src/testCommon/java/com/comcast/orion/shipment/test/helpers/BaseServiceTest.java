package com.comcast.orion.shipment.test.helpers;

import com.github.dreamhead.moco.HttpServer;
import com.github.dreamhead.moco.Moco;
import com.github.dreamhead.moco.RequestMatcher;
import com.github.dreamhead.moco.resource.ContentResource;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.BeforeClass;

import static com.github.dreamhead.moco.Moco.*;


public class BaseServiceTest {

    protected HttpServer mocoServer;

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 9001;
    }

    @Before
    public void setUp() throws Exception {
        mocoServer = httpServer(12306, log());
    }

    protected static RequestSpecification givenAuthorizationHeader() {
        return givenAuthorizationHeaderMergedWith();
    }

    protected static RequestSpecification givenAuthorizationHeaderMergedWith(String... headers) {
        return given().headers("Authorization", "Bearer " + OAuthTokenHelper.getToken(), (Object[]) headers).when();
    }

    protected RequestMatcher xmlUTF8(){
        return Moco.eq(header("Content-Type"), "application/xml;charset=UTF-8");
    }

    protected static RequestSpecification given() {
        return RestAssured.given().log().ifValidationFails();
    } 
    
    protected static Object[] getRestHeaders(){
        Object[] headers = new String[]{
        		"timestamp", "2013-10-16T13:08:32.831Z",
        		"sourceSystemId", "ss-id",
        		"sourceSystemUserId", "ss-user-id",
        		"sourceServerId", "sourceServerId-value",
        		"trackingId", "trackingId-value",
        };
        return headers;
    }

}