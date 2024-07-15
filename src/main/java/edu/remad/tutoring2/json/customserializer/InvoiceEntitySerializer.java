package edu.remad.tutoring2.json.customserializer;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import edu.remad.tutoring2.appconstants.TimeAppConstants;
import edu.remad.tutoring2.models.InvoiceEntity;
import lombok.val;

@JsonComponent
public class InvoiceEntitySerializer extends AbstractGenericTutoring2Serializer<InvoiceEntity> {

	/** generated serial version UID */
	private static final long serialVersionUID = -5105784886344395899L;

	public InvoiceEntitySerializer(Class<InvoiceEntity> vc) {
		super(vc);
	}
	
	public InvoiceEntitySerializer(Class<InvoiceEntity> vc, ObjectMapper objectMapper) {
		super(vc, objectMapper);
	}

	public InvoiceEntitySerializer() {
		super(InvoiceEntity.class);
	}

	@Override
	public void serialize(InvoiceEntity value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		gen.writeNumberField("invoiceNo", value.getInvoiceNo());
		gen.writeObjectField("invoiceServiceContract", value.getInvoiceServiceContract());
		gen.writeNumberField("invoiceTutoringHours", value.getInvoiceTutoringHours());
		gen.writeStringField("invoiceDate", value.getInvoiceDate().format(TimeAppConstants.LOCAL_DATE_TIME_FORMATTER));
		gen.writeStringField("invoiceTutoringDate", value.getInvoiceTutoringDate().format(TimeAppConstants.LOCAL_DATE_TIME_FORMATTER));
		gen.writeObjectField("invoiceUser", value.getInvoiceUser());
		gen.writeObjectField("price", value.getPrice());
		gen.writeStringField("invoiceCreationDate", value.getInvoiceCreationDate().format(TimeAppConstants.LOCAL_DATE_TIME_FORMATTER));
		gen.writeEndObject();
		gen.flush();
	}
}
