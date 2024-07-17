package edu.remad.tutoring2.json.customserializer;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import edu.remad.tutoring2.models.PriceEntity;

@JsonComponent
public class PriceEntitySerializer extends AbstractGenericTutoring2Serializer<PriceEntity>{

	/**
	 * generated serial version UID
	 */
	private static final long serialVersionUID = -1999078113969148643L;

	public PriceEntitySerializer() {
		super(PriceEntity.class);
	}
	
	public PriceEntitySerializer(Class<PriceEntity> vc) {
		super(vc);
	}
	
	public PriceEntitySerializer(Class<PriceEntity> vc, ObjectMapper objectMapper) {
		super(vc, objectMapper);
	}

	@Override
	public void serialize(PriceEntity value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		gen.writeNumberField("id", value.getId());
		gen.writeObjectField("user", value.getUser());
		gen.writeNumberField("price",value.getPrice());
		gen.writeObjectField("serviceContract", value.getServiceContract());
		gen.writeEndObject();
		gen.flush();
	}

}
