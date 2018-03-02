package cat.hmobile.leire.entities.microblogging;

import java.util.HashMap;

public class MicrobloggingUsersHolder {
	
	static MicrobloggingUsersHolder m_holder;
	static HashMap<Long, IMicrobloggingUser> m_usersMap;
	
	private MicrobloggingUsersHolder(){
		m_usersMap = new HashMap<Long, IMicrobloggingUser>();
	}
	
	public static MicrobloggingUsersHolder getHolder(){
		if(m_holder == null){
			m_holder = new MicrobloggingUsersHolder();
		}
		
		return m_holder;
	}
	
	public void addUser(IMicrobloggingUser user){
		m_usersMap.put(user.getId(), user); 
	}
	
	public IMicrobloggingUser getUser (Long userId){
		try{
			return m_usersMap.get(userId);
		}
		catch(Exception e){
			throw new MicrobloggingUserNotInHolderException();
		}
	}
	
	public boolean containsUser(Long userId){
		return m_usersMap.containsKey(userId);
	}
}
