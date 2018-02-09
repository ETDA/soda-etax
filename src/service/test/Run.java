package service.test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import service.EtaxDebitCreditnote;
import service.EtaxInvoice;

public class Run {

	public static void main(String args[]) {
		try {
			
			/**** Sample Input ****/
			/*String inputFilePath_inv = "resources/json_input_inv.json";
			String outputFilePath_inv = "target/ETDA-invoice_Invoice.xml";
			String inputFilePath_dbncrn = "resources/json_input_dbncrn.json";
			String outputFilePath_dbncrn = "target/ETDA-invoice_DebitCreditnote.xml";
			*/
			
			String inputFilePath_inv = args[0];
			String outputFilePath_inv = args[1];
			String inputFilePath_dbncrn = args[2];
			String outputFilePath_dbncrn = args[3];
			
			String xmlString_inv = new String(Files.readAllBytes(Paths.get(inputFilePath_inv)),	StandardCharsets.UTF_8);
			EtaxInvoice invoice = new EtaxInvoice(xmlString_inv);					
			invoice.getXML(outputFilePath_inv);
			System.out.println("Write XML to :"+ outputFilePath_inv);	
			
			String xmlString_dbncrn = new String(Files.readAllBytes(Paths.get(inputFilePath_dbncrn)),	StandardCharsets.UTF_8);
			EtaxDebitCreditnote invoice_dbncrn = new EtaxDebitCreditnote(xmlString_dbncrn);					
			invoice_dbncrn.getXML(outputFilePath_dbncrn);
			System.out.println("Write XML to :"+ outputFilePath_dbncrn);	

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}