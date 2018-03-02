package cat.hmobile.leire.entities.microblogging;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import cat.hmobile.leire.thirdparty.jtwitter.Twitter.User;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.widget.ImageView;


public class MicrobloggingUser implements IMicrobloggingUser {

	User m_twittUser;
	Bitmap m_userAvatar;
	boolean m_currentUser;
	
	public static String CURRENT_USER_NAME = "";
	
	public MicrobloggingUser (User twittUser){
		m_twittUser = twittUser;
		m_currentUser = (CURRENT_USER_NAME.equals(twittUser.getScreenName()));
	}
	
	
	@Override
	public Long getId() {
		return m_twittUser.getId();
	}

	@Override
	public String getName() {
		return m_twittUser.getName();
	}

	@Override
	public String getScreenName() {
		return m_twittUser.getScreenName();
	}

	@Override
	public Bitmap getImage(){
		if(m_userAvatar == null){
			m_userAvatar = createAvatar();
		}

		return m_userAvatar;
	}
	
	@Override
	public boolean isCurrentUser(){
		return m_currentUser;
	}

	private Bitmap createAvatar(){
		
		Bitmap avatar = null;
		try{
			URL newurl;
			newurl = new URL(m_twittUser.getProfileImageUrl().toString());
			avatar = BitmapFactory.decodeStream(newurl.openConnection() .getInputStream());
		}
		catch(MalformedURLException me){
    	}
    	catch(IOException io){
    	}
    	
    	return avatar;
	}

	@Override
	public Uri getImageURI() {
		Uri uri = Uri.parse(m_twittUser.getProfileImageUrl().toString());
		return uri;
	}

}
