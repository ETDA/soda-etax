package service;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import etda.uncefact.codelist.standard.thaidocumentnamecode_invoice._1.ThaiDocumentNameCodeInvoiceContentType;
import etda.uncefact.data.standard.qualifieddatatype._1.AmountType;
import etda.uncefact.data.standard.qualifieddatatype._1.CountryIDType;
import etda.uncefact.data.standard.qualifieddatatype._1.CurrencyCodeType;
import etda.uncefact.data.standard.qualifieddatatype._1.Max140TextType;
import etda.uncefact.data.standard.qualifieddatatype._1.Max16CodeType;
import etda.uncefact.data.standard.qualifieddatatype._1.Max16TextType;
import etda.uncefact.data.standard.qualifieddatatype._1.Max256IDType;
import etda.uncefact.data.standard.qualifieddatatype._1.Max256TextType;
import etda.uncefact.data.standard.qualifieddatatype._1.Max35IDType;
import etda.uncefact.data.standard.qualifieddatatype._1.Max35TextType;
import etda.uncefact.data.standard.qualifieddatatype._1.Max500TextType;
import etda.uncefact.data.standard.qualifieddatatype._1.QuantityType;
import etda.uncefact.data.standard.qualifieddatatype._1.TISI1099CityName;
import etda.uncefact.data.standard.qualifieddatatype._1.TISI1099CitySubDivisionName;
import etda.uncefact.data.standard.qualifieddatatype._1.TaxTypeCodeType;
import etda.uncefact.data.standard.qualifieddatatype._1.ThaiInvoiceDocumentCodeType;
import etda.uncefact.data.standard.taxinvoice_crossindustryinvoice._2.ObjectFactory;
import etda.uncefact.data.standard.taxinvoice_crossindustryinvoice._2.TaxInvoiceCrossIndustryInvoiceType;
import etda.uncefact.data.standard.taxinvoice_reusableaggregatebusinessinformationentity._2.DocumentContextParameterType;
import etda.uncefact.data.standard.taxinvoice_reusableaggregatebusinessinformationentity._2.DocumentLineDocumentType;
import etda.uncefact.data.standard.taxinvoice_reusableaggregatebusinessinformationentity._2.ExchangedDocumentContextType;
import etda.uncefact.data.standard.taxinvoice_reusableaggregatebusinessinformationentity._2.ExchangedDocumentType;
import etda.uncefact.data.standard.taxinvoice_reusableaggregatebusinessinformationentity._2.HeaderTradeAgreementType;
import etda.uncefact.data.standard.taxinvoice_reusableaggregatebusinessinformationentity._2.HeaderTradeDeliveryType;
import etda.uncefact.data.standard.taxinvoice_reusableaggregatebusinessinformationentity._2.HeaderTradeSettlementType;
import etda.uncefact.data.standard.taxinvoice_reusableaggregatebusinessinformationentity._2.LineTradeAgreementType;
import etda.uncefact.data.standard.taxinvoice_reusableaggregatebusinessinformationentity._2.LineTradeDeliveryType;
import etda.uncefact.data.standard.taxinvoice_reusableaggregatebusinessinformationentity._2.LineTradeSettlementType;
import etda.uncefact.data.standard.taxinvoice_reusableaggregatebusinessinformationentity._2.NoteType;
import etda.uncefact.data.standard.taxinvoice_reusableaggregatebusinessinformationentity._2.SupplyChainTradeLineItemType;
import etda.uncefact.data.standard.taxinvoice_reusableaggregatebusinessinformationentity._2.SupplyChainTradeTransactionType;
import etda.uncefact.data.standard.taxinvoice_reusableaggregatebusinessinformationentity._2.TaxRegistrationType;
import etda.uncefact.data.standard.taxinvoice_reusableaggregatebusinessinformationentity._2.TelephoneUniversalCommunicationType;
import etda.uncefact.data.standard.taxinvoice_reusableaggregatebusinessinformationentity._2.TradeAddressType;
import etda.uncefact.data.standard.taxinvoice_reusableaggregatebusinessinformationentity._2.TradeAllowanceChargeType;
import etda.uncefact.data.standard.taxinvoice_reusableaggregatebusinessinformationentity._2.TradeContactType;
import etda.uncefact.data.standard.taxinvoice_reusableaggregatebusinessinformationentity._2.TradePartyType;
import etda.uncefact.data.standard.taxinvoice_reusableaggregatebusinessinformationentity._2.TradePriceType;
import etda.uncefact.data.standard.taxinvoice_reusableaggregatebusinessinformationentity._2.TradeProductType;
import etda.uncefact.data.standard.taxinvoice_reusableaggregatebusinessinformationentity._2.TradeSettlementHeaderMonetarySummationType;
import etda.uncefact.data.standard.taxinvoice_reusableaggregatebusinessinformationentity._2.TradeSettlementMonetarySummationType;
import etda.uncefact.data.standard.taxinvoice_reusableaggregatebusinessinformationentity._2.TradeTaxType;
import etda.uncefact.data.standard.taxinvoice_reusableaggregatebusinessinformationentity._2.UniversalCommunicationType;
import un.unece.uncefact.codelist.standard.iso.iso3alphacurrencycode._2012_08_31.ISO3AlphaCurrencyCodeContentType;
import un.unece.uncefact.codelist.standard.unece.dutytaxfeetypecode.d14a.DutyTaxFeeTypeCodeContentType;
import un.unece.uncefact.identifierlist.standard.citynamefromtisi1099_2548.CityNameCodeContentType;
import un.unece.uncefact.identifierlist.standard.citysubdivisionnamefromtisi1099_2548.CitySubDivisionNameCodeContentType;
import un.unece.uncefact.identifierlist.standard.iso.isotwo_lettercountrycode.secondedition2006.ISOTwoletterCountryCodeContentType;
import util.DateHelper;
import util.JsonHelper;
import util.StringHelper;

public class EtaxInvoice {

	private static StringHelper stringHelper = new util.StringHelper();
	private static DateHelper dateHelper = new util.DateHelper();
	private static JSONObject jsonObj;
	private static HeaderTradeSettlementType headerTradeSettlementType;
	public TaxInvoiceCrossIndustryInvoiceType invoice;
	// private static SupplyChainTradeTransactionType
	// supplyChainTransactionType;
	private static DecimalFormat df = new DecimalFormat("#.##");

	public EtaxInvoice(String data) {
		try {
			jsonObj = new JsonHelper().parseString(data);
			invoice = new TaxInvoiceCrossIndustryInvoiceType();
			// Set Document Detail
			setDocDetail(invoice);
			// Add settlement type
			headerTradeSettlementType = getSettlement();

			// Add Seller and Buyer Detail
			HeaderTradeAgreementType headerTradeAgreementType = new HeaderTradeAgreementType();
			HeaderTradeDeliveryType headerTradeDeliveryType = new HeaderTradeDeliveryType();
			headerTradeAgreementType.setSellerTradeParty(setSeller(headerTradeDeliveryType));
			headerTradeAgreementType.setBuyerTradeParty(setBuyer(headerTradeDeliveryType));
			
			SupplyChainTradeTransactionType supplyChainTransactionType = new SupplyChainTradeTransactionType();
			supplyChainTransactionType.setApplicableHeaderTradeAgreement(headerTradeAgreementType);
			supplyChainTransactionType.setApplicableHeaderTradeDelivery(headerTradeDeliveryType);
			supplyChainTransactionType.setApplicableHeaderTradeSettlement(headerTradeSettlementType);
			addItems(supplyChainTransactionType);
			
			invoice.setSupplyChainTradeTransaction(supplyChainTransactionType);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static void setDocDetail(TaxInvoiceCrossIndustryInvoiceType invoice)
			throws ParseException, DatatypeConfigurationException, java.text.ParseException {
		JSONObject documentDetailObj = (JSONObject) jsonObj.get("DocumentDetail");

		ExchangedDocumentContextType exchangeDocumentContext = new ExchangedDocumentContextType();
		DocumentContextParameterType documentContextParameter = new DocumentContextParameterType();
		Max35IDType guidelineID = new Max35IDType();
		guidelineID.setSchemeAgencyID((String) documentDetailObj.get("SpecifiedCIDocument"));
		guidelineID.setSchemeVersionID("v2.0");
		guidelineID.setValue("ER3-2560");
		documentContextParameter.setID(guidelineID);
		exchangeDocumentContext.setGuidelineSpecifiedDocumentContextParameter(documentContextParameter);

		ExchangedDocumentType exchangeDocumentType = new ExchangedDocumentType();
		Max35TextType documentName = new Max35TextType();
		Max35IDType documentID = new Max35IDType();
		ThaiInvoiceDocumentCodeType invoiceDocumentCodeType = new ThaiInvoiceDocumentCodeType();

		documentID.setValue((String) documentDetailObj.get("ID"));
		exchangeDocumentType.setID(documentID);
		documentName.setValue((String) documentDetailObj.get("Name"));
		exchangeDocumentType.setName(documentName);
		invoiceDocumentCodeType
				.setValue(ThaiDocumentNameCodeInvoiceContentType.fromValue((String) documentDetailObj.get("TypeCode")));
		exchangeDocumentType.setTypeCode(invoiceDocumentCodeType);
		exchangeDocumentType
				.setIssueDateTime(dateHelper.Convert2XmlGregorianDate((String) documentDetailObj.get("IssueDateTime")));
		exchangeDocumentType.setCreationDateTime(
				dateHelper.Convert2XmlGregorianDate((String) documentDetailObj.get("CreationDateTime")));
		// System.out.println("issue date: " + (String)
		// documentDetailObj.get("IssueDateTime"));
		Max256TextType purpose = new Max256TextType();
		purpose.setValue((String) documentDetailObj.get("Purpose"));
		exchangeDocumentType.setPurpose(purpose);

		JSONObject noteObj = (JSONObject) documentDetailObj.get("Note");
		NoteType note = new NoteType();
		Max500TextType subject = new Max500TextType();
		subject.setValue((String) noteObj.get("Subject"));
		note.setSubject(subject);
		Max500TextType content = new Max500TextType();
		content.setValue((String) noteObj.get("Content"));
		note.getContent().add(content);
		exchangeDocumentType.getIncludedNote().add(note);

		invoice.setExchangedDocumentContext(exchangeDocumentContext);
		invoice.setExchangedDocument(exchangeDocumentType);
	}

	private static HeaderTradeSettlementType getSettlement() {
		HeaderTradeSettlementType headerTradeSettlementType = new HeaderTradeSettlementType();
		JSONObject monetaryObj = (JSONObject) jsonObj.get("ApplicableHeaderTradeSettlement");
		String invoiceCurrencyCode = (String) monetaryObj.get("InvoiceCurrencyCode");

		CurrencyCodeType currencyCodeType = new CurrencyCodeType();
		currencyCodeType.setValue(ISO3AlphaCurrencyCodeContentType.valueOf(invoiceCurrencyCode));
		JSONObject applicableCITradeTaxObj = (JSONObject) monetaryObj.get("ApplicableTradeTax");

		TradeTaxType tradeTaxType = new TradeTaxType();
		tradeTaxType.setCalculatedRate(new BigDecimal((String) applicableCITradeTaxObj.get("CalculatedRate")));
		TaxTypeCodeType taxTypeCodeType = new TaxTypeCodeType();
		taxTypeCodeType
				.setValue(DutyTaxFeeTypeCodeContentType.valueOf((String) applicableCITradeTaxObj.get("TypeCode")));
		AmountType basisAmountType = new AmountType();
		basisAmountType.setValue(new BigDecimal((String) applicableCITradeTaxObj.get("BasisAmount")));
		tradeTaxType.setTypeCode(taxTypeCodeType);
		tradeTaxType.getBasisAmount().add(basisAmountType);
		AmountType calculatedAmount = new AmountType();

		calculatedAmount.setValue(
				new BigDecimal(df.format((Double.parseDouble((String) applicableCITradeTaxObj.get("CalculatedRate"))
						* Double.parseDouble((String) applicableCITradeTaxObj.get("BasisAmount")) / 100))));
		tradeTaxType.getCalculatedAmount().add(calculatedAmount);

		JSONObject tradeAllowObj = (JSONObject) monetaryObj.get("SpecifiedTradeAllowanceCharge");
		TradeAllowanceChargeType tradeAllowType = new TradeAllowanceChargeType();
		tradeAllowType.setChargeIndicator(Boolean.valueOf((String) tradeAllowObj.get("ChargeIndicator")));
		AmountType actualAmountType = new AmountType();
		// TODO ตรวจสอบใหม่
		actualAmountType.setValue(new BigDecimal((String) tradeAllowObj.get("ActualAmount")));
		tradeAllowType.getActualAmount().add(actualAmountType);

		JSONObject settleMonetarySummationObj = (JSONObject) monetaryObj
				.get("SpecifiedTradeSettlementHeaderMonetarySummation");
		TradeSettlementHeaderMonetarySummationType settleMonetarySummationType = new TradeSettlementHeaderMonetarySummationType();
		AmountType lineTotalAmount = new AmountType();
		lineTotalAmount.setValue(new BigDecimal((String) settleMonetarySummationObj.get("LineTotalAmount")));
		settleMonetarySummationType.getLineTotalAmount().add(lineTotalAmount);
		AmountType taxTotalAmount = new AmountType();
		taxTotalAmount.setValue(new BigDecimal((String) settleMonetarySummationObj.get("TaxTotalAmount")));
		settleMonetarySummationType.getTaxTotalAmount().add(taxTotalAmount);
		AmountType grandTotalAmount = new AmountType();
		grandTotalAmount.setValue(new BigDecimal((String) settleMonetarySummationObj.get("GrandTotalAmount")));
		settleMonetarySummationType.getGrandTotalAmount().add(grandTotalAmount);

		// TODO หาวิธีคำนวณ & เช็คกับพี่ตั้น
		// settleMonetarySummationType.getAllowanceTotalAmount().add(actualAmountType);
		AmountType taxBasisTotalAmount = new AmountType();
		taxBasisTotalAmount.setValue(
				new BigDecimal(lineTotalAmount.getValue().floatValue() - actualAmountType.getValue().floatValue()));
		settleMonetarySummationType.getTaxBasisTotalAmount().add(taxBasisTotalAmount);

		headerTradeSettlementType.setInvoiceCurrencyCode(currencyCodeType);
		headerTradeSettlementType.getApplicableTradeTax().add(tradeTaxType);
		headerTradeSettlementType.getSpecifiedTradeAllowanceCharge().add(tradeAllowType);
		headerTradeSettlementType.setSpecifiedTradeSettlementHeaderMonetarySummation(settleMonetarySummationType);
		return headerTradeSettlementType;
	}

	private static void addItems(SupplyChainTradeTransactionType supplyChainTransactionType)
			throws ParseException, DatatypeConfigurationException {
		JSONArray productsArray = (JSONArray) jsonObj.get("IncludedSupplyChainTradeLineItem");
		for (Object obj : productsArray) {
			JSONObject productObj = (JSONObject) obj;

			addItem(supplyChainTransactionType, productObj);
		}
	}

	private static void addItem(SupplyChainTradeTransactionType supplyChainTransactionType, JSONObject productObj)
			throws ParseException, DatatypeConfigurationException {
		SupplyChainTradeLineItemType supplyChainTradeLineItemType = new SupplyChainTradeLineItemType();

		Max35IDType lineID = new Max35IDType();
		lineID.setValue((String) productObj.get("LineID"));
		DocumentLineDocumentType documentLineDocumentType = new DocumentLineDocumentType();
		documentLineDocumentType.setLineID(lineID);
		supplyChainTradeLineItemType.setAssociatedDocumentLineDocument(documentLineDocumentType);

		TradeProductType tradeProductType = new TradeProductType();
		tradeProductType.getName().add(stringHelper.ConvertMax256String((String) productObj.get("Name")));
		// TradeProductInstanceType tradeProductInstanceType = new
		// TradeProductInstanceType();
		// tradeProductInstanceType.setExpiryDateTime(dateHelper.Convert2XmlGregorianDate("1970-01-01T07:00:00.0"));
		// tradeProductType.getIndividualTradeProductInstance().add(tradeProductInstanceType);
		supplyChainTradeLineItemType.setSpecifiedTradeProduct(tradeProductType);

		LineTradeAgreementType lineTradeAgreementType = new LineTradeAgreementType();
		TradePriceType tradePriceType = new TradePriceType();
		AmountType chargeAmountType = new AmountType();
		chargeAmountType.setValue(new BigDecimal((String) productObj.get("ChargeAmount")));
		tradePriceType.getChargeAmount().add(chargeAmountType);
		lineTradeAgreementType.setGrossPriceProductTradePrice(tradePriceType);

		LineTradeDeliveryType lineTradeDeliveryType = new LineTradeDeliveryType();
		QuantityType quantityType = new QuantityType();
		quantityType.setUnitCode((String) productObj.get("UnitName"));
		quantityType.setValue(new BigDecimal((String) productObj.get("BilledQuantity")));

		lineTradeDeliveryType.setBilledQuantity(quantityType);

		LineTradeSettlementType lineTradeSettlement = new LineTradeSettlementType();
		TradeTaxType tradeTaxType = headerTradeSettlementType.getApplicableTradeTax().get(0);

		lineTradeSettlement.getApplicableTradeTax().add(tradeTaxType);
		TradeAllowanceChargeType tradeAllowanceCharge = new TradeAllowanceChargeType();
		tradeAllowanceCharge.setChargeIndicator(Boolean.valueOf((String) productObj.get("ChargeIndicator")));
		AmountType actualAmountType = new AmountType();
		actualAmountType.setValue(new BigDecimal((String) productObj.get("ActualAmount")));
		tradeAllowanceCharge.getActualAmount().add(actualAmountType);
		lineTradeSettlement.getSpecifiedTradeAllowanceCharge().add(tradeAllowanceCharge);

		Float netLineTotal = Float.valueOf((String) productObj.get("NetLineTotalAmount"));
		float taxTotal = (tradeTaxType.getCalculatedRate().floatValue() * netLineTotal) / 100;
		// System.out.println("Net Total: " + netLineTotal + ", Tax: " +
		// tradeTaxType.getCalculatedRate().floatValue()
		// + ", Tax Total: " + taxTotal);
		float netIncludeTax = taxTotal + netLineTotal;

		TradeSettlementMonetarySummationType tradeSettlementMonetarySummation = new TradeSettlementMonetarySummationType();
		AmountType taxTotalAmountType = new AmountType();

		taxTotalAmountType.setValue(new BigDecimal(df.format(taxTotal)));
		tradeSettlementMonetarySummation.getTaxTotalAmount().add(taxTotalAmountType);
		AmountType netLineTotalAmountType = new AmountType();
		netLineTotalAmountType.setValue(new BigDecimal(df.format(netLineTotal)));

		netLineTotalAmountType.setCurrencyID(headerTradeSettlementType.getInvoiceCurrencyCode().getValue());
		tradeSettlementMonetarySummation.getNetLineTotalAmount().add(netLineTotalAmountType);
		AmountType netIncludingTaxesLineAmount = new AmountType();
		netIncludingTaxesLineAmount.setValue(new BigDecimal(df.format(netIncludeTax)));
		netIncludingTaxesLineAmount.setCurrencyID(headerTradeSettlementType.getInvoiceCurrencyCode().getValue());
		tradeSettlementMonetarySummation.getNetIncludingTaxesLineTotalAmount().add(netIncludingTaxesLineAmount);
		lineTradeSettlement.setSpecifiedTradeSettlementLineMonetarySummation(tradeSettlementMonetarySummation);

		supplyChainTradeLineItemType.setSpecifiedLineTradeAgreement(lineTradeAgreementType);
		supplyChainTradeLineItemType.setSpecifiedLineTradeDelivery(lineTradeDeliveryType);
		supplyChainTradeLineItemType.setSpecifiedLineTradeSettlement(lineTradeSettlement);

		supplyChainTransactionType.getIncludedSupplyChainTradeLineItem().add(supplyChainTradeLineItemType);
	}

	private static TradePartyType setSeller(HeaderTradeDeliveryType headerTradeDeliveryType) {
		JSONObject sellerObj = (JSONObject) jsonObj.get("SellerTradeParty");

		TradePartyType sellerPartyType = new TradePartyType();
		Max256TextType sellerName = new Max256TextType();
		sellerName.setValue((String) sellerObj.get("Name"));
		sellerPartyType.setName(sellerName);
		TaxRegistrationType taxRegistration = new TaxRegistrationType();
		Max35IDType sellerTaxID = new Max35IDType();
		sellerTaxID.setValue((String) sellerObj.get("TaxID") + (String) sellerObj.get("BranchCode"));
		taxRegistration.setID(sellerTaxID);

		JSONObject addressObj = (JSONObject) sellerObj.get("PostalCITradeAddress");
		TradeAddressType tradeAddressType = new TradeAddressType();
		Max16CodeType postCode = new Max16CodeType();
		postCode.setValue((String) addressObj.get("PostcodeCode"));
		tradeAddressType.setPostcodeCode(postCode);

		Max256TextType lineOne = new Max256TextType();
		lineOne.setValue((String) addressObj.get("LineOne"));
		tradeAddressType.setLineOne(lineOne);

		Max256TextType lineTwo = new Max256TextType();
		lineTwo.setValue((String) addressObj.get("LineTwo"));
		tradeAddressType.setLineTwo(lineTwo);

		CountryIDType countryIDType = new CountryIDType();
		countryIDType.setValue(ISOTwoletterCountryCodeContentType.valueOf((String) addressObj.get("CountryID")));
		tradeAddressType.setCountryID(countryIDType);

		TISI1099CityName cityName = new TISI1099CityName();
		cityName.setValue(CityNameCodeContentType.fromValue((String) addressObj.get("CityID")));
		tradeAddressType.setCityName(cityName);

		TISI1099CitySubDivisionName citySubDivisionName = new TISI1099CitySubDivisionName();
		citySubDivisionName
				.setValue(CitySubDivisionNameCodeContentType.fromValue((String) addressObj.get("CitySubDivisionID")));
		tradeAddressType.setCitySubDivisionName(citySubDivisionName);

		TelephoneUniversalCommunicationType teleUniversalCommType = new TelephoneUniversalCommunicationType();
		teleUniversalCommType.setCompleteNumber((String) sellerObj.get("Telephone"));

		Max16TextType buildingNumber = new Max16TextType();
		String[] lineone = ((String) addressObj.get("LineOne")).split(" ", 2);
		buildingNumber.setValue(lineone[0]);
		tradeAddressType.setBuildingNumber(buildingNumber);

		Max35IDType countrySubDivionID = new Max35IDType();
		countrySubDivionID.setValue((String) addressObj.get("CountrySubDivisionID"));
		tradeAddressType.setCountrySubDivisionID(countrySubDivionID);

		UniversalCommunicationType universalCommType = new UniversalCommunicationType();
		Max256IDType uriId = new Max256IDType();
		uriId.setValue((String) sellerObj.get("URIID"));
		universalCommType.setURIID(uriId);

		TradeContactType contactType = new TradeContactType();
		contactType.setEmailURIUniversalCommunication(universalCommType);
		contactType.setTelephoneUniversalCommunication(teleUniversalCommType);

		sellerPartyType.setSpecifiedTaxRegistration(taxRegistration);
		sellerPartyType.getDefinedTradeContact().add(contactType);
		sellerPartyType.setPostalTradeAddress(tradeAddressType);
		TradePartyType tradePartyType = new TradePartyType();
		tradePartyType.setPostalTradeAddress(tradeAddressType);
		headerTradeDeliveryType.setShipFromTradeParty(tradePartyType);
		//headerTradeAgreementType.setSellerTradeParty(sellerPartyType);
		return sellerPartyType;
	}

	private static TradePartyType setBuyer(HeaderTradeDeliveryType headerTradeDeliveryType) {
		JSONObject buyerObj = (JSONObject) jsonObj.get("BuyerTradeParty");
		JSONObject addressObj = (JSONObject) buyerObj.get("PostalTradeAddress");

		TradePartyType buyerPartyType = new TradePartyType();
		Max256TextType buyerName = new Max256TextType();
		buyerName.setValue((String) buyerObj.get("Name"));
		buyerPartyType.setName(buyerName);

		TaxRegistrationType taxRegistration = new TaxRegistrationType();
		Max35IDType buyerTaxID = new Max35IDType();
		buyerTaxID.setValue((String) buyerObj.get("TaxID") + (String) buyerObj.get("BranchCode"));
		taxRegistration.setID(buyerTaxID);

		TradeAddressType tradeAddressType = new TradeAddressType();
		Max16CodeType postCode = new Max16CodeType();
		postCode.setValue((String) addressObj.get("PostcodeCode"));
		tradeAddressType.setPostcodeCode(postCode);

		Max256TextType lineOne = new Max256TextType();
		lineOne.setValue((String) addressObj.get("LineOne"));
		tradeAddressType.setLineOne(lineOne);

		Max256TextType lineTwo = new Max256TextType();
		lineTwo.setValue((String) addressObj.get("LineTwo"));
		tradeAddressType.setLineTwo(lineTwo);

		CountryIDType countryIDType = new CountryIDType();
		countryIDType.setValue(ISOTwoletterCountryCodeContentType.valueOf((String) addressObj.get("CountryID")));
		tradeAddressType.setCountryID(countryIDType);

		TISI1099CityName cityName = new TISI1099CityName();
		cityName.setValue(CityNameCodeContentType.fromValue((String) addressObj.get("CityID")));
		tradeAddressType.setCityName(cityName);

		TISI1099CitySubDivisionName citySubDivisionName = new TISI1099CitySubDivisionName();
		citySubDivisionName
				.setValue(CitySubDivisionNameCodeContentType.fromValue((String) addressObj.get("CitySubDivisionID")));
		tradeAddressType.setCitySubDivisionName(citySubDivisionName);

		TelephoneUniversalCommunicationType teleUniversalCommType = new TelephoneUniversalCommunicationType();
		teleUniversalCommType.setCompleteNumber((String) buyerObj.get("Telephone"));

		Max16TextType buildingNumber = new Max16TextType();
		String[] lineone = ((String) addressObj.get("LineOne")).split(" ", 2);
		buildingNumber.setValue(lineone[0]);
		tradeAddressType.setBuildingNumber(buildingNumber);

		Max35IDType countrySubDivionID = new Max35IDType();
		countrySubDivionID.setValue((String) addressObj.get("CountrySubDivisionID"));
		tradeAddressType.setCountrySubDivisionID(countrySubDivionID);

		UniversalCommunicationType universalCommType = new UniversalCommunicationType();
		Max256IDType uriId = new Max256IDType();
		uriId.setValue((String) buyerObj.get("URIID"));
		universalCommType.setURIID(uriId);

		TradeContactType contactType = new TradeContactType();
		contactType.setEmailURIUniversalCommunication(universalCommType);
		contactType.setTelephoneUniversalCommunication(teleUniversalCommType);
		Max140TextType personName = new Max140TextType();
		personName.setValue((String) buyerObj.get("PersonName"));
		contactType.setPersonName(personName);

		buyerPartyType.getDefinedTradeContact().add(contactType);
		buyerPartyType.setSpecifiedTaxRegistration(taxRegistration);
		buyerPartyType.setPostalTradeAddress(tradeAddressType);
		TradePartyType tradePartyType = new TradePartyType();
		tradePartyType.setPostalTradeAddress(tradeAddressType);
		headerTradeDeliveryType.setShipToTradeParty(tradePartyType);
		//headerTradeAgreementType.setBuyerTradeParty(buyerPartyType);
		return buyerPartyType;
	}
	
	public String getXML() throws JAXBException {
		StringWriter sw = new StringWriter();
		JAXBContext jaxbContext;
		try {

			jaxbContext = JAXBContext.newInstance(TaxInvoiceCrossIndustryInvoiceType.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			ObjectFactory objFactory = new ObjectFactory();
			JAXBElement<TaxInvoiceCrossIndustryInvoiceType> taxinvoice = objFactory.createTaxInvoiceCrossIndustryInvoice(invoice);
			jaxbMarshaller.marshal(taxinvoice, sw);

			String xmlString = sw.toString();
			//PDFACreator creator = new PDFACreator();
			//creator.convert2PDF(invoice, xmlString);
			try (PrintWriter out = new PrintWriter("target/data.xml")) {
				out.println(xmlString);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			System.out.println(xmlString);
			return xmlString;
		} catch (JAXBException e) {
			e.printStackTrace();
			throw(e);
		}
	}

}
