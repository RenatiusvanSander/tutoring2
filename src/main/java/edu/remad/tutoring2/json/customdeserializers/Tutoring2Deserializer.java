package edu.remad.tutoring2.json.customdeserializers;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public abstract class Tutoring2Deserializer<T> extends StdDeserializer<T> {
	
	/**
	 * serial version UID
	 */
	protected static final long serialVersionUID = 1L;
	
	protected ObjectMapper objectMapper;
	
	// protected final JsonBaseDeserializerHelper deserializerHelper;
	
	protected Tutoring2Deserializer(Class<?> vc) {
		super(vc);
		// deserializerHelper = new JsonBaseDeserializerHelper();
	}
	
	protected Tutoring2Deserializer(Class<?> vc, ObjectMapper objectMapper) {
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
