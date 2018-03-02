package cat.hmobile.leire.data.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cat.hmobile.leire.data.CategoryLoaderXml;
import cat.hmobile.leire.entities.ArgumentException;
import cat.hmobile.leire.entities.categories.Category;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CategoryLoaderXmlTest extends Object {

	private CategoryLoaderXml m_categoryLoader;
	
	@Before
	public void setUp() throws Exception {
		this.m_categoryLoader = new CategoryLoaderXml(CategoryLoaderXml.DEFAULT_CATALOG_XML_DATAFILE);
	}
	
	@Test
	public void testLoadMain_OK01()
	{
		List<Category> actual = this.m_categoryLoader.loadMain();
		Assert.assertTrue(this.areEqual(this.buildExpectedMain(), actual));
	}
	
	@Test
	public void testLoad_OK01()
	{
		Category actual = this.m_categoryLoader.load("003");
		Assert.assertTrue(this.buildExpectedCategory003().equals(actual));
	}
	
	@Test
	public void testLoad_OK02()
	{
		Category actual = this.m_categoryLoader.load("049");
		Assert.assertTrue(this.buildExpectedCategory049().equals(actual));
	}

	@Test(expected = ArgumentException.class)
	public void testLoad_KO01()
	{
		Category actual = this.m_categoryLoader.load("");
	}
	
	@Test(expected = ArgumentException.class)
	public void testLoad_KO02()
	{
		Category actual = this.m_categoryLoader.load(null);
	}
	
	@Test
	public void testLoadChilds_OK01()
	{
		List<Category> actual =this.m_categoryLoader.loadChilds("002");
		Assert.assertTrue(this.areEqual(this.buildExpectedChilds002(), actual));
	}
	
	@Test(expected = ArgumentException.class)
	public void testLoadChilds_KO01()
	{
		List<Category> actual = this.m_categoryLoader.loadChilds("");
	}
	
	@Test(expected = ArgumentException.class)
	public void testLoadChilds_KO02()
	{
		List<Category> actual =this.m_categoryLoader.loadChilds(null);
	}
	
	private List<Category> buildExpectedMain()
	{
		List<Category> result = new ArrayList<Category>();
		result.add(buildExpectedCategory("001", false, "Categ_0000001", null));
		result.add(buildExpectedCategory("002", false, "Categ_0000002", null));
		result.add(buildExpectedCategory("005", false, "Categ_0000003", null));
		result.add(buildExpectedCategory("018", false, "Categ_0000004", null));
		result.add(buildExpectedCategory("039", false, "Categ_0000005", null));
		return result;		
	}
	
	private List<Category> buildExpectedChilds002()
	{
		List<Category> result = new ArrayList<Category>();
		result.add(buildExpectedCategory("003", true, "Categ_0000021", "002"));
		result.add(buildExpectedCategory("004", true, "Categ_0000022", "002"));
		return result;
	}	
	
	private Category buildExpectedCategory049()
	{
		return buildExpectedCategory("049", false, "Categ_0051111", "048");
	}

	private Category buildExpectedCategory003()
	{
		return buildExpectedCategory("003", true, "Categ_0000021", "002");
	}
	
	private Category buildExpectedCategory(String id, Boolean isLeaf, String name, String parentId)
	{
		Category category = new Category(id, name);
		category.setParent(null); //parent
		//isLeaf?
		return category;
	}
	
	private boolean areEqual(List<Category> categs1, List<Category> categs2)
	{
		boolean result = true;
		
		if(categs1==null && categs2==null)
		{
			result = true;
		}
		else if ((categs1!=null && categs2==null) || (categs1==null && categs2!=null))
		{
			result = false;
		}
		else if (categs1.size() == categs2.size())
		{
			Iterator<Category> iteratorList1 = categs1.iterator();
			Iterator<Category> iteratorList2 = categs2.iterator();
			
			while(iteratorList1.hasNext() && iteratorList2.hasNext() && result)
			{
				result = iteratorList1.next().equals(iteratorList2.next());
			}
		}
		else
		{
			result = false;
		}
	return result;
	}
	
	@Deprecated
	private boolean areEqual(Category c1, Category c2)
	{
		boolean result = (c1.getId().equals(c2.getId()));
		result = result && (c1.isLeaf()==c2.isLeaf());
		result = result && (c1.getName().equals(c2.getName()));
		result = result && (c1.getParent()==c2.getParent());
	return result;
	}
}
