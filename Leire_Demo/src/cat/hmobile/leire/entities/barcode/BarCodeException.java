package cat.hmobile.leire.entities.barcode;

import cat.hmobile.leire.entities.LeireException;

public class BarCodeException extends LeireException
{
	public BarCodeException(int errorMessageCode) {
		super();
		this.m_errorMessageCode = errorMessageCode;
	}
}
