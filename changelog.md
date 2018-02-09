# Changelog

##2018-02-09
- create class for Debitcreditnote invoice class
- Add new json sample for DebitCreditnote 

class Debitcreditnote
 - add TradeSettlementMonetaryHeaderSummationType.Debitcreditnote.OriginalInformationAmount
 - add TradeSettlementMonetaryHeaderSummationType.Debitcreditnote.DifferenceInformationAmount
 - add PurposeCode
 - add Purpose
 - add reference document part > change AdditionalReferencedDocument from list to single 
 - add scheme ID
 - add Namespace ram at package-info of dbncrn_reusableaggregatiteg
 - add Namespace rsm at package-info of dbncrn_cross industry invoice
 - change Debitcreditnote.Tradepartytype.name's type back to Max256TextType from string
 - change Debitcreditnote.ExchangedDocumentType.Purpose's type back to Max256TextType from string
 - change TradeAddressType of CountryID,CityName,CitySubDivisionName,CountrySubDivisionID to Max35IDType (bypass validate porpose)
 - change UniversalCommunicationType to EmailUniversalCommunicationType (UniversalCommunicationType) not exist in dbncrn class
 - change TradeSettlementMonetarySummationType to LineSettlementMonetarySummationType class (but they has same parameter)
 - change Debitcreditnote.ReferencedDocumentType from XMLgeorgian into string 
 - edit UnitCode
 - edit Unitname
 - edit Namespace DebitCreditNoteCrossIndustryInvoiceType

class Taxinvoice
 - change TradeAddressType of CountryID,CityName,CitySubDivisionName,CountrySubDivisionID to Max35IDType (bypass validate porpose)
 - change TaxRegistrationType to SpecifyTaxRegistrationType 
 - add reference document part > change AdditionalReferencedDocument from list to single 
 - add scheme ID
 - edit UnitCode
 - edit Unitname


