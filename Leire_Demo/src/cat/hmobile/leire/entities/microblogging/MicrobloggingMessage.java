package cat.hmobile.leire.entities.microblogging;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import cat.hmobile.leire.thirdparty.jtwitter.Twitter.Status;
import cat.hmobile.leire.thirdparty.jtwitter.Twitter.User;



public class MicrobloggingMessage implements IMicrobloggingMessage {

	Status m_twitStatus;
	IMicrobloggingUser m_user;
	IMicrobloggingUserLoader m_userLoader;
		
	public MicrobloggingMessage(Status twitStatus, IMicrobloggingUserLoader userLoader){
		m_twitStatus = twitStatus;
		m_userLoader = userLoader;
	}
	@Override
	public BigInteger getId() {
		return m_twitStatus.getId();
	}

	@Override
	public Date getCreatedAd() {
		return m_twitStatus.getCreatedAt();
	}

	@Override
	public List<IMicrobloggingMessage> getMentions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IMicrobloggingMessage getOriginal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getText() {
		return m_twitStatus.getText();
	}

	@Override
	public IMicrobloggingUser getUser() {
		return m_userLoader.loadUser(m_twitStatus.getUser());		
	}
	
	
	@Override
	public boolean isFavorite() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getText();
	}
	
	@Override
	public boolean equals(Object o) {
		return this.getId().equals(((MicrobloggingMessage)o).getId());
	} 
}
