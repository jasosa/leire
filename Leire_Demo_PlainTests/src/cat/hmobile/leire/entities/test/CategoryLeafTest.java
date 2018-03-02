package cat.hmobile.leire.entities.test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import cat.hmobile.leire.entities.categories.Category;
import cat.hmobile.leire.entities.categories.CategoryLeaf;
import cat.hmobile.leire.entities.categories.CategoryNotAllowChildsException;
import cat.hmobile.leire.entities.products.Product;

public class CategoryLeafTest {

	Product m_product;
	Product m_product2;
	@Before
	public void setUp() throws Exception {
		
		m_product = new Product();
		m_product.setBarCode("8097347128234");
		m_product.setId("1");
		m_product.setBrand("Manufacturer XXX");
		m_product.setName("Product ACME");
		
		m_product2 = new Product();
		m_product2.setBarCode("8097347128235");
		m_product2.setId("2");
		m_product2.setBrand("Manufacturer YYY");
		m_product2.setName("Product ACME 2");
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testAddProductToCategory()
	{
		CategoryLeaf cat = new CategoryLeaf("1", "Category 1");
		cat.addProduct(m_product);
		assertEquals(1, cat.getProducts(false).size());
		assertEquals(cat.getId(),m_product.getCategoryId());
	}
	
	@Test
	public void testAddExistentProductToCategory()
	{
		CategoryLeaf cat = new CategoryLeaf("1", "Category 1");
		cat.addProduct(m_product);
		cat.addProduct(m_product);
		assertEquals(1, cat.getProducts(false).size());
		assertEquals(cat.getId(),m_product.getCategoryId());
	}
	
	@Test
	public void testAddProductToSubCategory()
	{
		Category cat = new Category("1", "Category 1");
		CategoryLeaf subcat = new CategoryLeaf("10", "SubCategory 10");
		cat.addChild(subcat);
		subcat.addProduct(m_product);
		assertEquals(1,subcat.getProducts(false).size());
		//assertEquals(1,cat.getProducts(true).size());
	}
	
	@Test(expected = CategoryNotAllowChildsException.class)
	public void testAddSubCategoryToLeafCategory()
	{
		Category cat = new CategoryLeaf("1", "Category 1");
		Category subcat = new Category("10", "SubCategory 10");
		cat.addChild(subcat);
	}
	
	
	@Test
	public void testRemoveProductFromCategory()
	{
		CategoryLeaf cat = new CategoryLeaf("1", "Category 1");
		cat.addProduct(m_product2);
		cat.addProduct(m_product);
		cat.removeProduct(m_product2);
		assertEquals(1,cat.getProducts(true).size());
	}
	
	@Test
	public void testRemoveProductFromSubCategory()
	{
		Category cat = new Category("1", "Category 1");
		CategoryLeaf subcat = new CategoryLeaf("10", "SubCategory 10");
		cat.addChild(subcat);
		//cat.AddProduct(m_product2);
		subcat.addProduct(m_product);
		subcat.removeProduct(m_product);
		//assertEquals(1,cat.getProducts(true).size());
		assertEquals(0,subcat.getProducts(false).size());
	}
	
	@Test
	public void testIsLeafCategory_True()
	{
		Category LeafCategory = new CategoryLeaf("1", "Category Parent");
		assertEquals(true, LeafCategory.isLeaf());
	}
	
}
