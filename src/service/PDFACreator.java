package service;

import java.awt.geom.Point2D;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.jempbox.xmp.XMPMetadata;
import org.apache.jempbox.xmp.XMPSchema;
import org.apache.jempbox.xmp.XMPSchemaBasic;
import org.apache.jempbox.xmp.XMPSchemaPDF;
import org.apache.jempbox.xmp.pdfa.XMPSchemaPDFAId;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDDocumentNameDictionary;
import org.apache.pdfbox.pdmodel.PDEmbeddedFilesNameTreeNode;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDMetadata;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.common.filespecification.PDComplexFileSpecification;
import org.apache.pdfbox.pdmodel.common.filespecification.PDEmbeddedFile;
import org.apache.pdfbox.pdmodel.documentinterchange.logicalstructure.PDMarkInfo;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.color.PDOutputIntent;
import org.apache.pdfbox.util.Matrix;
import org.vandeseer.pdfbox.easytable.Cell;
import org.vandeseer.pdfbox.easytable.Cell.HorizontalAlignment;
import org.vandeseer.pdfbox.easytable.Row.RowBuilder;
import org.vandeseer.pdfbox.easytable.Table.TableBuilder;
import org.vandeseer.pdfbox.easytable.TableDrawer;
import org.w3c.dom.Element;

import etda.uncefact.data.standard.taxinvoice_crossindustryinvoice._2.TaxInvoiceCrossIndustryInvoiceType;
import etda.uncefact.data.standard.taxinvoice_reusableaggregatebusinessinformationentity._2.ExchangedDocumentType;
import etda.uncefact.data.standard.taxinvoice_reusableaggregatebusinessinformationentity._2.HeaderTradeSettlementType;
import etda.uncefact.data.standard.taxinvoice_reusableaggregatebusinessinformationentity._2.SupplyChainTradeLineItemType;
import etda.uncefact.data.standard.taxinvoice_reusableaggregatebusinessinformationentity._2.TradePartyType;
import util.PdfHelper;

public class PDFACreator {

	private String BASE_FOLDER = "E:/test/";
	private String FILENAME = "test.pdf";
	private String FONT_FILE = "THSarabunNew.ttf";
	private String BOLD_FONT_FILE = "THSarabunNew Bold.ttf";
	private DecimalFormat df = new DecimalFormat("#.##");
	private PDType0Font font;
	private PDType0Font font_bold;
	private int fontSize = 14;

	public PDFACreator() {

	}

	public void convert2PDF(TaxInvoiceCrossIndustryInvoiceType invoice, String xml, String outputPath) {
		try (PDDocument doc = new PDDocument()) {
			PDPage page = new PDPage();
			doc.addPage(page);

			font = PDType0Font.load(doc, new File(BASE_FOLDER + FONT_FILE));
			font_bold = PDType0Font.load(doc, new File(BASE_FOLDER + BOLD_FONT_FILE));

			try (PDPageContentStream content = new PDPageContentStream(doc, page)) {
				ExchangedDocumentType document = invoice.getExchangedDocument();

				String title = document.getName().getValue().toString();
				Point2D.Float centerPage = new Point2D.Float(0, 0);
				addCenteredText(title, font_bold, 25, content, page, centerPage);

				float offset = 10;
				// int fontSize = 12;
				content.setLeading(12 * 1.2);
				Point2D.Float column1 = new Point2D.Float(150, 680);
				Point2D.Float column2 = new Point2D.Float(440, 720);
				Point2D.Float footer1 = new Point2D.Float(150, 200);
				Point2D.Float footer2 = new Point2D.Float(440, 200);

				content.beginText();
				content.setFont(font, fontSize);

				TradePartyType buyer = invoice.getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement()
						.getBuyerTradeParty();
				content.setTextMatrix(Matrix.getTranslateInstance(column1.x, column1.y));
				rightText("ผู้ออกใบกำกับภาษี", content);
				content.newLineAtOffset(+offset, 0);
				content.showText(buyer.getName().getValue().toString());
				content.newLine();
				content.showText(buyer.getPostalTradeAddress().getLineOne().getValue().toString());
				content.newLine();
				content.showText(buyer.getPostalTradeAddress().getLineTwo().getValue().toString()
						+ buyer.getPostalTradeAddress().getPostcodeCode().getValue().toString());
				content.newLine();
				content.newLine();
				content.newLineAtOffset(-offset, 0);
				rightText("เลขประจำตัวผู้เสียภาษีอากร", content);
				content.newLineAtOffset(+offset, 0);
				content.showText(buyer.getSpecifiedTaxRegistration().getID().getValue().toString());
				content.newLine();
				content.newLineAtOffset(-offset, 0);
				rightText("สำนักงานใหญ่/เลขที่สาขา", content);
				content.newLineAtOffset(+offset, 0);
				// TODO ยังเป็น hard code
				content.showText("สำนักงาน(00000)");
				content.newLine();
				content.newLineAtOffset(-offset, 0);
				rightText("อีเมล", content);
				content.newLineAtOffset(+offset, 0);
				content.showText(buyer.getDefinedTradeContact().get(0).getEmailURIUniversalCommunication().getURIID()
						.getValue().toString());
				content.newLine();
				content.newLineAtOffset(-offset, 0);
				rightText("เบอร์โทรศัพท์", content);
				content.newLineAtOffset(+offset, 0);
				content.showText(
						buyer.getDefinedTradeContact().get(0).getTelephoneUniversalCommunication().getCompleteNumber());

				content.setTextMatrix(Matrix.getTranslateInstance(column2.x, column2.y));
				rightText("เลขที่", content);
				content.newLineAtOffset(+offset, 0);
				content.showText(document.getID().getValue().toString());
				content.newLine();
				content.newLineAtOffset(-offset, 0);
				rightText("วันที่ออกเอกสาร", content);
				content.newLineAtOffset(+offset, 0);
				content.showText("6 มกราคม 2560");

				TradePartyType seller = invoice.getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement()
						.getSellerTradeParty();
				content.setTextMatrix(Matrix.getTranslateInstance(column2.x, column1.y));
				rightText("ผู้ซื้อ/ผู้บริการ", content);
				content.newLineAtOffset(+offset, 0);
				content.showText(seller.getName().getValue().toString());
				content.newLine();
				content.showText(seller.getPostalTradeAddress().getLineOne().getValue().toString());
				content.newLine();
				content.showText(seller.getPostalTradeAddress().getLineTwo().getValue().toString()
						+ seller.getPostalTradeAddress().getPostcodeCode().getValue().toString());
				content.newLine();
				content.newLine();
				content.newLineAtOffset(-offset, 0);
				rightText("เลขที่ประจำตัวผู้เสียภาษีอากร", content);
				content.newLineAtOffset(+offset, 0);
				content.showText(seller.getSpecifiedTaxRegistration().getID().getValue().toString());
				content.newLine();
				content.newLineAtOffset(-offset, 0);
				rightText("สำนักงานใหญ่/เลขที่สาขา", content);
				content.newLineAtOffset(+offset, 0);
				// TODO ยังเป็น hard code
				content.showText("สาขา (00234");
				content.newLine();
				content.newLineAtOffset(-offset, 0);
				rightText("อีเมล", content);
				content.newLineAtOffset(+offset, 0);
				content.showText(buyer.getDefinedTradeContact().get(0).getEmailURIUniversalCommunication().getURIID()
						.getValue().toString());
				content.newLine();
				content.newLineAtOffset(-offset, 0);
				rightText("ผู้ติดต่อ", content);
				content.newLineAtOffset(+offset, 0);
				content.showText(buyer.getDefinedTradeContact().get(0).getPersonName().getValue().toString());
				content.newLine();
				content.newLineAtOffset(-offset, 0);
				rightText("เบอร์โทรศัพท์", content);
				content.newLineAtOffset(+offset, 0);
				content.showText(
						buyer.getDefinedTradeContact().get(0).getTelephoneUniversalCommunication().getCompleteNumber());
				content.endText();

				// Table of items
				addTable(content, page, invoice.getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem());

				HeaderTradeSettlementType tradeSettlement = invoice.getSupplyChainTradeTransaction()
						.getApplicableHeaderTradeSettlement();

				// Footer Monetary
				content.beginText();
				content.setTextMatrix(Matrix.getTranslateInstance(footer2.x, footer2.y));
				rightText("มูลค่าสินค้า/บริการ", content);
				content.newLineAtOffset(+offset, 0);
				content.showText(
						df.format(tradeSettlement.getApplicableTradeTax().get(0).getBasisAmount().get(0).getValue()));
				content.newLineAtOffset(+offset * 3, 0);
				content.showText("บาท");
				content.newLine();
				content.newLineAtOffset(-offset * 4, 0);
				rightText("ส่วนลด", content);
				content.newLineAtOffset(+offset, 0);
				content.showText(df.format(
						tradeSettlement.getSpecifiedTradeAllowanceCharge().get(0).getActualAmount().get(0).getValue()));
				content.newLineAtOffset(+offset * 3, 0);
				content.showText("บาท");
				content.newLine();
				content.newLineAtOffset(-offset * 4, 0);
				rightText("ค่าบริการ", content);
				content.newLineAtOffset(+offset, 0);
				content.showText(df.format(0.00));
				content.newLineAtOffset(+offset * 3, 0);
				content.showText("บาท");
				content.newLine();
				content.newLineAtOffset(-offset * 4, 0);
				rightText("จำนวนเงินรวมสุทธิ", content);
				content.newLineAtOffset(+offset, 0);
				content.showText(df.format(tradeSettlement.getSpecifiedTradeSettlementHeaderMonetarySummation()
						.getLineTotalAmount().get(0).getValue()));
				content.newLineAtOffset(+offset * 3, 0);
				content.showText("บาท");
				content.newLine();
				content.newLineAtOffset(-offset * 4, 0);
				BigDecimal taxRate = tradeSettlement.getApplicableTradeTax().get(0).getCalculatedRate();
				rightText(String.format("ภาษีมูลค่าเพิ่ม %s %%", taxRate.toString()), content);
				content.newLineAtOffset(+offset, 0);
				content.showText(df.format(tradeSettlement.getSpecifiedTradeSettlementHeaderMonetarySummation()
						.getTaxTotalAmount().get(0).getValue()));
				content.newLineAtOffset(+offset * 3, 0);
				content.showText("บาท");
				content.newLine();
				content.newLineAtOffset(-offset * 4, 0);
				rightText("มูลค่าสุทธิ", content);
				content.newLineAtOffset(+offset, 0);
				content.showText(df.format(tradeSettlement.getSpecifiedTradeSettlementHeaderMonetarySummation()
						.getGrandTotalAmount().get(0).getValue()));
				content.newLineAtOffset(+offset * 3, 0);
				content.showText("บาท");

				// Footer Reason
				content.setTextMatrix(Matrix.getTranslateInstance(footer1.x, footer1.y));
				rightText("จำนวนเงินรวมทั้งสิ้น(ตัวอักษร)", content);
				content.newLine();
				rightText("", content);
				content.newLine();
				content.newLine();

				content.endText();
				content.saveGraphicsState();
				content.close();

			}

			if (!font.isEmbedded()) {
				throw new IllegalStateException("PDF/A compliance requires that all fonts used for"
						+ " text rendering in rendering modes other than rendering mode 3 are embedded.");
			}

			makeA3compliant(doc, xml);
			doc.setVersion(1.7f);

			// doc.save(BASE_FOLDER + FILENAME);
			doc.save(outputPath);
			doc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	void rightText(String str, PDPageContentStream content) throws IOException {
		float text_width = (font.getStringWidth(str) / 1000.0f) * fontSize;
		// content.moveTextPositionByAmount(-text_width, 0);
		content.moveTextPositionByAmount(-text_width, 0);
		content.showText(str);
		content.moveTextPositionByAmount(text_width, 0);
	}

	void addTable(PDPageContentStream content, PDPage page, List<SupplyChainTradeLineItemType> lineItems)
			throws IOException {
		// Define the table structure first
		TableBuilder tableBuilder = new TableBuilder().addColumnOfWidth(50).addColumnOfWidth(150).addColumnOfWidth(50)
				.addColumnOfWidth(50).addColumnOfWidth(80).addColumnOfWidth(50).addColumnOfWidth(100)
				.setFontSize(fontSize).setFont(font);

		// Header ...
		tableBuilder.addRow(new RowBuilder()
				.add(Cell.withText("ลำดับที่").setHorizontalAlignment(HorizontalAlignment.CENTER).withAllBorders())
				.add(Cell.withText("รายการ").setHorizontalAlignment(HorizontalAlignment.CENTER).withAllBorders())
				.add(Cell.withText("จำนวน").setHorizontalAlignment(HorizontalAlignment.CENTER).withAllBorders())
				.add(Cell.withText("หน่วย").setHorizontalAlignment(HorizontalAlignment.CENTER).withAllBorders())
				.add(Cell.withText("ราคาต่อหน่วย").setHorizontalAlignment(HorizontalAlignment.CENTER).withAllBorders())
				.add(Cell.withText("ส่วนลด").setHorizontalAlignment(HorizontalAlignment.CENTER).withAllBorders())
				.add(Cell.withText("จำนวนเงิน(ไม่รวม VAT)").setHorizontalAlignment(HorizontalAlignment.CENTER)
						.withAllBorders())
				.build());

		// ... and some cells
		int i = 1;
		for (SupplyChainTradeLineItemType lineItem : lineItems) {
			tableBuilder
					.addRow(new RowBuilder()
							.add(Cell.withText(i++).setHorizontalAlignment(HorizontalAlignment.CENTER)
									.setBorderWidthLeft(1).setBorderWidthBottom(i == lineItems.size() ? 0 : 1))
							.add(Cell
									.withText(
											lineItem.getSpecifiedTradeProduct().getName().get(0).getValue().toString())
									.setHorizontalAlignment(HorizontalAlignment.LEFT).setBorderWidthLeft(1)
									.setBorderWidthBottom(i == lineItems.size() ? 0 : 1))
							.add(Cell
									.withText(lineItem.getSpecifiedLineTradeDelivery().getBilledQuantity().getValue()
											.toString())
									.setHorizontalAlignment(HorizontalAlignment.CENTER).setBorderWidthLeft(1)
									.setBorderWidthBottom(i == lineItems.size() ? 0 : 1))
							.add(Cell
									.withText(
											lineItem.getSpecifiedLineTradeDelivery().getBilledQuantity().getUnitCode())
									.setHorizontalAlignment(HorizontalAlignment.CENTER).setBorderWidthLeft(1)
									.setBorderWidthBottom(i == lineItems.size() ? 0 : 1))
							.add(Cell
									.withText(lineItem.getSpecifiedLineTradeAgreement().getGrossPriceProductTradePrice()
											.getChargeAmount().get(0).getValue().toString())
									.setHorizontalAlignment(HorizontalAlignment.CENTER).setBorderWidthLeft(1)
									.setBorderWidthBottom(i == lineItems.size() ? 0 : 1))
							.add(Cell
									.withText(lineItem.getSpecifiedLineTradeSettlement()
											.getSpecifiedTradeAllowanceCharge().get(0).getActualAmount().get(0)
											.getValue().toString())
									.setHorizontalAlignment(HorizontalAlignment.CENTER).setBorderWidthLeft(1)
									.setBorderWidthBottom(i == lineItems.size() ? 0 : 1))
							.add(Cell
									.withText(lineItem.getSpecifiedLineTradeSettlement()
											.getSpecifiedTradeSettlementLineMonetarySummation().getNetLineTotalAmount()
											.get(0).getValue().toString())
									.setHorizontalAlignment(HorizontalAlignment.CENTER).setBorderWidthLeft(1)
									.setBorderWidthRight(1).setBorderWidthBottom(i == lineItems.size() ? 0 : 1))
							.build());
		}

		// Define the starting point
		final float startY = page.getMediaBox().getHeight() - 250;
		final int startX = 50;

		// Draw!
		(new TableDrawer(content, tableBuilder.build(), startX, startY)).draw();
		// contentStream.close();

		// document.save("E:/test/sampleWithColorsAndBorders.pdf");
		// document.close();
	}

	static void addCenteredText(String text, PDFont font, int fontSize, PDPageContentStream content, PDPage page,
			Point2D.Float offset) throws IOException {
		content.setFont(font, fontSize);
		content.beginText();

		// Rotate the text according to the page orientation
		boolean pageIsLandscape = isLandscape(page);
		Point2D.Float pageCenter = getCenter(page);

		// We use the text's width to place it at the center of the page
		float stringWidth = getStringWidth(text, font, fontSize);
		if (pageIsLandscape) {
			float textX = pageCenter.x - stringWidth / 2F + offset.x;
			float textY = pageCenter.y - offset.y;
			// Swap X and Y due to the rotation
			content.setTextMatrix(Matrix.getRotateInstance(Math.PI / 2, textY, textX));
		} else {
			float textX = pageCenter.x - stringWidth / 2F + offset.x;
			float textY = pageCenter.y + offset.y;
			content.setTextMatrix(Matrix.getTranslateInstance(textX, textY));
		}

		content.showText(text);
		content.endText();
	}

	static boolean isLandscape(PDPage page) {
		int rotation = page.getRotation();
		final boolean isLandscape;
		if (rotation == 90 || rotation == 270) {
			isLandscape = true;
		} else if (rotation == 0 || rotation == 360 || rotation == 180) {
			isLandscape = false;
		} else {
			System.out.println(
					"Can only handle pages that are rotated in 90 degree steps. This page is rotated {} degrees. Will treat the page as in portrait format");
			isLandscape = false;
		}
		return isLandscape;
	}

	private static Point2D.Float getCenter(PDPage page) {
		PDRectangle pageSize = page.getMediaBox();
		// boolean rotated = isRotated(page);
		boolean rotated = false;
		float pageWidth = rotated ? pageSize.getHeight() : pageSize.getWidth();
		float pageHeight = rotated ? pageSize.getWidth() : pageSize.getHeight();
		// System.out.println("Page Width : " + pageWidth);
		// System.out.println("Page Height : " + pageHeight);
		// System.out.println("CenterPage Width : " + pageWidth / 2f);
		// System.out.println("CenterPage Height : " + pageHeight / 2f);

		// return new Point2D.Float(pageWidth / 2F, pageHeight / 2F);
		return new Point2D.Float(pageWidth / 2F, pageHeight - 40);
	}

	static float getStringWidth(String text, PDFont font, int fontSize) throws IOException {
		return font.getStringWidth(text) * fontSize / 1000F;
	}

	private PDDocument makeA3compliant(PDDocument doc, String xml) throws Exception {
		// embed file
		PDEmbeddedFilesNameTreeNode efTree = new PDEmbeddedFilesNameTreeNode();
		PDMetadata metadata = new PDMetadata(doc);
		doc.getDocumentCatalog().setMetadata(metadata);

		// first create the file specification, which holds the embedded file
		PDComplexFileSpecification fs = new PDComplexFileSpecification();
		fs.setFile("ETDA-invoice.xml");
		// create a dummy file stream, this would probably normally be a
		// FileInputStream
		byte[] data = xml.getBytes("utf-8");
		ByteArrayInputStream dataFile = new ByteArrayInputStream(data);
		PDEmbeddedFile ef = new PDEmbeddedFile(doc, dataFile);
		// now lets some of the optional parameters
		ef.setSubtype("text/xml");
		ef.setSize(data.length);
		ef.setCreationDate(new GregorianCalendar());
		fs.setEmbeddedFile(ef);

		// create a new tree node and add the embedded file
		PDEmbeddedFilesNameTreeNode treeNode = new PDEmbeddedFilesNameTreeNode();
		treeNode.setNames(Collections.singletonMap("My first attachment", fs));
		// add the new node as kid to the root node
		List<PDEmbeddedFilesNameTreeNode> kids = new ArrayList<>();
		kids.add(treeNode);
		efTree.setKids(kids);
		// add the tree to the document catalog
		PDDocumentNameDictionary names = new PDDocumentNameDictionary(doc.getDocumentCatalog());
		names.setEmbeddedFiles(efTree);
		doc.getDocumentCatalog().setNames(names);

		XMPMetadata xmp = new XMPMetadata();
		XMPSchemaPDFAId pdfaid = new XMPSchemaPDFAId(xmp);
		xmp.addSchema(pdfaid);
		

		XMPSchemaBasic xsb = xmp.addBasicSchema();
		xsb.setAbout("");
		// Set Application Name
		xsb.setCreatorTool("pdfbox");
		xsb.setCreateDate(GregorianCalendar.getInstance());

		PDDocumentInformation pdi = new PDDocumentInformation();
		pdi.setProducer("ผู้เขียน");
		pdi.setAuthor("ผู้แต่ง");
		pdi.setTitle("ทดสอบ");
		pdi.setSubject("เพื่อทดสอบ");
		pdi.setKeywords("PDFA3");
		doc.setDocumentInformation(pdi);

		XMPSchema xsm = new XMPSchema(xmp, "rsm", "urn:etda:uncefact:data:standard:Invoice_CrossIndustryInvoice:2#");

		Element e = xsm.getElement();
		Element docFileNode = e.getOwnerDocument().createElement("rsm:DocumentFileName");
		docFileNode.setTextContent("ETDA-invoice.xml");
		e.appendChild(docFileNode);
		Element docTypeNode = e.getOwnerDocument().createElement("rsm:DocumentType");
		docTypeNode.setTextContent("TAX INVOICE");
		e.appendChild(docTypeNode);
		Element docVerNode = e.getOwnerDocument().createElement("rsm:Version");
		docVerNode.setTextContent("2.0");
		e.appendChild(docVerNode);

		xmp.addSchema(xsm);

		XMPSchemaPDF pdf = xmp.addPDFSchema();
		pdf.setPDFVersion("1.7");
		pdf.setAbout("");
		
		PDMarkInfo markinfo = new PDMarkInfo();
		markinfo.setMarked(true);
		doc.getDocumentCatalog().setMarkInfo(markinfo);
		pdfaid.setPart(3);
		pdfaid.setConformance("U");
		pdfaid.setAbout("");
		
		byte[] temp = xmp.asByteArray();

		metadata.importXMPMetadata(temp);

		// sRGB output intent
		InputStream colorProfile = PdfHelper.class.getResourceAsStream("/sRGB Color Space Profile.icm");
		PDOutputIntent intent = new PDOutputIntent(doc, colorProfile);
		intent.setInfo("sRGB IEC61966-2.1");
		intent.setOutputCondition("sRGB IEC61966-2.1");
		intent.setOutputConditionIdentifier("sRGB IEC61966-2.1");
		intent.setRegistryName("http://www.color.org");
		doc.getDocumentCatalog().addOutputIntent(intent);

		//doc.save(BASE_FOLDER + FILENAME);
		return null;

	}
}
