package service.test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import service.EtaxInvoice;

public class Run {

	public static void main(String args[]) {
		try {
			
			/**** Sample Input ****/
			/*String inputFilePath = "resources/json_input.json";
			String outputFilePath = "target/ETDA-invoice.xml";
			*/
			
			String inputFilePath = args[0];
			String outputFilePath = args[1];
			
			String xmlString = new String(Files.readAllBytes(Paths.get(inputFilePath)),
					StandardCharsets.UTF_8);
			EtaxInvoice invoice = new EtaxInvoice(xmlString);					
			invoice.getXML(outputFilePath);
			System.out.println("Write XML to :"+ outputFilePath);	

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}