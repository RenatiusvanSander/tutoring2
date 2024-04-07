package edu.remad.tutoring2.json.customdeserializers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.TextNode;

import edu.remad.tutoring2.json.JsonBaseDeserializerHelper;
import edu.remad.tutoring2.models.Role;
import edu.remad.tutoring2.models.TutoringAppointmentEntity;
import edu.remad.tutoring2.models.UserEntity;

@JsonComponent
public class RoleDeserializer extends StdDeserializer<Role> {

	/**
	 * serial version UID
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ObjectMapper objectMapper;

	public RoleDeserializer() {
		this(Role.class);
	}

	public RoleDeserializer(Class<?> vc) {
		super(vc);
	}

	public RoleDeserializer(ObjectMapper objectMapper) {
		this(TutoringAppointmentEntity.class);
		this.objectMapper = objectMapper;
	}

	@Override
	public Role deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
		TreeNode node = p.getCodec().readTree(p);

		int id = ((IntNode) node.get("id")).intValue();
		String name = ((TextNode)node.get("name")).asText();
		
		ObjectReader rolesReader = JsonBaseDeserializerHelper.createRolesReader(objectMapper);
		ArrayNode usersNode = (ArrayNode) node.get("users");
		List<UserEntity> roles = rolesReader.readValue(usersNode);

		return new Role(id, name, roles);
	}

	/**
	 * Sets Codec
	 * 
	 * @param c {@link ObjectCodec} shalls be {@link ObjectMapper} or
	 *          {@link ObjectReader}
	 */
	public void setCodec(ObjectCodec c) {
		objectMapper = (ObjectMapper) c;
	}
}
