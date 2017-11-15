package service.test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import service.EtaxInvoice;
import service.PDFACreator;

public class Run {

	public static void main(String args[]) {
		try {
			String xmlString = new String(Files.readAllBytes(Paths.get("resources/json_input.json")),
					StandardCharsets.UTF_8);
			EtaxInvoice invoice = new EtaxInvoice(xmlString);
			System.out.println(invoice.getXML());
			
			// Create PDFA with Pdfbox
			PDFACreator pdfACreator = new PDFACreator();
			byte[] result = pdfACreator.convert2PDF(invoice.invoice, invoice.getXML());
			Files.write(new File("E:/test.pdf").toPath(), result);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}