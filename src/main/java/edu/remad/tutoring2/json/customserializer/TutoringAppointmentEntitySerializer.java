package edu.remad.tutoring2.json.customserializer;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import edu.remad.tutoring2.appconstants.TimeAppConstants;
import edu.remad.tutoring2.models.TutoringAppointmentEntity;

@JsonComponent
public class TutoringAppointmentEntitySerializer extends StdSerializer<TutoringAppointmentEntity> {

	/**
	 * serial version UID
	 */
	private static final long serialVersionUID = 1L;

	public TutoringAppointmentEntitySerializer() {
		this(null);
	}
	
	public TutoringAppointmentEntitySerializer(Class<TutoringAppointmentEntity> t) {
		super(t);
	}

	@Override
	public void serialize(TutoringAppointmentEntity value, JsonGenerator gen, SerializerProvider provider)
			throws IOException {
		gen.writeStartObject();
		gen.writeNumberField("tutoringAppointmentNo", value.getTutoringAppointmentNo());
		gen.writeObjectField("tutoringAppointmentUser", value.getTutoringAppointmentUser());
		gen.writeStringField("tutoringAppointmentDate", value.getTutoringAppointmentDate().format(TimeAppConstants.LOCAL_DATE_TIME_FORMATTER));
		gen.writeStringField("tutoringAppointmentStartDateTime", value.getTutoringAppointmentStartDateTime().format(TimeAppConstants.LOCAL_DATE_TIME_FORMATTER));
		gen.writeStringField("tutoringAppointmentEndDateTime", value.getTutoringAppointmentEndDateTime().format(TimeAppConstants.LOCAL_DATE_TIME_FORMATTER));
		gen.writeObjectFieldStart("serviceContractEntity");
		gen.writeNumberField("serviceContractNo", value.getServiceContractEntity().getServiceContractNo());
		gen.writeStringField("serviceContractName", value.getServiceContractEntity().getServiceContractName());
		gen.writeStringField("serviceContractDescription", value.getServiceContractEntity().getServiceContractDescription());
		gen.writeStringField("serviceContractCreationDate", value.getServiceContractEntity().getServiceContractCreationDate().format(TimeAppConstants.LOCAL_DATE_TIME_FORMATTER));
		gen.writeEndObject();
		gen.writeStringField("tutoringAppointmentCreationDate", value.getTutoringAppointmentCreationDate().format(TimeAppConstants.LOCAL_DATE_TIME_FORMATTER));
		gen.writeEndObject();
	}
}
