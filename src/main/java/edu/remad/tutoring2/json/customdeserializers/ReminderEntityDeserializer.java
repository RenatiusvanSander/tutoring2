package edu.remad.tutoring2.json.customdeserializers;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.TextNode;

import edu.remad.tutoring2.json.JsonBaseDeserializerHelper;
import edu.remad.tutoring2.models.ReminderEntity;
import edu.remad.tutoring2.models.TutoringAppointmentEntity;

public class ReminderEntityDeserializer extends StdDeserializer<ReminderEntity> {

	@Autowired
	private ObjectMapper objectMapper;

	/**
	 * serial version UID
	 */
	private static final long serialVersionUID = 1L;

	public ReminderEntityDeserializer() {
		this(null);
	}

	public ReminderEntityDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public ReminderEntity deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
		TreeNode node = p.getCodec().readTree(p);
		
		Long reminderId = ((IntNode)node.get("reminderNo")).asLong();
		JsonParser appointment = node.get("reminderTutoringAppointment").traverse();
		TutoringAppointmentEntity reminderTutoringAppointment = objectMapper.readValue(appointment, TutoringAppointmentEntity.class);
		LocalDateTime reminderDate = JsonBaseDeserializerHelper.convertToLocalDateTime(((TextNode)node.get("reminderDate")).textValue());
		LocalDateTime reminderCreationDate = JsonBaseDeserializerHelper.convertToLocalDateTime(((TextNode)node.get("reminderCreationDate")).textValue());
		
		ReminderEntity reminder = new ReminderEntity(reminderId, reminderTutoringAppointment, null, reminderDate, reminderCreationDate);

		return reminder;
	}
}
