package cat.hmobile.leire.entities.microblogging;

import cat.hmobile.leire.thirdparty.jtwitter.Twitter.User;

public class MicrobloggingUserLoader implements IMicrobloggingUserLoader {
	
	IMicrobloggingUserBuilder m_userBuilder;
	
	public MicrobloggingUserLoader (IMicrobloggingUserBuilder userBuilder){
		m_userBuilder = userBuilder;
	}
	
	/* (non-Javadoc)
	 * @see cat.hmobile.leire.entities.microblogging.IMicrobloggingUserLoader#loadUser(cat.hmobile.leire.thirdparty.jtwitter.Twitter.User)
	 */
	@Override
	public IMicrobloggingUser loadUser (User m_twitUser){
		
		if(!isUserInHolder(m_twitUser.getId()))
		{
			addUserToHolder(m_twitUser);
		}
		
		return getUserFromHolder(m_twitUser.getId());
	}
	
	private IMicrobloggingUser getUserFromHolder(Long userId) {
		return MicrobloggingUsersHolder.getHolder().getUser(userId);
	}
	
	private void addUserToHolder(User m_twitUser) {
		MicrobloggingUsersHolder.getHolder().addUser(m_userBuilder.build(m_twitUser));
	}
	
	private boolean isUserInHolder(Long userId) {
		return MicrobloggingUsersHolder.getHolder().containsUser(userId);
	}
}
