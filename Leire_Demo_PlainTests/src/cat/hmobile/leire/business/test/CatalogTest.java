package cat.hmobile.leire.business.test;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cat.hmobile.leire.business.Catalog;
import cat.hmobile.leire.data.CategoryLoaderXml;
import cat.hmobile.leire.data.ProductLoaderXml;
import cat.hmobile.leire.entities.barcode.BarCodeException;
import cat.hmobile.leire.entities.categories.CategoryNotFoundException;
import cat.hmobile.leire.entities.products.BrandNotFoundException;
import cat.hmobile.leire.entities.products.Product;
import static org.junit.Assert.*;


public class CatalogTest extends Object {
	
	private static final int NUM_CATEGORIES = 5;
	private static final String CATEGORY_2_ID = "002";
	private static final String CATEGORY_1_ID = "001";
	private static final String CATEGORY_3_ID = "005";
	private static final int CATEGORY_2_NUM_SUBCATEGORIES = 2;
	private static final int CATEGORY_3_NUM_SUBCATEGORIES = 3;
	private static final String CATEGORY_33_ID = "008";
	private static final String CATEGORY_331_ID = "015";
	private static final int TOTAL_PRODUCTS = 366;
	private static final String NON_EXISTENT_CATEGORY = "154678";
	private static final String BRAND_1 = "Auchan";
	private static final String NON_EXISTENT_BRAND = "Mauchan";
	
	Catalog catalog;
	
	public CatalogTest() {
		super();
	}

	@Before
	public void setUp() throws Exception {
		catalog = new Catalog(new CategoryLoaderXml(CategoryLoaderXml.DEFAULT_CATALOG_XML_DATAFILE),  new ProductLoaderXml(ProductLoaderXml.DEFAULT_CATALOG_XML_DATAFILE));
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testLoadParentCategories() throws Exception
	{
		catalog.loadRootCategories();
		assertEquals(NUM_CATEGORIES,catalog.getCurrentSubCategories().size());
	}
	
	@Test
	public void testNavigateToSubCategory() throws Exception
	{
		catalog.loadRootCategories();
		catalog.navigateToSubCategoriesOf(CATEGORY_2_ID);
		assertEquals(CATEGORY_2_NUM_SUBCATEGORIES,catalog.getCurrentSubCategories().size());
	}
	
	@Test (expected = CategoryNotFoundException.class)
	public void testNavigateToCategoryThatNoExists()
	{
		catalog.loadRootCategories();
		catalog.navigateToSubCategoriesOf(CATEGORY_1_ID);
		catalog.navigateToSubCategoriesOf("11");
	}
	
	@Test
	public void testNavigateToYetLoadedSubCategory_11()
	{
		catalog.loadRootCategories();
		catalog.navigateToSubCategoriesOf(CATEGORY_2_ID);
		catalog.navigateToSubCategoriesOf(CATEGORY_2_ID);
		assertEquals(CATEGORY_2_NUM_SUBCATEGORIES,catalog.getCurrentSubCategories().size());
	}
	
	@Test
	public void testNavigateToAnotherSubCategory_11()
	{
		catalog.loadRootCategories();
		catalog.navigateToSubCategoriesOf(CATEGORY_2_ID);
		catalog.navigateToParentCategory();
		catalog.navigateToSubCategoriesOf(CATEGORY_3_ID);
		assertEquals(CATEGORY_3_NUM_SUBCATEGORIES,catalog.getCurrentSubCategories().size());
	}
	
	@Test
	public void testNavigateToParent()
	{
		catalog.loadRootCategories();
		catalog.navigateToSubCategoriesOf(CATEGORY_3_ID);
		catalog.navigateToParentCategory();
		assertEquals(NUM_CATEGORIES,catalog.getCurrentSubCategories().size());	
	}
	
	@Test
	public void testNavigateToParentInParentCategories()
	{
		catalog.loadRootCategories();
		catalog.navigateToParentCategory();
		assertEquals(NUM_CATEGORIES,catalog.getCurrentSubCategories().size());
	}
	
	@Test
	public void testViewProductsFromLeafCategory()
	{
		catalog.loadRootCategories();
		catalog.navigateToSubCategoriesOf(CATEGORY_3_ID);
		catalog.navigateToSubCategoriesOf(CATEGORY_33_ID);
		catalog.navigateToSubCategoriesOf(CATEGORY_331_ID);
		List<Product> productsOfCategory = catalog.getProductsOfCurrentCategory();
		assertEquals(22,productsOfCategory.size());
	}
	
	@Test
	public void testViewAllProductsFromRoot()
	{
		catalog.loadRootCategories();
		List<Product> productsOfCategory = catalog.getProductsOfCurrentCategory();
		assertEquals(TOTAL_PRODUCTS,productsOfCategory.size());
	}
	
	@Test
	public void testViewProductsFromEmptyCategory()
	{
		catalog.loadRootCategories();
		catalog.navigateToSubCategoriesOf("001");
		List<Product> productsOfCategory = catalog.getProductsOfCurrentCategory();
		assertEquals(0,productsOfCategory.size());
	}
	
	@Test
	public void testSearchFromRoot()
	{
		catalog.loadRootCategories();
		List<Product> productsOfCategory = catalog.getProductsByAproxText("Categ_");
		assertEquals(TOTAL_PRODUCTS,productsOfCategory.size());
	}
	
	@Test(expected=BarCodeException.class)
	public void testGetProductByEan_KO01()
	{
		catalog.getProductByEan("9501101020961");
	}
	
	@Test
	public void testGetProductByEan_OK01()
	{
		Product expected = this.buildProduct("002", 2, "Base de pizza", "0150000000002", "Alcampo", "Auchan", "003", "Categ_0000021", "10/10/2010");
		Product actual = catalog.getProductByEan("0150000000002");
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetProductsByCategory(){
		catalog.loadRootCategories();
		List<Product> productsOfCategory = catalog.getProductsByCategory(CATEGORY_331_ID);
		assertEquals(22,productsOfCategory.size());
	}
	
	@Test(expected=CategoryNotFoundException.class)
	public void testGetProductsByNonExistentCategory(){
		catalog.loadRootCategories();
		List<Product> productsOfCategory = catalog.getProductsByCategory(NON_EXISTENT_CATEGORY);
	}
	
	@Test
	public void testGetProductsByBrand(){
		catalog.loadRootCategories();
		List<Product> productsOfBrand = catalog.getProductsByBrand(BRAND_1);
		assertEquals(13,productsOfBrand.size());
	}
	
	@Test(expected=BrandNotFoundException.class)
	public void testGetProductsByNonExistentBrand(){
		catalog.loadRootCategories();
		List<Product> productsOfCategory = catalog.getProductsByBrand(NON_EXISTENT_BRAND);
	}
	

	private Product buildProduct(String id, int fitType, String name,
			String ean, String manufacturer, String brand, String categoryId,
			String categoryName, String dischargeDate)
	{
		Product result = new Product();
		
		result.setId(id);
		result.setFitType(fitType);
		result.setName(name);
		result.setBarCode(ean);
		result.setManufacturer(manufacturer);
		result.setBrand(brand);
		result.setCategoryId(categoryId);
		result.setCategoryName(categoryName);
		result.setDischargeDate(dischargeDate);

		return result;
	}
}
