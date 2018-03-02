package cat.hmobile.leire.business.microblogging.statusnetextension;

import cat.hmobile.leire.business.microblogging.MicrobloggingService;

public class StatusNetMarkersFormatter {
	
	public static final String CELIAC_GENERAL_GROUP_MARKER = "!celiacgeneral";
	
	public static String removeMarkers(String message){
		StringBuilder builder = new StringBuilder();
		for(String word : message.split(" ")){
			if(!word.equals(CELIAC_GENERAL_GROUP_MARKER)){
				builder.append(word);
				builder.append(" ");
			}
		}
		
		return builder.toString().trim();
	}
	
	public static String addMarkers(String message, String marker){
		StringBuilder builder = new StringBuilder(message);
		builder.insert(0, " ");
		builder.insert(0, marker);
		return builder.toString();
	}
}
