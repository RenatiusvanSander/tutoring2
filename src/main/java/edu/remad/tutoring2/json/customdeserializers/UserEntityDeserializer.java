package edu.remad.tutoring2.json.customdeserializers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Arrays;
import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.TextNode;

import edu.remad.tutoring2.json.JsonBaseDeserializerHelper;
import edu.remad.tutoring2.models.AddressEntity;
import edu.remad.tutoring2.models.UserEntity;

@JsonComponent
public class UserEntityDeserializer extends AbstractGenericTutoring2Deserializer<UserEntity> {

	/**
	 * serial version UID
	 */
	private static final long serialVersionUID = 70275258098187301L;

	/**
	 * Constructor
	 */
	public UserEntityDeserializer() {
		super(UserEntity.class);
	}

	/**
	 * Constructor
	 * 
	 * @param objectMapper object mapper bean
	 */
	public UserEntityDeserializer(ObjectMapper objectMapper) {
		super(UserEntity.class, objectMapper);
	}

	/**
	 * Constructor
	 * 
	 * @param vc type of class instance
	 */
	public UserEntityDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public UserEntity deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
		if (p.getCodec() == null) {
			p.setCodec(objectMapper);
		}

		TreeNode node = p.getCodec().readTree(p);
		Long id = node.get("id") instanceof NullNode ? -1L : ((IntNode) node.get("id")).asLong();
		TreeNode usernameNode = node.get("username");
		String username =  usernameNode instanceof NullNode ? "" : ((TextNode) node.get("username")).textValue();
		String email = node.get("email") instanceof NullNode ? "" : ((TextNode) node.get("email")).textValue();
		String password = node.get("password") instanceof NullNode ? "" : ((TextNode) node.get("password")).textValue();
		boolean enabled = ((BooleanNode) node.get("enabled")).booleanValue();
		// roles
		// tokens
		String firstName = node.get("firstName") instanceof NullNode ? "null" : ((TextNode) node.get("firstName")).textValue();
		String lastName = node.get("lastName") instanceof NullNode ? "" : ((TextNode) node.get("lastName")).textValue();
		String gender = node.get("gender") instanceof NullNode ? "" : ((TextNode) node.get("gender")).textValue();
		String cellPhone = node.get("cellPhone") instanceof NullNode ? "" : ((TextNode) node.get("cellPhone")).textValue();
		ObjectReader addressReader = objectMapper.readerFor(new TypeReference<List<AddressEntity>>() {
		});

		TreeNode addressesNode = node.get("addresses");

		List<AddressEntity> addresses = new ArrayList<>();
		if (addressesNode instanceof ArrayNode) {
			ArrayNode addressNode = (ArrayNode) node.get("addresses");
			addresses = addressReader.readValue(addressNode);
		} else {
			String addressNode = node.get("addresses") == null ? "" : ((TextNode) node.get("addresses")).asText();
			AddressEntity[] address = StringUtils.isBlank(addressNode) ? new AddressEntity[0] : objectMapper.readValue(addressNode, AddressEntity[].class);
			addresses = address == null || address.length == 0 ? Collections.emptyList() : Arrays.asList(address).stream().filter(AddressEntity.class::isInstance)
					.map(AddressEntity.class::cast).collect(Collectors.toList());
		}

		TreeNode date = node.get("creationDate");
		LocalDateTime creationDate = null;
		if (date instanceof ArrayNode) {
			ArrayNode arr = (ArrayNode) date;
			creationDate = LocalDateTime.of(arr.get(0).asInt(), arr.get(1).asInt(), arr.get(3).asInt(),
					arr.get(2).asInt(), arr.get(4).asInt());
		} else {
			creationDate = node.get("creationDate") instanceof NullNode ? null : JsonBaseDeserializerHelper
					.convertToLocalDateTimeAsDate(((TextNode) node.get("creationDate")).textValue());
		}

		UserEntity user = new UserEntity(id, username, email, password, enabled, new ArrayList<>(), new ArrayList<>(),
				firstName, lastName, gender, cellPhone, addresses, creationDate);

		return user;
	}
}
