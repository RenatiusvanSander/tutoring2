package edu.remad.tutoring2.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import edu.remad.tutoring2.models.InvoiceEntity;
import edu.remad.tutoring2.services.impl.PdfCreatorServiceImpl;

public class PdfCreatorServiceTest extends AbstractJunit5ServiceJpaTest {

	private InvoiceEntity invoice;

	@BeforeEach
	void setUp() {
		invoice = createInvoice();
	}

	@Test
	public void createInvoicePdfTest() throws IOException {
		PdfCreatorService pdfService = new PdfCreatorServiceImpl();
		
		byte[] pdf = pdfService.createInvoicePdf(invoice);
		
		assertEquals(5850, pdf.length);
		File file = new File("C:\\Users\\remad\\invoice_2_generated.pdf");
		Files.write(file.toPath(), pdf);
	}
}
