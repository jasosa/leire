package cat.hmobile.leire.data.xml;

import java.util.List;

import org.jdom.Element;

import android.content.Context;

import cat.hmobile.leire.GUI.Activities.R;
import cat.hmobile.leire.entities.ArgumentException;

public class CategoriesXmlAccessor extends XmlAccessor {
	
	public static final String ROOT_CATEGORY_ID = "-1";
	
	private static final String QUERY_SELECT_CATEGORY_BY_ID = "//*/category[id='%1$s']";
	private static final String QUERY_SELECT_CATEGORIES_BY_PARENT_ID = "//*/category[id='%1$s']/category";
	//private static final String QUERY_SELECT_ROOT_CATEGORIES = "//demo/category[]";
	
	public CategoriesXmlAccessor(Context ctx) {
		super(ctx);
	}
	
	public CategoriesXmlAccessor(Context ctx, String sourcefile){
		super(ctx, sourcefile);
	}
	
	//TODO: Constructor creat pel testeig
	public CategoriesXmlAccessor()
	{
		super();
	}
	
	//TODO: Constructor creat pel testeig
	public CategoriesXmlAccessor(String sourceFile)
	{
		super(sourceFile);
	}
	
	public List<Element> getRootCategories()
	{
		return this.getCategoriesByParentId(ROOT_CATEGORY_ID);
	}
	
	public List<Element> getCategoriesByParentId(String parentCategoryId)
	{
		if(parentCategoryId==null || parentCategoryId.length() == 0)
			throw new ArgumentException(R.string.error_illegal_argument);
		
		String xpath = addParameterToXpath(QUERY_SELECT_CATEGORIES_BY_PARENT_ID,parentCategoryId);
		return this.getNodeList(xpath);
	}

	public Element getCategoryById(String categoryId) {
		if(categoryId==null || categoryId.length() == 0)
			throw new ArgumentException(R.string.error_illegal_argument);
		
		String xpath = addParameterToXpath(QUERY_SELECT_CATEGORY_BY_ID,categoryId);
		return this.getNode(xpath);
	}
}
