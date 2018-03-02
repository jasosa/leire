package cat.hmobile.leire.entities.categories;

import android.content.Context;
import cat.hmobile.leire.data.CategoryLoaderXml;

public class CategoryLoaderFactory {
		
	public static ICategoryLoader createCategoryLoader(Context ctx)
	{
		return new CategoryLoaderXml(ctx, CategoryLoaderXml.DEFAULT_CATALOG_XML_DATAFILE);
	}
	
	public static ICategoryLoader createCategoryLoader(Context ctx, String sourceFile)
	{
		return new CategoryLoaderXml(ctx, sourceFile);
	}
}
