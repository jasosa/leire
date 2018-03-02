package cat.hmobile.leire.business.microblogging;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import android.R;

import cat.hmobile.leire.business.microblogging.statusnetextension.StatusNet;
import cat.hmobile.leire.business.microblogging.statusnetextension.StatusNetMarkersFormatter;
import cat.hmobile.leire.entities.microblogging.IMicrobloggingMessage;
import cat.hmobile.leire.entities.microblogging.IMicrobloggingUser;
import cat.hmobile.leire.entities.microblogging.IMicrobloggingUserLoader;
import cat.hmobile.leire.entities.microblogging.MicrobloggingAuthenticationException;
import cat.hmobile.leire.entities.microblogging.MicrobloggingNetworkException;
import cat.hmobile.leire.entities.microblogging.MicrobloggingMessage;
import cat.hmobile.leire.entities.microblogging.MicrobloggingUser;
import cat.hmobile.leire.entities.microblogging.MicrobloggingUserBuilder;
import cat.hmobile.leire.entities.microblogging.MicrobloggingUserLoader;
import cat.hmobile.leire.entities.microblogging.MicrobloggingUsersHolder;
import cat.hmobile.leire.thirdparty.jtwitter.OAuthSignpostClient;
import cat.hmobile.leire.thirdparty.jtwitter.Twitter;
import cat.hmobile.leire.thirdparty.jtwitter.TwitterException;
import cat.hmobile.leire.thirdparty.jtwitter.TwitterList;
import cat.hmobile.leire.thirdparty.jtwitter.Twitter.Status;


public class MicrobloggingService implements IMicrobloggingService {
	
	public static final String GENERAL_GROUP_NAME = "celiacgeneral";
	private static IMicrobloggingService microbloggingService;
	private static IMicrobloggingUserLoader microbloggingUserLoader;
	StatusNet m_twitter; 
	
	public static IMicrobloggingService getService(){
		if(microbloggingService==null){
			microbloggingService = new MicrobloggingService();
			microbloggingUserLoader = new MicrobloggingUserLoader(new MicrobloggingUserBuilder());
		}
		return microbloggingService;
	}
	
	public void Login(String userName, String password){
		m_twitter = new StatusNet(userName, password);
	}
	
	public void Login(String userName, IMicrobloggingOAuthClient oAuthclient){
		m_twitter = new StatusNet(userName, oAuthclient.getIHttpClient());
	}
	
	public void setApiUrlRoot(String url){
		m_twitter.setAPIRootUrl(url);
	}
	
	@Override
	public List<IMicrobloggingMessage> getPublicTimeLine() {
		List<IMicrobloggingMessage> publicTimeLineMessages = new ArrayList<IMicrobloggingMessage>();
		try{
		List<Status> publicTimeLineStatus = m_twitter.getPublicTimeline(); 
		return convertStatusToIMicroBloggingMessages(publicTimeLineStatus);
		}
		catch(TwitterException te){
			if(te.getCause().getClass() == UnknownHostException.class)
				throw new MicrobloggingNetworkException(cat.hmobile.leire.GUI.Activities.R.string.network_error);
			else
				throw new MicrobloggingException(te.getMessage());
		}
	}

	@Override
	public List<IMicrobloggingMessage> getHomeTimeLine() {
		List<IMicrobloggingMessage> publicTimeLineMessages = new ArrayList<IMicrobloggingMessage>();
		try{
			List<Status> publicTimeLineStatus = m_twitter.getHomeTimeline();
			return convertStatusToIMicroBloggingMessages(publicTimeLineStatus);
		}
		catch(TwitterException te){
			if(te.getCause().getClass() == UnknownHostException.class)
				throw new MicrobloggingNetworkException(cat.hmobile.leire.GUI.Activities.R.string.network_error);
			else if(te.getCause().getClass() == IOException.class)
				throw new MicrobloggingAuthenticationException(cat.hmobile.leire.GUI.Activities.R.string.statusnet_credendtials_error);
			else
				throw new MicrobloggingException(te.getMessage());
		}
	}

	@Override
	public List<IMicrobloggingMessage> getFriendsTimeLine() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IMicrobloggingMessage> getMentions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IMicrobloggingMessage> getUserTimeLine() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IMicrobloggingMessage> getMessagesFromGroup(String groupName) {
		try{
			List<Status> publicTimeLineStatus = m_twitter.getMessagesFromGroup(groupName);
			return convertStatusToIMicroBloggingMessages(publicTimeLineStatus);
		}
		catch(TwitterException te){
			if(te.getCause().getClass() == UnknownHostException.class)
				throw new MicrobloggingNetworkException(cat.hmobile.leire.GUI.Activities.R.string.network_error);
			else if(te.getCause().getClass() == IOException.class)
				throw new MicrobloggingAuthenticationException(cat.hmobile.leire.GUI.Activities.R.string.statusnet_credendtials_error);
			else
				throw new MicrobloggingException(te.getMessage()); 
		}
	}
	
	@Override
	public void sendMessage(String message) {
		try{
			m_twitter.setStatus(message);
		}
		catch(TwitterException te){
			if(te.getCause().getClass() == UnknownHostException.class)
				throw new MicrobloggingNetworkException(cat.hmobile.leire.GUI.Activities.R.string.network_error);
			else if(te.getCause().getClass() == IOException.class)
				throw new MicrobloggingAuthenticationException(cat.hmobile.leire.GUI.Activities.R.string.statusnet_credendtials_error);
			else
				throw new MicrobloggingException(te.getMessage()); 
		}
	}

	private List<IMicrobloggingMessage> convertStatusToIMicroBloggingMessages(
			List<Status> publicTimeLineStatus) {
		List<IMicrobloggingMessage> publicTimeLineMessages = new ArrayList<IMicrobloggingMessage>();
		for(int i = 0;i<publicTimeLineStatus.size();i++){
			publicTimeLineMessages.add(new MicrobloggingMessage(publicTimeLineStatus.get(i), microbloggingUserLoader));
		}
		
		return publicTimeLineMessages;
	}
	
}
