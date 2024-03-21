package edu.remad.tutoring2.json.customdeserializers;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.TextNode;

import edu.remad.tutoring2.json.JsonBaseDeserializerHelper;
import edu.remad.tutoring2.models.AddressEntity;
import edu.remad.tutoring2.models.ZipCodeEntity;

@JsonComponent
public class ZipCodeEntityDeserializer extends StdDeserializer<ZipCodeEntity> {

	/**
	 * serial version UID
	 */
	private static final long serialVersionUID = -2015161933971036460L;
	@Autowired
	private ObjectMapper objectMapper;
	
	public ZipCodeEntityDeserializer() {
		this(null);
	}
	
	public ZipCodeEntityDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public ZipCodeEntity deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
		TreeNode node = p.getCodec().readTree(p);
		
		Long zipId = ((IntNode)node.get("id")).asLong();
		String zipCode = ((TextNode)node.get("zipCode")).textValue();
		String zipCodeLocation = ((TextNode)node.get("zipCodeLocation")).textValue();
		LocalDateTime zipCodeCreationDate = JsonBaseDeserializerHelper.convertToLocalDateTime(((TextNode)node.get("zipCodeCreationDate")).textValue());
		AddressEntity address = objectMapper.readValue(node.get("address").traverse(), AddressEntity.class);
		
		ZipCodeEntity zip = new ZipCodeEntity(zipId, zipCode, zipCodeLocation, zipCodeCreationDate, address);
		
		return zip;
	}
}
