package cat.hmobile.leire.business.microblogging.statusnetextension;

import java.util.List;
import java.util.Map;

import cat.hmobile.leire.thirdparty.jtwitter.Twitter;
import cat.hmobile.leire.thirdparty.jtwitter.TwitterException.SuspendedUser;


public class StatusNet extends Twitter {
	
	public StatusNet(String user, String password){
			super(user, password);
	}
	
	public StatusNet(String user, IHttpClient password){
		super(user, password);
}
	
	/**
	 * Returns all status for the users in the given group.
	 * 
	 * @param groupId
	 *            The id of the group
	 * @throws exception
	 *             if the group does not exist
	 * @throws SuspendedUser if the user has been terminated (as happens to spam bots).
	 * @see #show(long)
	 * 
	 * URL Example: http://[apiUrl]/statusnet/groups/timeline.json?id=groupId
	 * 
	 */
	public List<Status> getMessagesFromGroup(String groupId){
		Map vars = newMap("id", groupId);
		return getStatuses (TWITTER_URL + "/statusnet/groups/timeline.json", vars, true);
	}
}