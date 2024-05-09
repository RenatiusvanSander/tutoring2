package edu.remad.tutoring2.json.customdeserializers;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.TextNode;

import edu.remad.tutoring2.json.JsonBaseDeserializerHelper;
import edu.remad.tutoring2.models.ReminderEntity;
import edu.remad.tutoring2.models.TutoringAppointmentEntity;
import edu.remad.tutoring2.models.UserEntity;

@JsonComponent
public class ReminderEntityDeserializer extends AbstractGenericTutoring2Deserializer<ReminderEntity> {

	/**
	 * serial version UID
	 */
	private static final long serialVersionUID = 1L;

	public ReminderEntityDeserializer() {
		super(ReminderEntity.class);
	}
	
	public ReminderEntityDeserializer(ObjectMapper objectMapper) {
		super(ReminderEntity.class, objectMapper);
	}

	public ReminderEntityDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public ReminderEntity deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
		if(p.getCodec() == null) {
			p.setCodec(objectMapper);
		}
		TreeNode node = p.getCodec().readTree(p);
		
		Long reminderId = ((IntNode)node.get("reminderNo")).asLong();
		JsonParser appointment = node.get("reminderTutoringAppointment").traverse();
		TutoringAppointmentEntity reminderTutoringAppointment = objectMapper.readValue(appointment, TutoringAppointmentEntity.class);
		LocalDateTime reminderDate = JsonBaseDeserializerHelper.convertToLocalDateTime(((TextNode)node.get("reminderDate")).textValue());
		LocalDateTime reminderCreationDate = JsonBaseDeserializerHelper.convertToLocalDateTime(((TextNode)node.get("reminderCreationDate")).textValue());
		JsonParser user = node.get("reminderUserEntity").traverse();
		UserEntity reminderUserEntity = objectMapper.readValue(user, UserEntity.class);
		
		return new ReminderEntity(reminderId, reminderTutoringAppointment, reminderUserEntity, reminderDate, reminderCreationDate);
	}
}
