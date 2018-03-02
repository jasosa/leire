package cat.hmobile.leire.data.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom.Element;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cat.hmobile.leire.data.ProductLoaderXml;
import cat.hmobile.leire.entities.ArgumentException;
import cat.hmobile.leire.entities.products.Product;

public class ProductLoaderXmlTest {

	private static final String BRAND_AUCHAN = "Auchan";
	private static final String BRAND_NOT_EXISTS = "dfgdfsgdsfg";
	private ProductLoaderXml m_productLoader;
	
	@Before
	public void setUp() throws Exception {
		this.m_productLoader = new ProductLoaderXml(ProductLoaderXml.DEFAULT_CATALOG_XML_DATAFILE);
	}

	@Test
	public void testLoadByEAN_OK01() {
		Product product = this.m_productLoader.loadByEAN("0150000000003");
		Assert.assertTrue(this.buildExpectedProduct01().equals(product));
	}
	
	@Test
	public void testLoadByEAN_KO01() {
		Product product = this.m_productLoader.loadByEAN("0190000000003");
		Assert.assertNull(product);
	}
	
	@Test
	public void testLoadByEAN_KO02() {
		try
		{
			Product product = this.m_productLoader.loadByEAN(null);
			Assert.fail();
		}
		catch(ArgumentException e)
		{}
		catch(Exception e)
		{
			Assert.fail();
		}
	}
	
	@Test
	public void testLoadById_OK01() {
		Product product = this.m_productLoader.loadById("003");
		Assert.assertTrue(this.buildExpectedProduct01().equals(product));
	}
	
	@Test
	public void testLoadById_KO01() {
		Product product = this.m_productLoader.loadById("aaaa");
		Assert.assertNull(product);
	}
	
	@Test
	public void testLoadById_KO02() {
		try
		{
			Product product = this.m_productLoader.loadById(null);
			Assert.fail();
		}
		catch(ArgumentException e)
		{}
		catch(Exception e)
		{
			Assert.fail();
		}
	}
	
	
	@Test
	public void testLoadByCategory_OK01() {
		List<Product> products = this.m_productLoader.loadByCategory("050");
		Assert.assertTrue(this.areEqual(products, this.buildExpectedProductList()));
	}
	
	@Test
	public void testLoadByCategory_KO01() {
		List<Product> products = this.m_productLoader.loadByCategory("aaa");
		Assert.assertTrue(products.isEmpty());
	}
	
	@Test
	public void testLoadByCategory_KO02() {
		try
		{
			List<Product> products = this.m_productLoader.loadByCategory(null);
			Assert.fail();
		}
		catch(ArgumentException e)
		{}
		catch(Exception e)
		{
			Assert.fail();
		}
	}
	
	@Test
	public void testLoadByBrand_OK01(){
		List<Product> products = this.m_productLoader.loadByBrand(BRAND_AUCHAN);
		assertEquals(13,products.size());	
	}
	
	@Test
	public void testLoadByBrand_KO01(){
		List<Product> products = this.m_productLoader.loadByBrand(BRAND_NOT_EXISTS);
		assertEquals(0,products.size());	
	}
	
	@Test
	public void loadByAproxTextSearch_OK01() {
		List<Product> products = this.m_productLoader.loadByAproxTextSearchAndCategory("aaa", "000");
		Assert.assertTrue(products.isEmpty());
	}
	
	@Test
	public void loadByAproxTextSearch_OK02() {
		List<Product> products = this.m_productLoader.loadByAproxTextSearchAndCategory("Croi", "002");
		Assert.assertEquals(2, products.size());
	}
	
	@Test
	public void loadByAproxTextSearch_OK03() {
		List<Product> products = this.m_productLoader.loadByAproxTextSearch("Categ_");
		Assert.assertEquals(366, products.size());
	}
	
	@Test
	public void loadByAproxTextSearch_OK04() {
		List<Product> products = this.m_productLoader.loadByAproxTextSearch("categ_");
		Assert.assertEquals(366, products.size());
	}
	
	@Test
	public void loadByAproxTextSearch_OK05() {
		List<Product> products = this.m_productLoader.loadByAproxTextSearch("Pa");
		Assert.assertEquals(90, products.size());
	}
	
	//Retorna el product 003
	private Product buildExpectedProduct01()
	{
		return this.buildExpectedProduct("003", "Bollos de cacao", "0150000000003", "003", "Categ_0000021", 
				"Alcampo", BRAND_AUCHAN, Product.FITTYPE_FACE, "10/10/2010");
	}
	
	private Product buildExpectedProduct02()
	{
		return this.buildExpectedProduct("370", "Tartisin casera", "0150000000370", "050", "Categ_0051112", 
				"TEBA El Catering Sin", "Teba", Product.FITTYPE_BRAND, "10/10/2010");
	}
	
	private Product buildExpectedProduct(String id,
			String name,
			String ean,
			String categoryId,
			String categoryName,
			String manufacturer,
			String brand,
			int fitType,
			String dischargeDate)
	{
			Product product = new Product();
			
			product.setId(id);
			product.setName(name);
			product.setBarCode(ean);
			product.setCategoryId(categoryId);
			product.setCategoryName(categoryName);
			product.setManufacturer(manufacturer);
			product.setBrand(brand);
			product.setFitType(fitType);
			product.setDischargeDate(dischargeDate);

			return product;
	}
	
	private List<Product> buildExpectedProductList()
	{
		List<Product> result = new ArrayList<Product>();
		result.add(this.buildExpectedProduct02());
		
		return result;
	}
	
	private boolean areEqual(List<Product> prods1, List<Product> prods2)
	{
		boolean result = true;
		
		if(prods1==null && prods2==null)
		{
			result = true;
		}
		else if ((prods1!=null && prods2==null) || (prods1==null && prods2!=null))
		{
			result = false;
		}
		else if (prods1.size() == prods2.size())
		{
			Iterator<Product> iteratorList1 = prods1.iterator();
			Iterator<Product> iteratorList2 = prods2.iterator();
			
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
	private boolean areEqual(Product p1, Product p2)
	{
		boolean result = (p1.getId().equals(p2.getId()));
		result = result && (p1.getBrand().equals(p2.getBrand()));
		result = result && (p1.getBarCode().equals(p2.getBarCode()));
		result = result && (p1.getName().equals(p2.getName()));
	return result;
	}
}
