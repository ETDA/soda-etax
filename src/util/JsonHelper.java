package util;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class JsonHelper {
	public JSONObject readFile() throws IOException, ParseException{
		Reader reader = new FileReader("resources/json_input.json");
		JSONParser parser = new JSONParser();
	    JSONObject jsonObj = (JSONObject) parser.parse(reader);
	    return jsonObj;

	}
	
	public JSONObject parseString(String data) throws ParseException{
		JSONParser parser = new JSONParser();
	    JSONObject jsonObj = (JSONObject) parser.parse(data);
	    return jsonObj;
	}
	

}
