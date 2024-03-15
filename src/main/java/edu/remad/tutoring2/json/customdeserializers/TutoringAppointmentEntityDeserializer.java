package edu.remad.tutoring2.json.customdeserializers;

import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.TextNode;

import edu.remad.tutoring2.json.JsonBaseDeserializerHelper;
import edu.remad.tutoring2.models.TutoringAppointmentEntity;
import edu.remad.tutoring2.models.UserEntity;

public class TutoringAppointmentEntityDeserializer extends StdDeserializer<TutoringAppointmentEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TutoringAppointmentEntityDeserializer() {
		this(null);
	}

	public TutoringAppointmentEntityDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public TutoringAppointmentEntity deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JacksonException {
		TreeNode node = p.getCodec().readTree(p);
		
		Long tutoringAppointmentNo = ((IntNode)node.get("tutoringAppointmentNo")).asLong();
		LocalDateTime tutoringAppointmentDate = JsonBaseDeserializerHelper.convertToLocalDateTime(((TextNode)node.get("tutoringAppointmentDate")).textValue());
		LocalDateTime tutoringAppointmentStartDateTime = JsonBaseDeserializerHelper.convertToLocalDateTime(((TextNode)node.get("tutoringAppointmentStartDateTime")).textValue());
		LocalDateTime tutoringAppointmentEndDateTime = JsonBaseDeserializerHelper.convertToLocalDateTime(((TextNode)node.get("tutoringAppointmentEndDateTime")).textValue());
		LocalDateTime tutoringAppointmentCreationDate = JsonBaseDeserializerHelper.convertToLocalDateTime(((TextNode)node.get("tutoringAppointmentCreationDate")).textValue());
		
		long tutoringAppointmentUser = ((IntNode)node.get("tutoringAppointmentUser").get("id")).asLong();
		UserEntity user = new UserEntity();
		user.setId(tutoringAppointmentUser);
		
		TutoringAppointmentEntity appointment = new TutoringAppointmentEntity(tutoringAppointmentNo, user, tutoringAppointmentDate, tutoringAppointmentStartDateTime, tutoringAppointmentEndDateTime, tutoringAppointmentCreationDate );
		
		return appointment;
	}
}
