package cat.hmobile.leire.entities.products;

import android.content.Context;
import cat.hmobile.leire.data.ProductLoaderXml;
import cat.hmobile.leire.data.ProductUpdateLoaderXml;

public class ProductLoaderFactory {
		
	public static IProductLoader createProductLoader(Context ctx)
	{
		return new ProductLoaderXml(ctx, ProductLoaderXml.DEFAULT_CATALOG_XML_DATAFILE);
	}
	
	public static IProductLoader createProductLoader(Context ctx, String sourceFile, boolean isProductUpdate)
	{
		if(isProductUpdate)
			return new ProductUpdateLoaderXml(ctx, sourceFile);
		else
			return new ProductLoaderXml(ctx, sourceFile);
	}
}
