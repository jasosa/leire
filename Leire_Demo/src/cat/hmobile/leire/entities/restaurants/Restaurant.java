package cat.hmobile.leire.entities.restaurants;

public class Restaurant {
	
	String m_Name;
	String m_Address;
	String m_webAddress;
	boolean m_isApproved;
	
	public Restaurant(String name, String address, String web, boolean isApproved){
		 m_Name = name;
		 m_Address = address;
		 m_webAddress = web;
		 m_isApproved = isApproved;
	}
	
	public String getName(){
		return m_Name;
	}
	
	public String getAddress(){
		return m_Address;
	}
	
	public String getWebAddress(){
		return m_webAddress;
	}
	
	public boolean isApproved(){
		return m_isApproved;
	}
}
