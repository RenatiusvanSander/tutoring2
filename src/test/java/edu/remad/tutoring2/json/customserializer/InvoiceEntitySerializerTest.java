package edu.remad.tutoring2.json.customserializer;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import edu.remad.tutoring2.json.AbstractJsonJUnit5Test;
import edu.remad.tutoring2.models.InvoiceEntity;

public class InvoiceEntitySerializerTest extends AbstractJsonJUnit5Test {

	private InvoiceEntity invoice;

	@BeforeEach
	@Override
	public void setUp() throws JsonProcessingException {
		super.setUp();
		invoice = createInvoice();
	}

	@Test
	public void serializeTest() throws IOException {
		String expectedJson = "{}";
		
		InvoiceEntitySerializer serializer = new InvoiceEntitySerializer();
		serializer.serialize(invoice, createJsonGenerator(), OBJECTMAPPER.getSerializerProvider());
		
		System.out.println(jsonWriter.toString());
		Assertions.assertEquals(expectedJson, jsonWriter.toString());
	}
}
