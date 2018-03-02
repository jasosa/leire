package cat.hmobile.leire.entities;

public class LeireException extends RuntimeException {
	
	protected int m_errorMessageCode = -1;
	
	public int getErrorMessageCode()
	{
		return m_errorMessageCode;
	}
}
