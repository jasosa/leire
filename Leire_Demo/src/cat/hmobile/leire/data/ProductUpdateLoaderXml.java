package cat.hmobile.leire.data;

import org.jdom.Element;

import android.content.Context;
import cat.hmobile.leire.entities.products.IProductLoader;
import cat.hmobile.leire.entities.products.Product;
import cat.hmobile.leire.entities.products.ProductUpdate;

public class ProductUpdateLoaderXml extends ProductLoaderXml implements
		IProductLoader {

	public ProductUpdateLoaderXml(Context ctx, String sourceFile) {
		super(ctx, sourceFile);
	}
	
	//ONLY FOR TEST
	public ProductUpdateLoaderXml(String sourceFile) {
		super(sourceFile);
	}
	
	@Override
	protected Product buildProduct(Element xmlProduct)
	{
		ProductUpdate result = null;

		if(xmlProduct!=null)
		{
			result = new ProductUpdate();
			fillProductWithXmlElement(xmlProduct, result);
		}
	
		return result;
	}

}
