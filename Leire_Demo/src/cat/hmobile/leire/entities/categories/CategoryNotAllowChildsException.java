package cat.hmobile.leire.entities.categories;

import cat.hmobile.leire.entities.LeireException;

public class CategoryNotAllowChildsException extends LeireException {
	
	public CategoryNotAllowChildsException(int errorMessageCode) {
		super();
		this.m_errorMessageCode = errorMessageCode;
	}
}
