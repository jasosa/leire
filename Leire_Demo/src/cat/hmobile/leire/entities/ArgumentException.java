package cat.hmobile.leire.entities;

public class ArgumentException extends LeireException {
	public ArgumentException(int errorMessageCode) {
		super();
		this.m_errorMessageCode = errorMessageCode;
	}
}
