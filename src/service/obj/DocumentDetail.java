package service.obj;

import java.util.Date;

public class DocumentDetail {

	public DocumentDetail() {
		
	}

	private String schemeAgencyID;
	private String schemeVersionID;
	private String documentID;
	private String name;
	private String typeCode;
	private Date issueDateTime;
	private Date creationDateTime;
	private String purpose;
	private String note;
	/**
	 * @return the schemeAgencyID
	 */
	public String getSchemeAgencyID() {
		return schemeAgencyID;
	}
	/**
	 * @param schemeAgencyID the schemeAgencyID to set
	 */
	public void setSchemeAgencyID(String schemeAgencyID) {
		this.schemeAgencyID = schemeAgencyID;
	}
	/**
	 * @return the schemeVersionID
	 */
	public String getSchemeVersionID() {
		return schemeVersionID;
	}
	/**
	 * @param schemeVersionID the schemeVersionID to set
	 */
	public void setSchemeVersionID(String schemeVersionID) {
		this.schemeVersionID = schemeVersionID;
	}
	/**
	 * @return the documentID
	 */
	public String getDocumentID() {
		return documentID;
	}
	/**
	 * @param documentID the documentID to set
	 */
	public void setDocumentID(String documentID) {
		this.documentID = documentID;
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
	 * @return the creationDateTime
	 */
	public Date getCreationDateTime() {
		return creationDateTime;
	}
	/**
	 * @param creationDateTime the creationDateTime to set
	 */
	public void setCreationDateTime(Date creationDateTime) {
		this.creationDateTime = creationDateTime;
	}
	/**
	 * @return the purpose
	 */
	public String getPurpose() {
		return purpose;
	}
	/**
	 * @param purpose the purpose to set
	 */
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}
	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}
}
