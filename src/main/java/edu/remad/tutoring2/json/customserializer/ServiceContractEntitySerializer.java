package edu.remad.tutoring2.json.customserializer;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import edu.remad.tutoring2.appconstants.TimeAppConstants;
import edu.remad.tutoring2.models.ServiceContractEntity;

@JsonComponent
public class ServiceContractEntitySerializer extends AbstractGenericTutoring2Serializer<ServiceContractEntity> {

	/**
	 * serial version UID
	 */
	private static final long serialVersionUID = 1L;

	public ServiceContractEntitySerializer(Class<ServiceContractEntity> t) {
		super(t);
	}
	
	public ServiceContractEntitySerializer() {
		super(ServiceContractEntity.class);
	}
	
	public ServiceContractEntitySerializer(Class<ServiceContractEntity> vc, ObjectMapper objectMapper) {
		super(vc, objectMapper);
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
		gen.flush();
	}

}
