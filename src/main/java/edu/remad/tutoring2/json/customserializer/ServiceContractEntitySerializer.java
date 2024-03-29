package edu.remad.tutoring2.json.customserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import edu.remad.tutoring2.appconstants.TimeAppConstants;
import edu.remad.tutoring2.models.ServiceContractEntity;

public class ServiceContractEntitySerializer extends StdSerializer<ServiceContractEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServiceContractEntitySerializer(Class<ServiceContractEntity> t) {
		super(t);
	}
	
	public ServiceContractEntitySerializer() {
		this(null);
	}

	@Override
	public void serialize(ServiceContractEntity serviceContractEntity, JsonGenerator gen, SerializerProvider provider)
			throws IOException {
		
		gen.writeStartObject();
		gen.writeNumberField("serviceContractNo", serviceContractEntity.getServiceContractNo());
		gen.writeStringField("serviceContractName", serviceContractEntity.getServiceContractName());
		gen.writeStringField("serviceContractDescription", serviceContractEntity.getServiceContractDescription());
		gen.writeStringField("serviceContractCreationDate", serviceContractEntity.getServiceContractCreationDate().format(TimeAppConstants.LOCAL_DATE_TIME_FORMATTER));
		gen.writeEndObject();		
	}

}
