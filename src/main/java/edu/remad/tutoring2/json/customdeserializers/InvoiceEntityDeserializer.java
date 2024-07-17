package edu.remad.tutoring2.json.customdeserializers;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.NumericNode;
import com.fasterxml.jackson.databind.node.TextNode;

import edu.remad.tutoring2.json.JsonBaseDeserializerHelper;
import edu.remad.tutoring2.models.InvoiceEntity;
import edu.remad.tutoring2.models.PriceEntity;
import edu.remad.tutoring2.models.ServiceContractEntity;
import edu.remad.tutoring2.models.UserEntity;

@JsonComponent
public class InvoiceEntityDeserializer extends AbstractGenericTutoring2Deserializer<InvoiceEntity> {

	/**
	 * generated serial version UID
	 */
	private static final long serialVersionUID = 4426789536189707835L;

	public InvoiceEntityDeserializer() {
		super(InvoiceEntity.class);
	}

	public InvoiceEntityDeserializer(Class<?> vc) {
		super(vc);
	}
	
	public InvoiceEntityDeserializer(ObjectMapper objectMapper) {
		super(InvoiceEntity.class, objectMapper);
	}
	
	public InvoiceEntityDeserializer(Class<?> vc, ObjectMapper objectMapper) {
		super(vc, objectMapper);
	}

	@Override
	public InvoiceEntity deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
		if(p.getCodec() == null) {
			p.setCodec(objectMapper);
		}
		TreeNode node = p.getCodec().readTree(p);
		
		long id = node.get("invoiceNo") instanceof NullNode ? 0l : ((IntNode)node.get("invoiceNo")).asLong();
		
		JsonParser serviceContract = node.get("invoiceServiceContract").traverse();
		serviceContract.setCodec(objectMapper);
		ServiceContractEntity invoiceServiceContract = objectMapper.readValue(serviceContract, ServiceContractEntity.class);
		
		float invoiceTutoringHours = ((NumericNode)node.get("invoiceTutoringHours")).floatValue();
		
		LocalDateTime invoiceDate = JsonBaseDeserializerHelper.convertToLocalDateTime(((TextNode)node.get("invoiceDate")).asText());
		
		LocalDateTime invoiceTutoringDate = JsonBaseDeserializerHelper.convertToLocalDateTime(((TextNode)node.get("invoiceTutoringDate")).asText());
		
		JsonParser invoiceUserParser = node.get("invoiceUser").traverse();
		invoiceUserParser.setCodec(objectMapper);
		UserEntity invoiceUser = objectMapper.readValue(invoiceUserParser, UserEntity.class);
		
		JsonParser priceParser = node.get("price").traverse();
		priceParser.setCodec(objectMapper);
		PriceEntity price = objectMapper.readValue(priceParser, PriceEntity.class);
		
		LocalDateTime invoiceCreationDate = JsonBaseDeserializerHelper.convertToLocalDateTime(((TextNode)node.get("invoiceCreationDate")).asText());
		
		return new InvoiceEntity(id, invoiceServiceContract, invoiceTutoringHours, invoiceDate, invoiceTutoringDate, invoiceUser, price, invoiceCreationDate);
	}
}
