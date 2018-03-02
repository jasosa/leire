package cat.hmobile.leire.GUI.Helpers;

import android.os.Bundle;
import cat.hmobile.leire.business.microblogging.statusnetextension.StatusNetMarkersFormatter;
import cat.hmobile.leire.entities.microblogging.IMicrobloggingMessage;
import cat.hmobile.leire.entities.products.Product;

public class MicrobloggingBundleHelper {

	public static final String KEY_MESSAGE_TEXT = "message_text";
	public static final String KEY_MESSAGE_CREATED_AT = "message_created_at";
	public static final String KEY_MESSAGE_USER_ID = "message_user_id";

	public static Bundle createBundleFromMessage(IMicrobloggingMessage message) {
		 Bundle b = new Bundle();
		 b.putString(MicrobloggingBundleHelper.KEY_MESSAGE_TEXT, StatusNetMarkersFormatter.removeMarkers(message.getText()));
	     b.putString(MicrobloggingBundleHelper.KEY_MESSAGE_CREATED_AT, message.getCreatedAd().toLocaleString());
	     b.putLong(MicrobloggingBundleHelper.KEY_MESSAGE_USER_ID, message.getUser().getId());
	     return b;
	 }	
}
