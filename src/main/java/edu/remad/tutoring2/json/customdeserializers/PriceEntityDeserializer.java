package edu.remad.tutoring2.json.customdeserializers;

import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.NumericNode;

import edu.remad.tutoring2.models.PriceEntity;
import edu.remad.tutoring2.models.ServiceContractEntity;
import edu.remad.tutoring2.models.UserEntity;

@JsonComponent
public class PriceEntityDeserializer extends AbstractGenericTutoring2Deserializer<PriceEntity> {

	/**
	 * serial version UID
	 */
	private static final long serialVersionUID = 1L;

	public PriceEntityDeserializer() {
		super(PriceEntity.class);
	}

	public PriceEntityDeserializer(Class<?> vc) {
		super(vc);
	}
	
	public PriceEntityDeserializer(ObjectMapper objectMapper) {
		super(PriceEntity.class, objectMapper);
	}
	
	public PriceEntityDeserializer(Class<?> vc, ObjectMapper objectMapper) {
		super(vc, objectMapper);
	}

	@Override
	public PriceEntity deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
		if(p.getCodec() == null) {
			p.setCodec(objectMapper);
		}
		TreeNode node = p.getCodec().readTree(p);
		
		TreeNode priceIdNode = node.get("id");
		long priceId = priceIdNode instanceof NullNode ? 0L : ((IntNode)priceIdNode).asLong();
		
		JsonParser userParser = node.get("user").traverse();
		userParser.setCodec(objectMapper);
		UserEntity user = objectMapper.readValue(userParser, UserEntity.class);
		
		TreeNode priceNode = node.get("price");
		BigDecimal price = priceNode instanceof NullNode ? BigDecimal.valueOf(0.00f) : ((NumericNode)priceNode).decimalValue();
		
		JsonParser serviceContractParser = node.get("serviceContract").traverse();
		serviceContractParser.setCodec(objectMapper);
		ServiceContractEntity serviceContract = objectMapper.readValue(serviceContractParser, ServiceContractEntity.class);
		
		return new PriceEntity(priceId, user, price,  serviceContract);
	}

}
