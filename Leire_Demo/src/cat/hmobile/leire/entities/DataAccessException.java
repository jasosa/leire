package cat.hmobile.leire.entities;

public class DataAccessException extends LeireException
{
	public DataAccessException(int errorMessageCode) {
		super();
		this.m_errorMessageCode = errorMessageCode;
	}
}
