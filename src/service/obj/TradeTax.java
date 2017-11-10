package service.obj;

public class TradeTax {

    public TradeTax(){

    }

    private Double calculatedRate;
    private String typeCode;
    private Double basisAmount;
	/**
	 * @return the calculatedRate
	 */
	public Double getCalculatedRate() {
		return calculatedRate;
	}
	/**
	 * @param calculatedRate the calculatedRate to set
	 */
	public void setCalculatedRate(Double calculatedRate) {
		this.calculatedRate = calculatedRate;
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
	/**
	 * @return the basisAmount
	 */
	public Double getBasisAmount() {
		return basisAmount;
	}
	/**
	 * @param basisAmount the basisAmount to set
	 */
	public void setBasisAmount(Double basisAmount) {
		this.basisAmount = basisAmount;
	}
}