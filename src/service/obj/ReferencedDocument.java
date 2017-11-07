package service.obj;

import java.util.Date;

public class ReferencedDocument {

	public ReferencedDocument() {
		
	}

	private String issuerAssignedID;
	private Date issueDateTime;
	private String referenceTypeCode;
	/**
	 * @return the issuerAssignedID
	 */
	public String getIssuerAssignedID() {
		return issuerAssignedID;
	}
	/**
	 * @param issuerAssignedID the issuerAssignedID to set
	 */
	public void setIssuerAssignedID(String issuerAssignedID) {
		this.issuerAssignedID = issuerAssignedID;
	}
	/**
	 * @return the issueDateTime
	 */
	public Date getIssueDateTime() {
		return issueDateTime;
	}
	/**
	 * @param issueDateTime the issueDateTime to set
	 */
	public void setIssueDateTime(Date issueDateTime) {
		this.issueDateTime = issueDateTime;
	}
	/**
	 * @return the referenceTypeCode
	 */
	public String getReferenceTypeCode() {
		return referenceTypeCode;
	}
	/**
	 * @param referenceTypeCode the referenceTypeCode to set
	 */
	public void setReferenceTypeCode(String referenceTypeCode) {
		this.referenceTypeCode = referenceTypeCode;
	}

}
