package projeto.java.web.rest.webfilter.utils;

import java.util.Arrays;
import java.util.List;

public class StringUtils {
	
	public static List<String> getTermos(String texto){
		return Arrays.asList(texto.split(" "));
	}
}
