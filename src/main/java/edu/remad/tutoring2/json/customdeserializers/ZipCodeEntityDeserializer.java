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
import edu.remad.tutoring2.models.ZipCodeEntity;

@JsonComponent
public class ZipCodeEntityDeserializer extends AbstractGenericTutoring2Deserializer<ZipCodeEntity> {

	/**
	 * serial version UID
	 */
	private static final long serialVersionUID = -2015161933971036460L;
	
	public ZipCodeEntityDeserializer() {
		super(ZipCodeEntity.class);
	}
	
	public ZipCodeEntityDeserializer(ObjectMapper objectMapper) {
		super(ZipCodeEntity.class, objectMapper);
	}
	
	public ZipCodeEntityDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public ZipCodeEntity deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
		if(p.getCodec() == null) {
			p.setCodec(objectMapper);
		}
		TreeNode node = p.getCodec().readTree(p);
		
		Long zipId = ((IntNode)node.get("id")).asLong();
		String zipCode = ((TextNode)node.get("zipCode")).textValue();
		String zipCodeLocation = ((TextNode)node.get("zipCodeLocation")).textValue();
		LocalDateTime zipCodeCreationDate = JsonBaseDeserializerHelper.convertToLocalDateTimeAsDate(((TextNode)node.get("zipCodeCreationDate")).textValue());
		ZipCodeEntity zip = new ZipCodeEntity(zipId, zipCode, zipCodeLocation, zipCodeCreationDate);
		
		return zip;
	}
}
