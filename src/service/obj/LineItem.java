package service.obj;

public class LineItem {

	public LineItem() {
		
	}

	private String lineID;
	private String productID;
	private String name;
	private double chargeAmount;
	private double actualAmount;
	private int billedQuantity;
	private String unicode;
	private String unitname;
	private boolean chargeIndicator;	
	private double netlineTotalAmount;
	/**
	 * @return the lineID
	 */
	public String getLineID() {
		return lineID;
	}
	/**
	 * @param lineID the lineID to set
	 */
	public void setLineID(String lineID) {
		this.lineID = lineID;
	}
	/**
	 * @return the productID
	 */
	public String getProductID() {
		return productID;
	}
	/**
	 * @param productID the productID to set
	 */
	public void setProductID(String productID) {
		this.productID = productID;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the chargeAmount
	 */
	public double getChargeAmount() {
		return chargeAmount;
	}
	/**
	 * @param chargeAmount the chargeAmount to set
	 */
	public void setChargeAmount(double chargeAmount) {
		this.chargeAmount = chargeAmount;
	}
	/**
	 * @return the actualAmount
	 */
	public double getActualAmount() {
		return actualAmount;
	}
	/**
	 * @param actualAmount the actualAmount to set
	 */
	public void setActualAmount(double actualAmount) {
		this.actualAmount = actualAmount;
	}
	/**
	 * @return the billedQuantity
	 */
	public int getBilledQuantity() {
		return billedQuantity;
	}
	/**
	 * @param billedQuantity the billedQuantity to set
	 */
	public void setBilledQuantity(int billedQuantity) {
		this.billedQuantity = billedQuantity;
	}
	/**
	 * @return the unicode
	 */
	public String getUnicode() {
		return unicode;
	}
	/**
	 * @param unicode the unicode to set
	 */
	public void setUnicode(String unicode) {
		this.unicode = unicode;
	}
	/**
	 * @return the unitname
	 */
	public String getUnitname() {
		return unitname;
	}
	/**
	 * @param unitname the unitname to set
	 */
	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}
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
	 * @return the netlineTotalAmount
	 */
	public double getNetlineTotalAmount() {
		return netlineTotalAmount;
	}
	/**
	 * @param netlineTotalAmount the netlineTotalAmount to set
	 */
	public void setNetlineTotalAmount(double netlineTotalAmount) {
		this.netlineTotalAmount = netlineTotalAmount;
	}
}
