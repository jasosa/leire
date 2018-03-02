package cat.hmobile.leire.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom.Element;
import android.content.Context;

import cat.hmobile.leire.data.xml.CategoriesXmlAccessor;
import cat.hmobile.leire.data.xml.ProductsXmlAccessor;
import cat.hmobile.leire.entities.products.IProductLoader;
import cat.hmobile.leire.entities.products.Product;

public class ProductLoaderXml implements IProductLoader {

	protected static final String CHILD_ID="id";
	protected static final String CHILD_NAME="name";
	protected static final String CHILD_EAN="ean";
	protected static final String CHILD_CATEGORY_ID="categoryId";
	protected static final String CHILD_CATEGORY_NAME="categoryName";
	protected static final String CHILD_BRAND="brand";
	protected static final String CHILD_MANUFACTURER="manufacturer";
	protected static final String CHILD_FIT_TYPE="fitType";
	protected static final String CHILD_DISCHARGE_DATE = "dischargeDate";
	
	public static final String DEFAULT_CATALOG_XML_DATAFILE = "demoData.xml";

	private ProductsXmlAccessor m_productsAccessor;
	
	public ProductLoaderXml(Context ctx, String sourceFile)
	{
		this.m_productsAccessor = new ProductsXmlAccessor(ctx, sourceFile);
	}
	
	//TODO: for test purposes
	public ProductLoaderXml(String sourceFile)
	{
		this.m_productsAccessor = new ProductsXmlAccessor(sourceFile);
	}
	
	@Override
	public Product loadByEAN(String ean) {
		Element xmlProduct = this.m_productsAccessor.getProductByEan(ean);
		return this.buildProduct(xmlProduct);
	}

	@Override
	public Product loadById(String productId) {
		Element xmlProduct = this.m_productsAccessor.getProductById(productId);
		return this.buildProduct(xmlProduct);
	}

	@Override
	public List<Product> loadByCategory(String categoryId) {
		List<Element> xmlProducts = this.m_productsAccessor.getProductListByCategory(categoryId);
		return this.buildProductList(xmlProducts);
	}
	
	@Override
	public List<Product> loadByBrand(String brand) {
		List<Element> xmlProducts = this.m_productsAccessor.getProductListByBrand(brand);
		return this.buildProductList(xmlProducts);
	}

	@Override
	public List<Product> loadAllProducts() {
		List<Element> xmlProducts = this.m_productsAccessor.getProductListByCategory(CategoriesXmlAccessor.ROOT_CATEGORY_ID);
		return this.buildProductList(xmlProducts);
	}

	@Override
	public List<Product> loadByAproxTextSearch(String text) {
		return this.loadByAproxTextSearchAndCategory(text, CategoriesXmlAccessor.ROOT_CATEGORY_ID);
	}
	
	@Override
	public List<Product> loadByAproxTextSearchAndCategory(String text, String categoryId) {
		List<Element> xmlProducts = this.m_productsAccessor.getProductsByAproxTextSearch(text, categoryId);
		return this.buildProductList(xmlProducts);
	}
	
	private List<Product> buildProductList(List<Element> xmlProducts)
	{
		List<Product> result = new ArrayList<Product>();
		
		if(xmlProducts!=null && !xmlProducts.isEmpty())
		{
			Element currentElement;
			for (Iterator<Element> i = xmlProducts.iterator( ); i.hasNext( );)
			{
				currentElement = i.next();
				result.add(this.buildProduct(currentElement));
			}
		}
		return result;
	}
	
	protected Product buildProduct(Element xmlProduct)
	{
		Product result = null;

		if(xmlProduct!=null)
		{
			result = new Product();
			fillProductWithXmlElement(xmlProduct, result);
		}
	
		return result;
	}

	protected void fillProductWithXmlElement(Element xmlProduct, Product result) {
		
		String id = xmlProduct.getChild(CHILD_ID).getText();
		String name = xmlProduct.getChild(CHILD_NAME).getText();
		String ean = xmlProduct.getChild(CHILD_EAN).getText();
		String categoryId = xmlProduct.getChild(CHILD_CATEGORY_ID).getText();
		String categoryName = xmlProduct.getChild(CHILD_CATEGORY_NAME).getText();
		String brand = xmlProduct.getChild(CHILD_BRAND).getText();
		String manufacturer = xmlProduct.getChild(CHILD_MANUFACTURER).getText();
		int fitType = Integer.parseInt(xmlProduct.getChild(CHILD_FIT_TYPE).getText());
	
		String dischargeDate = xmlProduct.getChild(CHILD_DISCHARGE_DATE).getText();;

		result.setId(id);
		result.setName(name);
		result.setBarCode(ean);
		result.setCategoryId(categoryId);
		result.setCategoryName(categoryName);
		result.setBrand(brand);
		result.setManufacturer(manufacturer);
		result.setFitType(fitType);
		result.setDischargeDate(dischargeDate);
	}
}
