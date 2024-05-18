package edu.remad.tutoring2.json.customserializer;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import edu.remad.tutoring2.appconstants.TimeAppConstants;
import edu.remad.tutoring2.models.ReminderEntity;

@JsonComponent
public class ReminderEntitySerializer extends StdSerializer<ReminderEntity> {

	/**
	 * serial version UID
	 */
	private static final long serialVersionUID = 1L;

	public ReminderEntitySerializer() {
		super(ReminderEntity.class);
	}

	public ReminderEntitySerializer(Class<ReminderEntity> t) {
		super(t);
	}

	@Override
	public void serialize(ReminderEntity reminder, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		gen.writeNumberField("reminderNo", reminder.getReminderNo());
		gen.writeStringField("reminderDate", reminder.getReminderDate().format(TimeAppConstants.DATE_FORMATTER));
		gen.writeStringField("reminderCreationDate", reminder.getReminderCreationDate().format(TimeAppConstants.DATE_FORMATTER));
		gen.writeEndObject();
	}
}
