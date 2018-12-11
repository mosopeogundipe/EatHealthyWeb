package eathealthyweb.logic;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.apache.commons.text.StringEscapeUtils;

/**
 * This class holds General Functions
 * @author mosopeogundipe
 *
 */
public class Utilities {

	/**
	 * Decodes a URL
	 * @param value
	 * @return
	 */
	public static String decode(String value)
	{
		String result = null;
		try {
			result = URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException ioe) {
			ioe.printStackTrace();
		}
		return result;
	}

	/**
	 * Encodes a URL
	 * @param value
	 * @return
	 */
	public static String encode(String value)
	{
		String result = null;
		try {
			result = URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException ioe) {
			ioe.printStackTrace();
		}
		return result;
	}
	
	public static String encodeHtml(String value)
	{
		String result = StringEscapeUtils.escapeHtml4(value);
		return result;
	}
	
	public static String encodeJavaScript(String value)
	{
		String result = StringEscapeUtils.escapeEcmaScript(value);
		return result;
	}
}

