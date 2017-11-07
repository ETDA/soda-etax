package service.obj;

import java.util.List;

public class Invoice {

	public Invoice() {
		
	}

	private Buyer buyer;
	private Seller seller;
	private List<LineItem> lineItems;
	private DocumentDetail documentDetail;
	private List<ReferencedDocument> referencedDocuments;
	private TradeSettlement tradeSettlement;
	/**
	 * @return the buyer
	 */
	public Buyer getBuyer() {
		return buyer;
	}
	/**
	 * @param buyer the buyer to set
	 */
	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}
	/**
	 * @return the seller
	 */
	public Seller getSeller() {
		return seller;
	}
	/**
	 * @param seller the seller to set
	 */
	public void setSeller(Seller seller) {
		this.seller = seller;
	}
	/**
	 * @return the lineItems
	 */
	public List<LineItem> getLineItems() {
		return lineItems;
	}
	/**
	 * @param lineItems the lineItems to set
	 */
	public void setLineItems(List<LineItem> lineItems) {
		this.lineItems = lineItems;
	}
	/**
	 * @return the documentDetail
	 */
	public DocumentDetail getDocumentDetail() {
		return documentDetail;
	}
	/**
	 * @param documentDetail the documentDetail to set
	 */
	public void setDocumentDetail(DocumentDetail documentDetail) {
		this.documentDetail = documentDetail;
	}
	/**
	 * @return the tradeSettlement
	 */
	public TradeSettlement getTradeSettlement() {
		return tradeSettlement;
	}
	/**
	 * @param tradeSettlement the tradeSettlement to set
	 */
	public void setTradeSettlement(TradeSettlement tradeSettlement) {
		this.tradeSettlement = tradeSettlement;
	}
	/**
	 * @return the referencedDocuments
	 */
	public List<ReferencedDocument> getReferencedDocuments() {
		return referencedDocuments;
	}
	/**
	 * @param referencedDocuments the referencedDocuments to set
	 */
	public void setReferencedDocuments(List<ReferencedDocument> referencedDocuments) {
		this.referencedDocuments = referencedDocuments;
	}

}
