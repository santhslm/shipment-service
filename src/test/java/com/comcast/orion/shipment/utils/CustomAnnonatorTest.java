package com.comcast.orion.shipment.utils;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.fasterxml.jackson.databind.JsonNode;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMethod;

@RunWith(MockitoJUnitRunner.class)


public class CustomAnnonatorTest{
	
	@InjectMocks
	CustomAnnonator annotator;
	
	@Mock
	JMethod jMethod;
	
	
	@Test(expected = Exception.class)
	public void testPropertyInclusion() {
		
		JDefinedClass clazz = mock(JDefinedClass.class);
		JsonNode schema = mock(JsonNode.class);
		annotator.propertyInclusion(clazz, schema);
		assertNotNull("check",schema);
	}
	
	@Test(expected = Exception.class)
	public void testPropertyOrder() {
		
		JDefinedClass clazz = mock(JDefinedClass.class);
		JsonNode schema = mock(JsonNode.class);
		annotator.propertyOrder(clazz, schema);
		assertNotNull("check",schema);
	}
	
	@Test
	public void testPropertyField() {
		
		JDefinedClass clazz = mock(JDefinedClass.class);
		JsonNode schema = mock(JsonNode.class);
		JFieldVar field = mock(JFieldVar.class);
		annotator.propertyField(field, clazz, "type", schema);
		assertNotNull("check",schema);
	}
	
	@Test
	public void testPropertyField1() {
		
		JDefinedClass clazz = mock(JDefinedClass.class);
		JsonNode schema = mock(JsonNode.class);
		JFieldVar field = mock(JFieldVar.class);
		when(schema.get("required")).thenReturn(schema);
		when(schema.get("type")).thenReturn(schema);
		when(schema.get("position")).thenReturn(schema);
		when(schema.get("description")).thenReturn(schema);
		when(schema.get("example")).thenReturn(schema);
		annotator.propertyField(field, clazz, "type", schema);
		assertNotNull("check",schema);
	}
	
	
	@Test
	public void testAdditionalPropertiesField() {
		
		JDefinedClass clazz = mock(JDefinedClass.class);
		JsonNode schema = mock(JsonNode.class);
		JFieldVar field = mock(JFieldVar.class);
		annotator.additionalPropertiesField(field,clazz,"364564");
		assertNotNull("check",schema);
	}
	
	@Test
	public void testdateField() {
		String response = "Success";
		annotator.dateField(null, null);
		assertNotNull(response);
	}
	
	@Test
	public void testdateTimeField() {
		String response = "Success";
		annotator.dateTimeField(null, null);
		assertNotNull(response);
	}
	
	@Test
	public void testAdditionalPropertiesFields() {
		
		JDefinedClass clazz = mock(JDefinedClass.class);
		JsonNode schema = mock(JsonNode.class);
		JFieldVar field = mock(JFieldVar.class);
		annotator.additionalPropertiesField(field,clazz,"364564");
		assertNotNull("check",schema);
	}
	
	@Test
	public void testenumConstant() {
		String response = "Success";
		annotator.enumConstant(null, null);
		assertNotNull(response);
	}
	
	@Test
	public void testisAdditionalPropertiesSupported() {
		String response = "Success";
		annotator.isAdditionalPropertiesSupported();
		assertNotNull(response);
	}
	
	@Test
	public void testenumValueMethod() {
		String response = "Success";
		annotator.enumValueMethod(jMethod);
		assertNotNull(response);
	}
	
	@Test
	public void testenumCreatorMethod() {
		String response = "Success";
		annotator.enumCreatorMethod(jMethod);
		assertNotNull(response);
	}
	
	@Test
	public void testanySetter() {		
		String response = "Success";
		annotator.anySetter(jMethod);
		assertNotNull(response);
	}
	
	@Test
	public void testanyGetter() {		
		String response = "Success";
		annotator.anyGetter(jMethod);
		assertNotNull(response);
	}
	
	@Test(expected = NullPointerException.class)
	public void testpropertySetter() {		
		annotator.propertySetter(jMethod, null);		
	}
	
	@Test(expected = NullPointerException.class)
	public void testpropertyGetter() {		
		annotator.propertyGetter(null, null);		
	}
	@Test(expected = NullPointerException.class)
	public void testpropertyInclusion() {		
		annotator.propertyInclusion(null, null);		
	}
	
	@Test(expected = NullPointerException.class)
	public void testpropertyOrder() {		
		annotator.propertyOrder(null, null);		
	}
	
}