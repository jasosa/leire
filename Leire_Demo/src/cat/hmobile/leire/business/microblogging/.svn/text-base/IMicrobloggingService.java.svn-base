package cat.hmobile.leire.business.microblogging;

import java.util.List;

import cat.hmobile.leire.entities.microblogging.IMicrobloggingMessage;
import cat.hmobile.leire.entities.microblogging.IMicrobloggingUser;
import cat.hmobile.leire.thirdparty.jtwitter.Twitter.IHttpClient;

public interface IMicrobloggingService {
	public void Login(String userName, String password);
	public void Login(String userName, IMicrobloggingOAuthClient oAuthclient);
	public List<IMicrobloggingMessage> getPublicTimeLine();
	public List<IMicrobloggingMessage> getHomeTimeLine();
	public List<IMicrobloggingMessage> getFriendsTimeLine();
	public List<IMicrobloggingMessage> getMentions();
	public List<IMicrobloggingMessage> getUserTimeLine();
	public List<IMicrobloggingMessage> getMessagesFromGroup(String groupName);
	public void sendMessage(String message);
	public void setApiUrlRoot(String url);
}
