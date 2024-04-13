package edu.remad.tutoring2.json.customdeserializers;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.remad.tutoring2.models.TokenEntity;

@JsonComponent
public class TokenEntityDeserializer extends AbstractGenericTutoring2Deserializer<TokenEntity> {

	/**
	 * generated serial version UID
	 */
	private static final long serialVersionUID = -4076590352948105441L;

	public TokenEntityDeserializer() {
		super(TokenEntity.class);
	}
	
	public TokenEntityDeserializer(ObjectMapper objectMapper) {
		super(TokenEntity.class, objectMapper);
	}
	
	public TokenEntityDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public TokenEntity deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
		if(p.getCodec() == null) {
			p.setCodec(objectMapper);
		}
		
		TreeNode node = p.getCodec().readTree(p);
		
		return new TokenEntity();
	}
}
