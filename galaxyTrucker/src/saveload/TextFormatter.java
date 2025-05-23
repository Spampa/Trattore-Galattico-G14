package saveload;

public class TextFormatter {
	
	public static String createOpenTag(String s) {
		return "<" + s + ">";
	}
	
	public static String createCloseTag(String s) {
		return "</" + s + ">";
	}
	
	public static boolean isTag(String s) {
		return s.contains("<") && s.contains(">");
	}
	
	public static boolean isCloseTag(String s) {
		return s.contains("</") && s.contains(">");
	}
	
	public static String getTag(String s) {
		if(isCloseTag(s)) {
			return s.substring(2, s.length() - 1);
		}
		return s.substring(1, s.length() - 1);
	}
}
