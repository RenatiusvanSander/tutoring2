package edu.remad.tutoring2.json.customdeserializers;

import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;

import edu.remad.tutoring2.json.JsonBaseDeserializerHelper;

// TODO delete
public class LocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> {

	/**
	 * serial version UID
	 */
	private static final long serialVersionUID = 1L;

	public LocalDateTimeDeserializer() {
		this(null);
	}

	public LocalDateTimeDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JacksonException {
		TreeNode node = p.getCodec().readTree(p);
		
		LocalDateTime serviceContractCreationDate = JsonBaseDeserializerHelper.convertToLocalDateTime(((TextNode)node.get("serviceContractCreationDate")).textValue());
		
		return serviceContractCreationDate;
	}
}
