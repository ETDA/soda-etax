package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.GregorianCalendar;

import org.apache.jempbox.xmp.XMPMetadata;
import org.apache.jempbox.xmp.XMPSchemaBasic;
import org.apache.jempbox.xmp.XMPSchemaDublinCore;
import org.apache.jempbox.xmp.XMPSchemaPDF;
import org.apache.jempbox.xmp.pdfa.XMPSchemaPDFAId;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDDocumentNameDictionary;
import org.apache.pdfbox.pdmodel.PDEmbeddedFilesNameTreeNode;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDMetadata;
import org.apache.pdfbox.pdmodel.common.filespecification.PDComplexFileSpecification;
import org.apache.pdfbox.pdmodel.common.filespecification.PDEmbeddedFile;
import org.apache.pdfbox.pdmodel.documentinterchange.logicalstructure.PDMarkInfo;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.color.PDOutputIntent;

public class PdfHelper {

	private static String filePath = "E:/test/";
	private static String pdfPath = "table.pdf";
	private static String inputFileName = "sample.pdf";
	private static String embedFileName = "sample.pdf";
	private static String outputFile = "success.pdf";

	public PdfHelper() {

	}

	// public static void main(String[] args) throws IOException {

	// 	try {
	// 		createPDF();
	// 		// convert();
	// 	} catch (Exception e) {
	// 		e.printStackTrace();
	// 	}

	// }

	public static void createPDF() throws IOException {
		PDDocument doc = new PDDocument();
		PDPage page = new PDPage();
		doc.addPage(page);
		PDPageContentStream contentStream = new PDPageContentStream(doc, page);

		String[][] content = { { "Name", " Time " }, { "HTC", "01:25" }, { "Samsung Tab2", "05:30" } };

		drawTable(page, contentStream, 700, 100, content);

		contentStream.close();
		String output = filePath + pdfPath;
		doc.save(output);
	}

	public static void convert() throws Exception {
		File inputFile = new File(filePath + pdfPath);
		File embedFile = new File(filePath + embedFileName);
		PDDocument doc = PDDocument.load(inputFile);

		PDDocumentCatalog cat = makeA3compliant(doc);
		attachSampleFile(doc, embedFile, embedFileName);
		addOutputIntent(doc, cat);

		doc.save(filePath + outputFile);
		doc.close();
	}

	public static void drawTable(PDPage page, PDPageContentStream contentStream, float y, float margin,
			String[][] content) throws IOException {
		final int rows = content.length;
		final int cols = content[0].length;
		final float rowHeight = 20f;
		final float tableWidth = page.getCropBox().getWidth() - margin - margin;
		final float tableHeight = rowHeight * rows;
		final float colWidth = tableWidth / (float) cols;
		final float cellMargin = 5f;

		// draw the rows
		float nexty = y;
		for (int i = 0; i <= rows; i++) {
			contentStream.moveTo(margin, nexty);
			contentStream.lineTo(margin + tableWidth, nexty);
			contentStream.stroke();
			nexty -= rowHeight;
		}

		// draw the columns
		float nextx = margin;
		for (int i = 0; i <= cols; i++) {
			contentStream.moveTo(nextx, y);
			contentStream.lineTo(nextx, y - tableHeight);
			contentStream.stroke();
			nextx += colWidth;
		}

		// now add the text
		contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
		float textx = margin + cellMargin;
		float texty = y - 15;
		for (int i = 0; i < content.length; i++) {
			for (int j = 0; j < content[i].length; j++) {
				String text = content[i][j];
				contentStream.beginText();
				contentStream.newLineAtOffset(textx, texty);
				contentStream.showText(text);
				contentStream.endText();
				textx += colWidth;
			}
			texty -= rowHeight;
			textx = margin + cellMargin;
		}
	}

	private static void attachSampleFile(PDDocument doc, File embedFile, String embedFileName) throws IOException {
		PDEmbeddedFilesNameTreeNode efTree = new PDEmbeddedFilesNameTreeNode();

		// first create the file specification, which holds the embedded file

		PDComplexFileSpecification fs = new PDComplexFileSpecification();
		fs.setFile(embedFileName);
		COSDictionary dict = fs.getCOSObject();
		// Relation "Source" for linking with eg. catalog
		// dict.setName("AFRelationship", "Alternative");
		dict.setName("AFRelationship", "Source");

		dict.setString("UF", embedFileName);
		// fs.put(new PdfName("AFRelationship"), new PdfName("Source"));

		InputStream is = new FileInputStream(embedFile);

		PDEmbeddedFile ef = new PDEmbeddedFile(doc, is);
		// set some of the attributes of the embedded file

		ef.setModDate(GregorianCalendar.getInstance());

		ef.setSize((int) embedFile.length());
		ef.setCreationDate(new GregorianCalendar());
		fs.setEmbeddedFile(ef);

		// now add the entry to the embedded file tree and set in the document.
		efTree.setNames(Collections.singletonMap("My first attachment", fs));

		// attachments are stored as part of the "names" dictionary in the
		// document
		// catalog
		PDDocumentCatalog catalog = doc.getDocumentCatalog();

		PDDocumentNameDictionary names = new PDDocumentNameDictionary(doc.getDocumentCatalog());
		names.setEmbeddedFiles(efTree);
		catalog.setNames(names);

		// // AF entry (Array) in catalog with the FileSpec
		// PDAcroForm pdAcroForm = new PDAcroForm(doc);
		// COSArray cosArray = new COSArray();
		// cosArray.add(fs);
		// catalog.setItem("AF", cosArray);

		COSDictionary dict2 = catalog.getCOSObject();
		COSArray array = new COSArray();
		array.add(fs.getCOSObject()); // see below
		dict2.setItem("AF", array);
	}

	private static PDDocumentCatalog makeA3compliant(PDDocument doc) throws Exception {
		PDDocumentCatalog cat = doc.getDocumentCatalog();
		PDDocumentInformation pdd = doc.getDocumentInformation();
		PDMetadata metadata = new PDMetadata(doc);
		cat.setMetadata(metadata);
		// jempbox version
		XMPMetadata xmp = new XMPMetadata();
		XMPSchemaPDFAId pdfaid = new XMPSchemaPDFAId(xmp);
		xmp.addSchema(pdfaid);

		XMPSchemaDublinCore dc = xmp.addDublinCoreSchema();
		dc.addCreator(pdd.getCreator());
		dc.setAbout("");

		XMPSchemaBasic xsb = xmp.addBasicSchema();
		xsb.setAbout("");

		// Set Application Name
		xsb.setCreatorTool("pdfbox");
		xsb.setCreateDate(GregorianCalendar.getInstance());

		PDDocumentInformation pdi = new PDDocumentInformation();
		pdi.setProducer(pdd.getProducer());
		pdi.setAuthor(pdd.getAuthor());
		pdi.setTitle(pdd.getTitle());
		pdi.setSubject(pdd.getSubject());
		pdi.setKeywords(pdd.getKeywords());

		// Set OID Test
		pdi.setCustomMetadataValue("OID", "10.2.3.65.5");
		doc.setDocumentInformation(pdi);

		XMPSchemaPDF pdf = xmp.addPDFSchema();
		pdf.setProducer(pdd.getProducer());
		pdf.setAbout("");

		// Mandatory: PDF-A3 is tagged PDF which has to be expressed using a
		// MarkInfo dictionary (PDF A/3 Standard sec. 6.7.2.2)
		PDMarkInfo markinfo = new PDMarkInfo();
		markinfo.setMarked(true);
		doc.getDocumentCatalog().setMarkInfo(markinfo);

		pdfaid.setPart(3);
		pdfaid.setConformance(
				"U");/*
						 * All files are PDF/A-3, setConformance refers to the
						 * level conformance, e.g. PDF/A-3-B where B means only
						 * visually preservable, U means visually and unicode
						 * preservable and A -like in this case- means full
						 * compliance, i.e. visually, unicode and structurally
						 * preservable
						 */
		pdfaid.setAbout("");
		byte[] temp = xmp.asByteArray();
		metadata.importXMPMetadata(temp);
		return cat;
	}

	private static void addOutputIntent(PDDocument doc, PDDocumentCatalog cat) throws IOException {
		InputStream colorProfile = PdfHelper.class.getResourceAsStream("/sRGB Color Space Profile.icm");
		if (cat.getOutputIntents().isEmpty()) {
			PDOutputIntent oi = new PDOutputIntent(doc, colorProfile);
			oi.setInfo("sRGB IEC61966-2.1");
			oi.setOutputCondition("sRGB IEC61966-2.1");
			oi.setOutputConditionIdentifier("sRGB IEC61966-2.1");
			oi.setRegistryName("http://www.color.org");
			cat.addOutputIntent(oi);
		}

	}

	private static void createTable() {

	}

}
