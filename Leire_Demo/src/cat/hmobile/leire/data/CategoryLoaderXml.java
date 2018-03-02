package cat.hmobile.leire.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom.Element;

import android.content.Context;

import cat.hmobile.leire.GUI.Activities.R;
import cat.hmobile.leire.data.xml.CategoriesXmlAccessor;
import cat.hmobile.leire.entities.ArgumentException;
import cat.hmobile.leire.entities.categories.*;

public class CategoryLoaderXml implements ICategoryLoader
{
	private static final String CHILD_ID="id";
	private static final String CHILD_LEAF="isLeaf";
	private static final String CHILD_NAME="name";
	private static final String CHILD_PARENT_ID="parentId";
	public static final String DEFAULT_CATALOG_XML_DATAFILE = "demoData.xml";
	
	private CategoriesXmlAccessor m_categoriesAccessor;
	
	public CategoryLoaderXml(Context ctx, String sourceFile)
	{
		this.m_categoriesAccessor = new CategoriesXmlAccessor(ctx, sourceFile);
	}
	
	//TODO: for test purposes
	public CategoryLoaderXml(String sourceFile)
	{
		this.m_categoriesAccessor = new CategoriesXmlAccessor(sourceFile);
	}
	
	@Override
	public List<Category> loadMain() {
		List<Element> xmlCategories = this.m_categoriesAccessor.getRootCategories();
		return this.buildCategoryList(xmlCategories);
	}

	@Override
	public Category load(String categoryId) {
		if(categoryId==null || categoryId.length() == 0)
			throw new ArgumentException(R.string.error_illegal_argument);
		
		Element xmlCategory = this.m_categoriesAccessor.getCategoryById(categoryId);
		return this.buildCategory(xmlCategory);
	}

	@Override
	public List<Category> loadChilds(String parentCategoryId) {
		if(parentCategoryId==null || parentCategoryId.length() == 0)
			throw new ArgumentException(R.string.error_illegal_argument);
		
		List<Element> xmlCategories = this.m_categoriesAccessor.getCategoriesByParentId(parentCategoryId);
		return this.buildCategoryList(xmlCategories);
	}

	private List<Category> buildCategoryList(List<Element> xmlCategories) {
		
		List<Category> result = new ArrayList<Category>();
		
		Element currentElement;
		for (Iterator<Element> i = xmlCategories.iterator(); i.hasNext();)
		{
			currentElement = i.next();
			result.add(this.buildCategory(currentElement));
		}
	return result;
	}
	
	private Category buildCategory(Element xmlCategory) {
		Category result = null;
		
		if(xmlCategory != null)
		{
			String id = xmlCategory.getChild(CHILD_ID).getText();
			Boolean isLeaf =  Boolean.parseBoolean(xmlCategory.getChild(CHILD_LEAF).getText());
			String name = xmlCategory.getChild(CHILD_NAME).getText();
			//String parentId = xmlCategory.getChild(CHILD_PARENT_ID).getText();
					
			if(isLeaf)
				result = new CategoryLeaf(id, name);
			else
				result = new Category(id, name);
		}
	return result;
	}

}
