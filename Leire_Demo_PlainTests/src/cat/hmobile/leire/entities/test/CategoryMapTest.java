package cat.hmobile.leire.entities.test;


import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cat.hmobile.leire.entities.categories.Category;
import cat.hmobile.leire.entities.categories.CategoryMap;
import cat.hmobile.leire.entities.categories.CategoryNotFoundException;

public class CategoryMapTest extends Object {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	
	@Test
	public void testAddMainCategoryToMap()
	{
		CategoryMap map = new CategoryMap();
		Category cat = new Category("1", "Category 1", null);
		map.add(cat);
		assertEquals(1, map.getMainCategoriesCount());
	}
	
	@Test
	public void testAddChildCategoryToMap()
	{
		CategoryMap map = new CategoryMap();
		Category cat = new Category("1", "Category 1", null);
		map.add(cat);
		Category childCat = new Category("10", "Category 10", cat);
		map.add(childCat);
		assertEquals(1, map.getCategory("1").getChilds().size());
	}
	
	@Test(expected = CategoryNotFoundException.class)
	public void testGetNonExistentCategory()
	{
		CategoryMap map = new CategoryMap();
		map.getCategory("10");
	}
	
	@Test
	public void testGetMainCategories()
	{
		CategoryMap map = new CategoryMap();
		Category cat = new Category("1", "Category 1", null);
		map.add(cat);
		Category childCat = new Category("10", "Category 10", cat);
		map.add(childCat);
		assertEquals(1,map.getMainCategories().size());
	}
}
