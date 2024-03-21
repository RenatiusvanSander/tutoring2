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
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import edu.remad.tutoring2.json.JsonBaseDeserializerHelper;
import edu.remad.tutoring2.models.AddressEntity;
import edu.remad.tutoring2.models.ZipCodeEntity;

@JsonComponent
public class AddressEntityDeserializer extends StdDeserializer<AddressEntity> {

	/**
	 * serial version UID
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	public AddressEntityDeserializer(ObjectMapper objectMapper) {
		super(AddressEntity.class);
		this.objectMapper = objectMapper;
	}

	public AddressEntityDeserializer() {
		super(AddressEntity.class);
	}

	public AddressEntityDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public AddressEntity deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
		TreeNode node = p.getCodec().readTree(p);

		Long id = ((IntNode) node.get("id")).asLong();
		String addressStreet = ((TextNode) node.get("addressStreet")).textValue();
		String addressHouseNo = ((TextNode) node.get("addressHouseNo")).textValue();
		ObjectNode zipNode = (ObjectNode) node.get("addressZipCode");

		Long zipId = ((IntNode) zipNode.get("id")).asLong();
		String zipCode = ((TextNode) zipNode.get("zipCode")).textValue();
		String zipCodeLocation = ((TextNode) zipNode.get("zipCodeLocation")).textValue();

		TreeNode creationDate = zipNode.get("zipCodeCreationDate");

		LocalDateTime zipCodeCreationDate = null;
		if (creationDate instanceof TextNode) {
			zipCodeCreationDate = JsonBaseDeserializerHelper
					.convertToLocalDateTime(((TextNode) creationDate).textValue());
		} else {
			ArrayNode date = (ArrayNode) creationDate;
			zipCodeCreationDate = LocalDateTime.of(date.get(0).asInt(), date.get(1).asInt(), date.get(3).asInt(),
					date.get(2).asInt(), date.get(4).asInt());
		}

		ZipCodeEntity zip = new ZipCodeEntity(zipId, zipCode, zipCodeLocation, zipCodeCreationDate, null);

		AddressEntity address = new AddressEntity(id, addressStreet, addressHouseNo, zip);

		zip.setAddress(address);

		return address;
	}
}
