package service.test;

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
			pdfACreator.convert2PDF(invoice.invoice, invoice.getXML(), "E:/test.pdf");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}