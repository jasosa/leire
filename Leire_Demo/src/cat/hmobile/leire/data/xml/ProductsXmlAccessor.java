package cat.hmobile.leire.data.xml;

import java.util.List;

import org.jdom.Element;
import android.content.Context;

import cat.hmobile.leire.GUI.Activities.R;
import cat.hmobile.leire.entities.ArgumentException;

public class ProductsXmlAccessor extends XmlAccessor {
	
	private static final String QUERY_SELECT_PRODUCT_BY_ID = "//*/products/product[id='%1$s']";
	private static final String QUERY_SELECT_PRODUCT_BY_EAN = "//*/products/product[ean='%1$s']";
	private static final String QUERY_SELECT_PRODUCTS_BY_BRAND = "//*/category[isLeaf='true']//product[brand='%1$s']";
	private static final String QUERY_SELECT_PRODUCTS_BY_CATEGORY = "//*/category[id='%1$s']//product";
	private static final String QUERY_SELECT_PRODUCTS_BY_CATEGORY_AND_APROX_TEXT = 
		"//*/category[id='%1$s']//product[name[contains(lower-case(text()),'%2$s')] or " +
	                                      "brand[contains(lower-case(text()),'%2$s')] or " + 
                                          "categoryName[contains(lower-case(text()),'%2$s')]]";
	
	public ProductsXmlAccessor(Context ctx) {
		super(ctx);
	}
	
	public ProductsXmlAccessor(Context ctx, String sourcefile){
		super(ctx, sourcefile);
	}
	
	//TODO: Constructor creat pel testeig
	public ProductsXmlAccessor()
	{
		super();
	}
	
	//TODO: Constructor creat pel testeig
	public ProductsXmlAccessor(String sourceFile)
	{
		super(sourceFile);
	}
	
	
	public Element getProductByEan(String ean) {
		if(ean==null || ean.length() == 0)
			throw new ArgumentException(R.string.error_illegal_argument);
		
		String xpath = addParameterToXpath(QUERY_SELECT_PRODUCT_BY_EAN,ean);
		return this.getNode(xpath);
	}

	public Element getProductById(String productId) {
		if(productId==null || productId.length() == 0)
			throw new ArgumentException(R.string.error_illegal_argument);
		
		String xpath = addParameterToXpath(QUERY_SELECT_PRODUCT_BY_ID,productId);
		return this.getNode(xpath);
	}

	public List<Element> getProductListByCategory(String categoryId) {
		if(categoryId==null || categoryId.length() == 0)
			throw new ArgumentException(R.string.error_illegal_argument);
		
		String xpath = addParameterToXpath(QUERY_SELECT_PRODUCTS_BY_CATEGORY,categoryId);
		return this.getNodeList(xpath);
	}

	public List<Element> getProductListByBrand(String brand) {
		if(brand==null || brand.length() == 0)
			throw new ArgumentException(R.string.error_illegal_argument);
		
		String xpath = addParameterToXpath(QUERY_SELECT_PRODUCTS_BY_BRAND,brand);
		return this.getNodeList(xpath);
	}
	
	public List<Element> getProductsByAproxTextSearch(String text, String categoryId) {
		if(text==null || text.length() == 0)
			throw new ArgumentException(R.string.error_illegal_argument);
	
		String xpath = addParameterToXpath(QUERY_SELECT_PRODUCTS_BY_CATEGORY_AND_APROX_TEXT, categoryId, text.toLowerCase());
		return this.getNodeList(xpath);
	}
}
