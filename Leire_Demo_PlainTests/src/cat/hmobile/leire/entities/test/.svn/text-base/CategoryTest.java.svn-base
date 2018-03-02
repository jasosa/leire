package cat.hmobile.leire.entities.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.content.OperationApplicationException;

import cat.hmobile.leire.entities.categories.Category;
import cat.hmobile.leire.entities.products.Product;

public class CategoryTest {
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	
	@Test
	public void testCreateCategoryWithoutParent()
	{
		Category cat = new Category("1", "Category 1");
		assertEquals(null, cat.getParent());
	}
	
	@Test
	public void testCreateCategoryWithParent()
	{	
		Category parentCategory = new Category("1", "Category Parent");
		Category childCategory = new Category("10", "Child category", parentCategory);
		assertEquals(parentCategory, childCategory.getParent());
	}
	
	@Test
	public void testAddChildToCategory()
	{
		Category parentCategory = new Category("1", "Category Parent");
		Category categoryWithoutParent = new Category("10", "Category without parent");
		parentCategory.addChild(categoryWithoutParent);
		assertEquals(parentCategory, categoryWithoutParent.getParent());
		assertEquals(1,parentCategory.getChilds().size());
	}
	
	@Test
	public void testExistentChildToCategory() 
	{
		Category parentCategory = new Category("1", "Category Parent");
		Category categoryWithoutParent = new Category("10", "Category without parent");
		parentCategory.addChild(categoryWithoutParent);
		parentCategory.addChild(categoryWithoutParent);
		assertEquals(parentCategory, categoryWithoutParent.getParent());
		assertEquals(1,parentCategory.getChilds().size());	
	}
	
	@Test
	public void testRemoveNonExistentChildFromcategory() 
	{
		Category parentCategory = new Category("1", "Category Parent");
		Category childCategory1 = new Category("10", "Category without parent");
		Category childCategory2 = new Category("20", "Child category 2");
		parentCategory.addChild(childCategory1);
		parentCategory.removeChild(childCategory2);
		assertEquals(parentCategory, childCategory1.getParent());
		assertEquals(1,parentCategory.getChilds().size());
	}
	
	@Test
	public void testRemoveChildFromcategory()
	{
		Category parentCategory = new Category("1", "Category Parent");
		Category childCategory1 = new Category("10", "Category without parent");
		parentCategory.addChild(childCategory1);
		parentCategory.removeChild(childCategory1);
		assertEquals(0,parentCategory.getChilds().size());
	}
	
	@Test
	public void testIsLeafCategory_False()
	{
		Category noLeafCategory = new Category("1", "Category Parent");
		assertEquals(false, noLeafCategory.isLeaf());
	}
}
