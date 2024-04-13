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
import com.fasterxml.jackson.databind.node.TextNode;

import edu.remad.tutoring2.json.JsonBaseDeserializerHelper;
import edu.remad.tutoring2.models.ServiceContractEntity;

@JsonComponent
public class ServiceContractEntityDeserializer extends AbstractGenericTutoring2Deserializer<ServiceContractEntity> {
	
	/**
	 * serial version uid
	 */
	private static final long serialVersionUID = 1L;

	public ServiceContractEntityDeserializer() {
		super(ServiceContractEntity.class);
	}
	
	public ServiceContractEntityDeserializer(ObjectMapper objectMapper) {
		super(ServiceContractEntity.class, objectMapper);
	}

	public ServiceContractEntityDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public ServiceContractEntity deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JacksonException {
		if(p.getCodec() == null) {
			p.setCodec(objectMapper);
		}
		TreeNode node = p.getCodec().readTree(p);
		
		Long serviceContractNo = ((IntNode) node.get("serviceContractNo")).numberValue().longValue();
		String serviceContractName = ((TextNode)node.get("serviceContractName")).textValue();
		String serviceContractDescription = ((TextNode)node.get("serviceContractDescription")).textValue();
		LocalDateTime serviceContractCreationDate = JsonBaseDeserializerHelper.convertToLocalDateTimeAsDate(((TextNode)node.get("serviceContractCreationDate")).textValue());
		
		ServiceContractEntity serviceContract = new ServiceContractEntity(serviceContractNo, serviceContractName, serviceContractDescription, serviceContractCreationDate);
		
		return serviceContract;
	}
}
