package util;

import etda.uncefact.data.standard.qualifieddatatype._1.Max256TextType;

public class StringHelper {

	public StringHelper() {
		// TODO Auto-generated constructor stub
	}

	public Max256TextType ConvertMax256String(String text) {
		Max256TextType max256Text = new Max256TextType();
		max256Text.setValue(text);
		return max256Text;
	}


}
