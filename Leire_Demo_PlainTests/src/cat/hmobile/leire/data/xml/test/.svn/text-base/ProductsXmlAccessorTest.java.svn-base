package cat.hmobile.leire.data.xml.test;

import java.util.List;

import org.jdom.Element;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import cat.hmobile.leire.data.xml.ProductsXmlAccessor;
import cat.hmobile.leire.entities.ArgumentException;

public class ProductsXmlAccessorTest {

	private static final String CHILD_ID="id";
	private static final String CHILD_NAME="name";
	private static final String CHILD_EAN="ean";
	private static final String CHILD_CATEGORY_ID="categoryId";
	private static final String CHILD_CATEGORY_NAME="categoryName";
	private static final String CHILD_BRAND="brand";
	private static final String CHILD_MANUFACTURER="manufacturer";
	private static final String CHILD_FIT_TYPE="fitType";
	private static final String CHILD_DISHARGE_DATE = "dischargeDate";
	
	private ProductsXmlAccessor m_products;
	
	@Before
	public void setUp() throws Exception {
		this.m_products = new ProductsXmlAccessor();
	}
	
	@Test(expected = ArgumentException.class)
	public void testGetProductByEanEmpty() {
		Element product = this.m_products.getProductByEan("");
	}
	
	@Test
	public void testGetProductByEanError() {
		Element product = this.m_products.getProductByEan("Manel");
		Assert.assertNull(product);
	}

	@Test
	public void testGetProductByEanOk() {
		Element product = this.m_products.getProductByEan("0150000000370");
		this.AssertProduct(product, "370", "Tartisin casera", "0150000000370", "050", "Categ_0051112", "TEBA El Catering Sin", "Teba", "02", "10/10/2010");
	}

	@Test
	public void testGetProductByIdOk() {
		Element product = this.m_products.getProductById("370");
		this.AssertProduct(product, "370", "Tartisin casera", "0150000000370", "050", "Categ_0051112", "TEBA El Catering Sin", "Teba", "02", "10/10/2010");
	}
	
	@Test
	public void testGetProductListByCategoryOk050() {
		List products = this.m_products.getProductListByCategory("050");
		Assert.assertEquals(1,products.size());
	}
	
	@Test
	public void testGetProductListByCategoryOk003() {
		List products = this.m_products.getProductListByCategory("003");
		Assert.assertEquals(11,products.size());
	}

	@Test
	public void testGetProductListByBrandOk01() {
		List products = this.m_products.getProductListByBrand("Auchan");
		Assert.assertEquals(13,products.size());
	}

	@Test
	public void testGetProductListByBrandOk02() {
		List products = this.m_products.getProductListByBrand("Auflm");
		Assert.assertEquals(0,products.size());
	}
	
	private void AssertProduct(Element product,
									String id,
									String name,
									String ean,
									String categoryId,
									String categoryName,
									String manufacturer,
									String brand,
									String fitType,
									String dischargeRate)
	{
		Assert.assertEquals(id, product.getChild(CHILD_ID).getText());
		Assert.assertEquals(name,product.getChild(CHILD_NAME).getText());
		Assert.assertEquals(ean,product.getChild(CHILD_EAN).getText());
		Assert.assertEquals(categoryId,product.getChild(CHILD_CATEGORY_ID).getText());
		Assert.assertEquals(categoryName,product.getChild(CHILD_CATEGORY_NAME).getText());
		Assert.assertEquals(manufacturer,product.getChild(CHILD_MANUFACTURER).getText());
		Assert.assertEquals(brand,product.getChild(CHILD_BRAND).getText());
		Assert.assertEquals(fitType,product.getChild(CHILD_FIT_TYPE).getText());
		Assert.assertEquals(dischargeRate,product.getChild(CHILD_DISHARGE_DATE).getText());
	}
}
