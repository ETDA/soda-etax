package service.test;

import java.util.ArrayList;
import java.util.Date;

import javax.xml.datatype.DatatypeConfigurationException;

import service.obj.*;
import service.obj.TradeTax;
import etda.uncefact.codelist.standard.thaidocumentnamecode_invoice._1.ThaiDocumentNameCodeInvoiceContentType;
import etda.uncefact.data.standard.qualifieddatatype._1.*;
import etda.uncefact.data.standard.taxinvoice_crossindustryinvoice._2.ObjectFactory;
import etda.uncefact.data.standard.taxinvoice_crossindustryinvoice._2.TaxInvoiceCrossIndustryInvoiceType;
import etda.uncefact.data.standard.taxinvoice_reusableaggregatebusinessinformationentity._2.*;
import un.unece.uncefact.codelist.standard.iso.iso3alphacurrencycode._2012_08_31.ISO3AlphaCurrencyCodeContentType;
import un.unece.uncefact.codelist.standard.unece.dutytaxfeetypecode.d14a.DutyTaxFeeTypeCodeContentType;
import un.unece.uncefact.identifierlist.standard.citynamefromtisi1099_2548.CityNameCodeContentType;
import un.unece.uncefact.identifierlist.standard.citysubdivisionnamefromtisi1099_2548.CitySubDivisionNameCodeContentType;
import un.unece.uncefact.identifierlist.standard.iso.isotwo_lettercountrycode.secondedition2006.ISOTwoletterCountryCodeContentType;
import util.DateHelper;
import util.JsonHelper;
import util.StringHelper;

public class RunWithObj {

	private static StringHelper stringHelper = new util.StringHelper();
	private static DateHelper dateHelper = new util.DateHelper();

	public static void main(String[] args) {
		Invoice invoice = new Invoice();
		DocumentDetail detail = new DocumentDetail();
		detail.setDocumentID("TIV000012");
		detail.setName("ใบกำกับภาษี");
		detail.setTypeCode("388");
		detail.setPurpose("ซื้อสินค้า");
		detail.setSchemeAgencyID("ETDA");
		detail.setSchemeVersionID("v2.0");
		detail.setCreationDateTime(new Date("2016-06-06T13:59:30.000"));
		detail.setIssueDateTime(new Date("2016-06-06T00:00:00.000"));
		invoice.setDocumentDetail(detail);
		// Buyer
		Buyer buyer = new Buyer();
		buyer.setName("บริษัท สัพเพเหระ จำกัด");
		buyer.setTaxID("1234567890123");
		buyer.setBranchCode("0000");
		buyer.setTelephone("+66-21231234");
		buyer.setUriID("www.abc.com");
		buyer.setDepartmentName("R&D");
		buyer.setContactName("นาย....");
		Address bAddress = new Address();
		bAddress.setBuildingName("The ninth");
		bAddress.setLineOne("3/107 ถนนศรีนครินทร์");
		bAddress.setLineTwo("ซอยศรีนครินทร์ 5");
		bAddress.setCountryID("TH");
		bAddress.setCountrySubDivisionID("10");
		bAddress.setCityID("1032");
		bAddress.setCitySubDivisionID("103202");
		bAddress.setPostCodeCode("10260");
		buyer.setAddress(bAddress);
		invoice.setBuyer(buyer);
		// Seller
		Seller seller = new Seller();
		seller.setName("วิชัย ธรรมมา");
		seller.setTaxID("3210987654321");
		seller.setBranchCode("00017");
		seller.setTelephone("+66-29437799(5678)");
		seller.setUriID("www.cdf.com");
		seller.setDepartmentName("Sell");
		seller.setContactName("นาย....");
		Address sAddress = new Address();
		sAddress.setBuildingName("Central");
		sAddress.setLineOne("12/65 ถนนสุขุมวิท 64");
		sAddress.setLineTwo("ซอย สุขุมวิท 4 แยก 12");
		sAddress.setCountryID("TH");
		sAddress.setCountrySubDivisionID("10");
		sAddress.setCityID("1009");
		sAddress.setCitySubDivisionID("100902");
		sAddress.setPostCodeCode("10260");
		seller.setAddress(sAddress);
		invoice.setSeller(seller);
		// Line Items
		ArrayList<LineItem> lineItems = new ArrayList<LineItem>();
		LineItem lineItem1 = new LineItem();
		lineItem1.setLineID("1");
		lineItem1.setName("นวดอโรมา");
		lineItem1.setChargeAmount(900);
		lineItem1.setBilledQuantity(2);
		lineItem1.setUnicode("ZZ");
		lineItem1.setUnitname("ครั้ง");
		lineItem1.setChargeIndicator(false);
		lineItem1.setActualAmount(270);
		lineItem1.setNetlineTotalAmount(1530);
		lineItem1.setProductID("AS100000");
		lineItems.add(lineItem1);

		LineItem lineItem2 = new LineItem();
		lineItem2.setLineID("2");
		lineItem2.setName("ดินสอสี");
		lineItem2.setChargeAmount(70);
		lineItem2.setBilledQuantity(5);
		lineItem2.setUnicode("ZZ");
		lineItem2.setUnitname("กล่อง");
		lineItem2.setChargeIndicator(false);
		lineItem2.setActualAmount(60);
		lineItem2.setNetlineTotalAmount(290);
		lineItem2.setProductID("AS100001");
		lineItems.add(lineItem2);

		invoice.setLineItems(lineItems);

		ArrayList<ReferencedDocument> referencedDocuments = new ArrayList<>();
		ReferencedDocument rDocument = new ReferencedDocument();
		rDocument.setReferenceTypeCode("388");
		rDocument.setIssuerAssignedID("TIV000005");
		rDocument.setIssueDateTime(new Date("2016-06-01T00:00:00.000"));
		referencedDocuments.add(rDocument);
		invoice.setReferencedDocuments(referencedDocuments);

		// Trade Settlement
		TradeSettlement tradeSettlement = new TradeSettlement();
		tradeSettlement.setInvoiceCurrencyCode("THB");

		TradeTax tradeTax = new TradeTax();
		tradeTax.setBasisAmount(1000.00);
		tradeTax.setCalculatedRate(7.00);
		tradeTax.setTypeCode("VAT");
		tradeSettlement.setTradeTax(tradeTax);

		TradeAllowanceCharge tradeAllowanceCharge = new TradeAllowanceCharge();
		tradeAllowanceCharge.setChargeIndicator(false);
		tradeAllowanceCharge.setActualAmount(10000.00);
		tradeSettlement.setTradeAllowanceCharge(tradeAllowanceCharge);

		TradeMonetarySummation tradeMonetarySummation = new TradeMonetarySummation();
		tradeMonetarySummation.setLineTotalAmount(2000.00);
		tradeMonetarySummation.setGrandTotalAmount(1070.00);
		tradeMonetarySummation.setTaxTotalAmount(70.00);
		tradeSettlement.setTradeMonetarySummation(tradeMonetarySummation);
		invoice.setTradeSettlement(tradeSettlement);

		RunWithObj(invoice);
	}

	public static void RunWithObj(Invoice invoice) {
		TaxInvoiceCrossIndustryInvoiceType taxInvoiceCrossIndustryInvoiceType = new TaxInvoiceCrossIndustryInvoiceType();
		try {

			setDocDetail(invoice.getDocumentDetail(), taxInvoiceCrossIndustryInvoiceType);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private static void setDocDetail(DocumentDetail documentDetail,
			TaxInvoiceCrossIndustryInvoiceType taxInvoiceCrossIndustryInvoiceType)
			throws DatatypeConfigurationException {
		ExchangedDocumentContextType exchangeDocumentContext = new ExchangedDocumentContextType();
		DocumentContextParameterType documentContextParameter = new DocumentContextParameterType();

		Max35IDType guidelineID = new Max35IDType();
		guidelineID.setSchemeAgencyID(documentDetail.getSchemeAgencyID());
		guidelineID.setSchemeVersionID(documentDetail.getSchemeVersionID());
		guidelineID.setValue("ER3-2560");
		documentContextParameter.setID(guidelineID);
		exchangeDocumentContext.setGuidelineSpecifiedDocumentContextParameter(documentContextParameter);

		ExchangedDocumentType exchangeDocumentType = new ExchangedDocumentType();
		Max35TextType documentName = new Max35TextType();
		Max35IDType documentID = new Max35IDType();
		ThaiInvoiceDocumentCodeType invoiceDocumentCodeType = new ThaiInvoiceDocumentCodeType();

		documentID.setValue(documentDetail.getDocumentID());
		exchangeDocumentType.setID(documentID);
		documentName.setValue(documentDetail.getName());
		exchangeDocumentType.setName(documentName);
		invoiceDocumentCodeType
				.setValue(ThaiDocumentNameCodeInvoiceContentType.fromValue(documentDetail.getTypeCode()));
		exchangeDocumentType.setTypeCode(invoiceDocumentCodeType);
		exchangeDocumentType
				.setIssueDateTime(dateHelper.convert2XmlGregorianCalendar(documentDetail.getIssueDateTime()));
		exchangeDocumentType
				.setCreationDateTime(dateHelper.convert2XmlGregorianCalendar(documentDetail.getCreationDateTime()));
		// System.out.println("issue date: " + (String)
		// documentDetailObj.get("IssueDateTime"));
		Max256TextType purpose = new Max256TextType();
		purpose.setValue(documentDetail.getPurpose());
		exchangeDocumentType.setPurpose(purpose);

		Note note = documentDetail.getNote();
		NoteType noteType = new NoteType();
		Max500TextType subject = new Max500TextType();
		subject.setValue(note.getSubject());
		noteType.setSubject(subject);
		Max500TextType content = new Max500TextType();
		content.setValue(note.getContent());
		noteType.getContent().add(content);
		exchangeDocumentType.getIncludedNote().add(noteType);

		taxInvoiceCrossIndustryInvoiceType.setExchangedDocumentContext(exchangeDocumentContext);
		taxInvoiceCrossIndustryInvoiceType.setExchangedDocument(exchangeDocumentType);
	}

}
