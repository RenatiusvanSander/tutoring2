package edu.remad.tutoring2.json.customserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import edu.remad.tutoring2.appconstants.TimeAppConstants;
import edu.remad.tutoring2.models.TutoringAppointmentEntity;

public class TutoringAppointmentEntitySerializer extends StdSerializer<TutoringAppointmentEntity> {

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
//		this.tutoringAppointmentUser = tutoringAppointmentUser;
		gen.writeStringField("tutoringAppointmentDate", value.getTutoringAppointmentDate().format(TimeAppConstants.LOCAL_DATE_TIME_FORMATTER));
		gen.writeStringField("tutoringAppointmentStartDateTime", value.getTutoringAppointmentStartDateTime().format(TimeAppConstants.LOCAL_DATE_TIME_FORMATTER));
		gen.writeStringField("tutoringAppointmentEndDateTime", value.getTutoringAppointmentEndDateTime().format(TimeAppConstants.LOCAL_DATE_TIME_FORMATTER));
		gen.writeStringField("tutoringAppointmentCreationDate", value.getTutoringAppointmentCreationDate().format(TimeAppConstants.LOCAL_DATE_TIME_FORMATTER));
		gen.writeEndObject();
	}
}
