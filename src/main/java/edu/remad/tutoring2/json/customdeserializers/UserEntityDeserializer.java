package edu.remad.tutoring2.json.customdeserializers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.TextNode;

import edu.remad.tutoring2.json.JsonBaseDeserializerHelper;
import edu.remad.tutoring2.models.AddressEntity;
import edu.remad.tutoring2.models.UserEntity;

public class UserEntityDeserializer extends StdDeserializer<UserEntity> {

	/**
	 * serial version UID
	 */
	private static final long serialVersionUID = 70275258098187301L;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	/**
	 * Constructor
	 */
	public UserEntityDeserializer() {
		this(null);
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
		TreeNode node = p.getCodec().readTree(p);
		Long id = ((IntNode)node.get("id")).asLong();
		String username = ((TextNode)node.get("username")).textValue();
		String email = ((TextNode)node.get("email")).textValue();
		String password = ((TextNode)node.get("password")).textValue();
		boolean enabled = ((BooleanNode)node.get("enabled")).booleanValue();
		// roles
		// tokens
		String firstName = ((TextNode)node.get("firstName")).textValue();
		String lastName = ((TextNode)node.get("lastName")).textValue();
		String gender = ((TextNode)node.get("gender")).textValue();
		String cellPhone = ((TextNode)node.get("cellPhone")).textValue();
		ObjectReader addressReader = objectMapper.readerFor(new TypeReference<List<AddressEntity>>() {});
		ArrayNode addressNode = (ArrayNode)node.get("addresses");
		List<AddressEntity> addresses = addressReader.readValue(addressNode);
		
		TreeNode date = node.get("creationDate");
		LocalDateTime creationDate = null;
		if(date instanceof ArrayNode) {
			ArrayNode arr = (ArrayNode)date;
			creationDate = LocalDateTime.of(arr.get(0).asInt(), arr.get(1).asInt(), arr.get(3).asInt(), arr.get(2).asInt(), arr.get(4).asInt());
		} else {
			creationDate = JsonBaseDeserializerHelper.convertToLocalDateTime(((TextNode)node.get("creationDate")).textValue());
		}
		
		UserEntity user = new UserEntity(id, username, email, password, enabled, new ArrayList<>(), new ArrayList<>(), firstName, lastName, gender, cellPhone, addresses, creationDate);
		
		return user;
	}
	
	/**
	 * Sets Codec
	 * 
	 * @param c {@link ObjectCodec} shalls be {@link ObjectMapper} or {@link ObjectReader}
	 */
	public void setCodec(ObjectCodec c) {
		objectMapper = (ObjectMapper) c;
	}
}
