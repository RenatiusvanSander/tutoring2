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
import edu.remad.tutoring2.models.TutoringAppointmentEntity;
import edu.remad.tutoring2.models.UserEntity;

@JsonComponent
public class TutoringAppointmentEntityDeserializer extends AbstractGenericTutoring2Deserializer<TutoringAppointmentEntity> {

	/**
	 * serial version UID
	 */
	private static final long serialVersionUID = 1L;
	
	public TutoringAppointmentEntityDeserializer() {
		super(TutoringAppointmentEntity.class);
	}

	public TutoringAppointmentEntityDeserializer(Class<?> vc) {
		super(vc);
	}

	public TutoringAppointmentEntityDeserializer(ObjectMapper objectMapper) {
		super(TutoringAppointmentEntity.class, objectMapper);
	}

	@Override
	public TutoringAppointmentEntity deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JacksonException {
		if(p.getCodec() == null) {
			p.setCodec(objectMapper);
		}
		TreeNode node = p.getCodec().readTree(p);
		
		Long tutoringAppointmentNo = ((IntNode)node.get("tutoringAppointmentNo")).asLong();
		LocalDateTime tutoringAppointmentDate = JsonBaseDeserializerHelper.convertToLocalDateTime(((TextNode)node.get("tutoringAppointmentDate")).textValue());
		LocalDateTime tutoringAppointmentStartDateTime = JsonBaseDeserializerHelper.convertToLocalDateTime(((TextNode)node.get("tutoringAppointmentStartDateTime")).textValue());
		LocalDateTime tutoringAppointmentEndDateTime = JsonBaseDeserializerHelper.convertToLocalDateTime(((TextNode)node.get("tutoringAppointmentEndDateTime")).textValue());
		LocalDateTime tutoringAppointmentCreationDate = JsonBaseDeserializerHelper.convertToLocalDateTime(((TextNode)node.get("tutoringAppointmentCreationDate")).textValue());
		
		JsonParser parser = node.get("tutoringAppointmentUser").traverse();
		parser.setCodec(objectMapper);
		UserEntity user = objectMapper.readValue(parser, UserEntity.class);
		TutoringAppointmentEntity appointment = new TutoringAppointmentEntity(tutoringAppointmentNo, user, tutoringAppointmentDate, tutoringAppointmentStartDateTime, tutoringAppointmentEndDateTime, tutoringAppointmentCreationDate );
		
		return appointment;
	}
}
