package edu.remad.tutoring2.json.customdeserializers;

import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.LongNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.jayway.jsonpath.internal.filter.ValueNodes.StringNode;

import edu.remad.tutoring2.json.JsonBaseDeserializerHelper;
import edu.remad.tutoring2.models.ServiceContractEntity;

public class ServiceContractEntityDeserializer extends StdDeserializer<ServiceContractEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServiceContractEntityDeserializer() {
		this(null);
	}

	public ServiceContractEntityDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public ServiceContractEntity deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JacksonException {
		TreeNode node = p.getCodec().readTree(p);
		
		Long serviceContractNo = (Long)((LongNode) node.get("serviceContractNo")).numberValue();
		String serviceContractName = (String) ((StringNode)node.get("serviceContractName")).getString();
		String serviceContractDescription = (String) ((StringNode)node.get("serviceContractDescription")).getString();
		LocalDateTime serviceContractCreationDate = JsonBaseDeserializerHelper.convertToLocalDateTime(((TextNode)node.get("serviceContractCreationDate")).textValue());
		
		ServiceContractEntity serviceContract = new ServiceContractEntity(serviceContractNo, serviceContractName, serviceContractDescription, serviceContractCreationDate);
		
		return serviceContract;
	}
}
