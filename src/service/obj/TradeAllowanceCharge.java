package service.obj;

public class TradeAllowanceCharge {

    public TradeAllowanceCharge(){

    }

    private boolean chargeIndicator;
    private Double actualAmount;
    private String reasonCode;
    private String reason;
    private String typeCode;
	/**
	 * @return the chargeIndicator
	 */
	public boolean isChargeIndicator() {
		return chargeIndicator;
	}
	/**
	 * @param chargeIndicator the chargeIndicator to set
	 */
	public void setChargeIndicator(boolean chargeIndicator) {
		this.chargeIndicator = chargeIndicator;
	}
	/**
	 * @return the actualAmount
	 */
	public Double getActualAmount() {
		return actualAmount;
	}
	/**
	 * @param actualAmount the actualAmount to set
	 */
	public void setActualAmount(Double actualAmount) {
		this.actualAmount = actualAmount;
	}
	/**
	 * @return the reasonCode
	 */
	public String getReasonCode() {
		return reasonCode;
	}
	/**
	 * @param reasonCode the reasonCode to set
	 */
	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}
	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}
	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	/**
	 * @return the typeCode
	 */
	public String getTypeCode() {
		return typeCode;
	}
	/**
	 * @param typeCode the typeCode to set
	 */
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
}