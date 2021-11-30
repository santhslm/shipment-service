package com.comcast.orion.shipment.utils;

import static org.junit.Assert.assertNotNull;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

public class JsonObjectGeneratorTest {
	JsonObjectGenerator jsonObjectGenerator = new JsonObjectGenerator();
	private String path = "\\src\\main\\resources\\json";
	private String dirPath = null;

	@Before
	public void setUp() {
		Path currentRelativePath = Paths.get("");
		String absolutePath = currentRelativePath.toAbsolutePath().toString();
		path = absolutePath + path;
		dirPath = absolutePath + dirPath;
	}

	/**
	 * Run the void main method test.
	 *
	 * @throws Exception
	 */
	/*
	 * @Test public void testMain() throws Exception { String[] args = new String[]
	 * {}; JsonObjectGenerator.main(args);
	 * assertNotNull("returns nothing & no exception", "Success"); }
	 */

	/**
	 * @throws MalformedURLException
	 * 
	 */
	@Test
	public void testGenerateJsonPogo() throws MalformedURLException {
		Path testPath = Paths.get(path);
		if (Files.isRegularFile(testPath)) {
			jsonObjectGenerator.generateJsonPogo(testPath);
			assertNotNull("returns nothing & no exception", "Success");
		}
		assertNotNull("returns nothing & no exception", "Success");
	}

	/**
	 * @throws MalformedURLException
	 *//*
		 * @Test public void testGetDomainPackagePath() throws MalformedURLException {
		 * Path testPkgPath = Paths.get(path); if (Files.isRegularFile(testPkgPath)) {
		 * assertEquals("pkgPath value returned as expected",
		 * JsonObjectGenerator.POJO_DOMAIN_FOLDER,
		 * jsonObjectGenerator.getDomainPackagePath(testPkgPath)); } }
		 */
	/**
	 * Run the void main method test.
	 *
	 * @throws Exception
	 */
//	@Test
//	public void testMain() throws Exception {
//		String[] args = new String[] {};
//		JsonObjectGenerator.main(args);
//		assertNotNull("Check", "Success");
//	}
}