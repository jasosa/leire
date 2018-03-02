package cat.hmobile.leire.data.xml.test;

import java.util.List;

import org.jdom.Element;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cat.hmobile.leire.data.xml.CategoriesXmlAccessor;

public class CategoriesXmlAccessorTest {

	private static final String CHILD_ID="id";
	private static final String CHILD_LEAF="isLeaf";
	private static final String CHILD_NAME="name";
	private static final String CHILD_PARENT_ID="parentId";
	
	
	private CategoriesXmlAccessor m_categories;
	
	@Before
	public void setUp() throws Exception {
		this.m_categories = new CategoriesXmlAccessor();
	}
	

	@Test
	public void testGetRootCategories() {
		List categories = this.m_categories.getRootCategories();
		Assert.assertEquals(5, categories.size());
	}

	@Test
	public void testGetCategoriesByParentId() {
		List categories = this.m_categories.getCategoriesByParentId("002");
		Assert.assertEquals(2, categories.size());
	}

	@Test
	public void testGetCategoryById() {
		Element category = this.m_categories.getCategoryById("012");
		this.AssertCategory(category,"012","true","Categ_0000321","007");
	}
	
	private void AssertCategory(Element category, String id, String isLeaf, String name, String parentId)
	{
		Assert.assertEquals(id, category.getChild(CHILD_ID).getText());
		Assert.assertEquals(isLeaf, category.getChild(CHILD_LEAF).getText());
		Assert.assertEquals(name,category.getChild(CHILD_NAME).getText());
		Assert.assertEquals(parentId,category.getChild(CHILD_PARENT_ID).getText());
	}
}