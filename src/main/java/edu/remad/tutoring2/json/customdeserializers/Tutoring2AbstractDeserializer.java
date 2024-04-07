package edu.remad.tutoring2.json.customdeserializers;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

interface Tutoring2Deserializer {

	/**
	 * serial version UID
	 */
	static final long serialVersionUID = 1L;
	
	/**
	 * Sets Codec
	 * 
	 * @param c {@link ObjectCodec} shalls be {@link ObjectMapper} or
	 *          {@link ObjectReader}
	 */
	void setCodec(ObjectCodec c);
}
