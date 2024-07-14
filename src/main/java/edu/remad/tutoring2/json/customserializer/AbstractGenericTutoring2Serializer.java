package edu.remad.tutoring2.json.customserializer;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public abstract class AbstractGenericTutoring2Serializer<T> extends StdSerializer<T> {

	/**
	 * serial version UID
	 */
	private static final long serialVersionUID = -7994085869490583813L;
	
	/**
	 * Jackson's {@link ObjectMapper}
	 */
	@Autowired
	protected ObjectMapper objectMapper;
	
	/**
	 * Constructor
	 *  
	 * @param vc the class type to serialize for
	 */
	protected AbstractGenericTutoring2Serializer(Class<T> vc) {
		super(vc);
	}
	
	/**
	 * Constructor
	 * 
	 * @param vc the class type to serialize for
	 * @param objectMapper Jackson's {@link ObjectMapper}
	 */
	protected AbstractGenericTutoring2Serializer(Class<T> vc, ObjectMapper objectMapper) {
		super(vc);
		this.objectMapper = objectMapper;
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
	public ObjectCodec getCodec() {
		return objectMapper;
	}
	
	protected boolean isEntityNull(T entity) {
		return entity == null;
	}

	public abstract void serialize(T value, JsonGenerator gen, SerializerProvider provider) throws IOException;
}
