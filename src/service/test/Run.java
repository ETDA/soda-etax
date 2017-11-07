package service.test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import service.EtaxInvoice;

public class Run {

	public static void main(String args[]) {
		try {
			String jsonString = new String(Files.readAllBytes(Paths.get("resources/json_input.json")),
					StandardCharsets.UTF_8);
			EtaxInvoice invoice = new EtaxInvoice(jsonString);
			System.out.println(invoice.getXML());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}