package serviceInterface;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import etda.uncefact.data.standard.taxinvoice_crossindustryinvoice._2.TaxInvoiceCrossIndustryInvoiceType;

public interface EtaxInvoice {

	//TaxInvoiceCrossIndustryInvoiceType xmlRootElement;
	void setSeller(JSONObject seller);
	void setBuyer(JSONObject buyer);
	void setDocumentDetail(JSONObject documentDetail);
	void setItems(JSONArray items);
	void setReferencedDocument(JSONArray documents);
	void setTradeSettlement(JSONObject tradeSettlement);
	
}
