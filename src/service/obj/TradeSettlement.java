package service.obj;

public class TradeSettlement {

	public TradeSettlement() {
		
	}

	private String invoiceCurrencyCode;
	private TradeTax tradeTax;
	private TradeAllowanceCharge tradeAllowanceCharge;
	private TradeMonetarySummation tradeMonetarySummation;
	/**
	 * @return the invoiceCurrencyCode
	 */
	public String getInvoiceCurrencyCode() {
		return invoiceCurrencyCode;
	}
	/**
	 * @param invoiceCurrencyCode the invoiceCurrencyCode to set
	 */
	public void setInvoiceCurrencyCode(String invoiceCurrencyCode) {
		this.invoiceCurrencyCode = invoiceCurrencyCode;
	}
	/**
	 * @return the tradeTax
	 */
	public TradeTax getTradeTax() {
		return tradeTax;
	}
	/**
	 * @param tradeTax the tradeTax to set
	 */
	public void setTradeTax(TradeTax tradeTax) {
		this.tradeTax = tradeTax;
	}
	/**
	 * @return the tradeAllowanceCharge
	 */
	public TradeAllowanceCharge getTradeAllowanceCharge() {
		return tradeAllowanceCharge;
	}
	/**
	 * @param tradeAllowanceCharge the tradeAllowanceCharge to set
	 */
	public void setTradeAllowanceCharge(TradeAllowanceCharge tradeAllowanceCharge) {
		this.tradeAllowanceCharge = tradeAllowanceCharge;
	}
	/**
	 * @return the tradeMonetarySummation
	 */
	public TradeMonetarySummation getTradeMonetarySummation() {
		return tradeMonetarySummation;
	}
	/**
	 * @param tradeMonetarySummation the tradeMonetarySummation to set
	 */
	public void setTradeMonetarySummation(TradeMonetarySummation tradeMonetarySummation) {
		this.tradeMonetarySummation = tradeMonetarySummation;
	}
}
