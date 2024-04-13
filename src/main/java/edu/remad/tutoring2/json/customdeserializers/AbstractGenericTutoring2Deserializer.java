package edu.remad.tutoring2.json.customdeserializers;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public abstract class AbstractGenericTutoring2Deserializer<T> extends StdDeserializer<T> {
	
	/**
	 * serial version UID
	 */
	protected static final long serialVersionUID = 1L;
	
	/**
	 * Jackson's {@link ObjectMapper}
	 */
	@Autowired
	protected ObjectMapper objectMapper;
	
	// protected final JsonBaseDeserializerHelper deserializerHelper;
	
	protected AbstractGenericTutoring2Deserializer(Class<?> vc) {
		super(vc);
		// deserializerHelper = new JsonBaseDeserializerHelper();
	}
	
	protected AbstractGenericTutoring2Deserializer(Class<?> vc, ObjectMapper objectMapper) {
		super(vc);
		this.objectMapper = objectMapper;
		// deserializerHelper = new JsonBaseDeserializerHelper();
	}
	
	/**
	 * Sets Codec
	 * 
	 * @param c {@link ObjectCodec} shalls be {@link ObjectMapper} or
	 *          {@link ObjectReader}
	 */
	public void setCodec(ObjectCodec c) {
		this.objectMapper = (ObjectMapper) c;
	}
	
	/**
	 * Gets Codec
	 * 
	 * @return {@link ObjectCodec} 
	 */
	public ObjectCodec getCodec(ObjectCodec c) {
		return c;
	}
}
