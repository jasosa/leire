package cat.hmobile.leire.entities.categories;

import cat.hmobile.leire.entities.LeireException;

public class CategoryNotFoundException extends LeireException {
	
	private String m_category;
	
	public CategoryNotFoundException(String categoryId, int errorMessageCode)
	{
		m_category = categoryId;
		m_errorMessageCode = errorMessageCode;
	}
	
	public CategoryNotFoundException(String categoryId)
	{
		super();
		this.m_category = categoryId;
	}
	
	public String getCategoryId()
	{
		return m_category;
	}
}
