package edu.remad.tutoring2.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
	@Disabled
	public void writeToFileTest() throws IOException {
		PdfCreatorService pdfService = new PdfCreatorServiceImpl();

		byte[] pdf = pdfService.createInvoicePdf(invoice);

		String filePath = "C:\\Users\\remad\\writeToFile.pdf";
		writeToFile(filePath, pdf);
		Path pathFile = new File(filePath).toPath();
		boolean actualFileExists = Files.exists(pathFile);
		byte[] actualFileBytes = Files.readAllBytes(pathFile);

		assertTrue(actualFileExists);
		assertEquals(5850, actualFileBytes.length);
	}

	@Test
	public void createInvoicePdfTest() throws IOException {
		PdfCreatorService pdfService = new PdfCreatorServiceImpl();

		byte[] pdf = pdfService.createInvoicePdf(invoice);

		assertEquals(5850, pdf.length);
		writeToFile("C:\\Users\\remad\\invoice_2_generated.pdf", pdf);
	}

	@Test
	public void createInvoicesPdfsTest() throws IOException {
		List<InvoiceEntity> invoices = createInvoices();
		PdfCreatorService pdfService = new PdfCreatorServiceImpl();

		byte[] pdFInvoicesAsOneFile = pdfService.createInvoicesPdfs(invoices);

		assertTrue(pdFInvoicesAsOneFile.length > 0);
		assertEquals(10912, pdFInvoicesAsOneFile.length);

		writeToFile("C:\\Users\\remad\\2_invoices_generated.pdf", pdFInvoicesAsOneFile);
	}

	private void writeToFile(String filePath, byte[] pdf) throws IOException {
		Files.write(new File(filePath).toPath(), pdf);
	}
}
