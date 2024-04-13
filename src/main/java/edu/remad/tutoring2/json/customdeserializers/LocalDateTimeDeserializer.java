package edu.remad.tutoring2.json.customdeserializers;

import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TextNode;

import edu.remad.tutoring2.json.JsonBaseDeserializerHelper;

// TODO delete
public class LocalDateTimeDeserializer extends AbstractGenericTutoring2Deserializer<LocalDateTime> {

	/**
	 * serial version UID
	 */
	private static final long serialVersionUID = 1L;

	public LocalDateTimeDeserializer() {
		super(LocalDateTime.class);
	}
	
	public LocalDateTimeDeserializer(ObjectMapper objectMapper) {
		super(LocalDateTime.class, objectMapper);
	}

	public LocalDateTimeDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JacksonException {
		if(p.getCodec() == null) {
			p.setCodec(objectMapper);
		}
		TreeNode node = p.getCodec().readTree(p);
		
		LocalDateTime serviceContractCreationDate = JsonBaseDeserializerHelper.convertToLocalDateTime(((TextNode)node.get("serviceContractCreationDate")).textValue());
		
		return serviceContractCreationDate;
	}
}
