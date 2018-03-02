package cat.hmobile.leire.entities.categories;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cat.hmobile.leire.business.Catalog;

public class CategoryMap {

	private Category m_rootCategory;
	
	public CategoryMap()
	{
		this.m_rootCategory = new Category(Category.ROOT_ID, Catalog.ROOT_CATEGORY_NAME);
	}
	
	public void add(Category cat){
		
		if(cat.getParent()==null)
		{
			this.addMainCategory(cat);
		}
		else
		{
			this.addChildCategory(cat);
		}
	}

	public Category getCategory(String categoryId)
	{	
		if(Category.ROOT_ID.equals(categoryId)) 
		{
			return m_rootCategory;
		}
		else
		{
			Category categoryToFind = this.findInChilds(m_rootCategory, categoryId);
			if(categoryToFind == null)
				throw new CategoryNotFoundException(categoryId);
			else 
				return categoryToFind;
		}
	}
	
	public int getMainCategoriesCount()
	{
		if(m_rootCategory.getChilds() != null)
			return m_rootCategory.getChilds().size();
		else
			return 0;
	}
	
	public List<Category> getMainCategories() {
		
		if(m_rootCategory.getChilds()!=null)
		{
			Iterator<Category> mainCategories = this.m_rootCategory.getChilds().iterator();
			ArrayList<Category> categoriesList = new ArrayList<Category>();
			while(mainCategories.hasNext())
			{	
				categoriesList.add(mainCategories.next());
			}
			
			return categoriesList;
		}
		else
			return new ArrayList<Category>();
	}

	private Category findInChilds(Category currentParentCategory, String categoryId) {

		if(currentParentCategory.getChilds()!=null) 
		{
			Iterator<Category> childCategories = currentParentCategory.getChilds().iterator();
			while(childCategories.hasNext())
			{
				Category current = childCategories.next();
				if(current.getId().equals(categoryId))
				{
					return current;
				}
				else
				{
					current = findInChilds(current, categoryId);
					if(current != null) return current;
				}
			}
		}
		
		return null;
	}
	
	private void addChildCategory(Category cat) {
		Category parentCategory = this.getCategory(cat.getParent().getId());
		parentCategory.addChild(cat);
	}

	private void addMainCategory(Category cat) {
		m_rootCategory.addChild(cat);
	}
}
