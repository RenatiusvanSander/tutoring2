package edu.remad.tutoring2.json.customserializer;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonProcessingException;

import edu.remad.tutoring2.json.AbstractJsonJUnit5Test;
import edu.remad.tutoring2.models.InvoiceEntity;

@JsonComponent
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
		String expectedJson = "{\"invoiceNo\":125,\"invoiceServiceContract\":{\"serviceContractNo\":41,\"serviceContractName\":\"Elektrotechnik Nachhilfe\",\"serviceContractDescription\":\"Nachhilfe in den Grundlagen der Elektrotechnik\",\"serviceContractCreationDate\":\"2024-05-11 00:00\"},\"invoiceTutoringHours\":1.0,\"invoiceDate\":\"2024-05-11 00:00\",\"invoiceTutoringDate\":\"2024-05-11 00:00\",\"invoiceUser\":{\"id\":3,\"username\":\"mustermann\",\"email\":\"maxmustermann@nirgendwo.de\",\"password\":\"MusterCity\",\"enabled\":true,\"roles\":[{\"id\":1,\"name\":\"Admin\",\"users\":[{\"id\":1}]}],\"firstName\":\"Max\",\"lastName\":\"Mustermann\",\"gender\":\"male\",\"cellPhone\":\"+4953535343435747\",\"addresses\":\"[{\\\"id\\\":0,\\\"addressStreet\\\":\\\"Hohlenbarg\\\",\\\"addressHouseNo\\\":\\\"24\\\",\\\"addressZipCode\\\":{\\\"id\\\":1,\\\"zipCode\\\":\\\"76246\\\",\\\"zipCodeLocation\\\":\\\"Hohleburg\\\",\\\"zipCodeCreationDate\\\":\\\"2024-03-10\\\"}}]\",\"creationDate\":\"2022-03-14\"},\"price\":{\"id\":3,\"user\":{\"id\":3,\"username\":\"mustermann\",\"email\":\"maxmustermann@nirgendwo.de\",\"password\":\"MusterCity\",\"enabled\":true,\"roles\":[{\"id\":1,\"name\":\"Admin\",\"users\":[{\"id\":1}]}],\"firstName\":\"Max\",\"lastName\":\"Mustermann\",\"gender\":\"male\",\"cellPhone\":\"+4953535343435747\",\"addresses\":\"[{\\\"id\\\":0,\\\"addressStreet\\\":\\\"Hohlenbarg\\\",\\\"addressHouseNo\\\":\\\"24\\\",\\\"addressZipCode\\\":{\\\"id\\\":1,\\\"zipCode\\\":\\\"76246\\\",\\\"zipCodeLocation\\\":\\\"Hohleburg\\\",\\\"zipCodeCreationDate\\\":\\\"2024-03-10\\\"}}]\",\"creationDate\":\"2022-03-14\"},\"price\":12.44999980926513671875},\"invoiceCreationDate\":\"2024-05-11 00:00\"}";
		
		InvoiceEntitySerializer serializer = new InvoiceEntitySerializer();
		serializer.serialize(invoice, createJsonGenerator(), OBJECTMAPPER.getSerializerProvider());
		
		System.out.println(jsonWriter.toString());
		Assertions.assertEquals(expectedJson, jsonWriter.toString());
	}
}
