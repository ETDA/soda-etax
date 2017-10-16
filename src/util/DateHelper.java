package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class DateHelper {

	public DateHelper() {
		
	}
	
	public XMLGregorianCalendar Convert2XmlGregorianDate(String dateInString) throws ParseException, DatatypeConfigurationException{
		//TODO แสดงปีไม่ถูก
		GregorianCalendar c = new GregorianCalendar();
		TimeZone thaiTimeZone = TimeZone.getTimeZone("US");
		//c.getInstance(Locale.US);
		c.setTimeZone(thaiTimeZone);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sss");
		//sdf.setTimeZone(TimeZone.getTimeZone("GMT+7"));
		Date date = null;
		try {
			date = sdf.parse(dateInString);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		c.setTime(date);
		XMLGregorianCalendar gregorianDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		return gregorianDate;
	}

}
