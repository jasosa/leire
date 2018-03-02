package cat.hmobile.leire.entities.microblogging;

import cat.hmobile.leire.thirdparty.jtwitter.Twitter.User;

public class MicrobloggingUserBuilder implements IMicrobloggingUserBuilder {

	/* (non-Javadoc)
	 * @see cat.hmobile.leire.entities.microblogging.IMicrobloggingUserBuilder#build(cat.hmobile.leire.thirdparty.jtwitter.Twitter.User)
	 */
	@Override
	public IMicrobloggingUser build(User m_twitUser) {
		return new MicrobloggingUser(m_twitUser);
	}

}
