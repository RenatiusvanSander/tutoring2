package edu.remad.tutoring2.json.customserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import edu.remad.tutoring2.models.ServiceContractEntity;

public class ServiceContractEntitySerializer extends StdSerializer<ServiceContractEntity> {

	protected ServiceContractEntitySerializer(StdSerializer<?> src) {
		super(src);
	}

	private static final long serialVersionUID = 1L;

	@Override
	public void serialize(ServiceContractEntity value, JsonGenerator gen, SerializerProvider provider)
			throws IOException {
		
		gen.writeStartObject();
		gen.writeStringField("serviceContractNo", Long.toString(value.getServiceContractNo()));
		gen.writeStringField("serviceContractName", value.getServiceContractName());
		gen.writeStringField("serviceContractDescription", value.getServiceContractDescription());
		gen.writeStringField("serviceContractCreationDate", value.getServiceContractCreationDate().toString());
		gen.writeEndObject();
	}
}
