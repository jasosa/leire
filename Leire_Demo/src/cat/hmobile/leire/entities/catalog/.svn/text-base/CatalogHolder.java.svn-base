package cat.hmobile.leire.entities.catalog;

import android.content.Context;
import cat.hmobile.leire.business.Catalog;
import cat.hmobile.leire.business.ICatalogNavigator;
import cat.hmobile.leire.entities.categories.CategoryLoaderFactory;
import cat.hmobile.leire.entities.products.ProductLoaderFactory;

public class CatalogHolder {

	private static ICatalogNavigator m_generalCatalog;
	private static ICatalogNavigator m_searchableCatalog;
	private static Context m_context;
	
	public static void initializeGeneralCatalog (Context ctx){
		m_context = ctx;
	}
	
	public static ICatalogNavigator getGeneralCatalog(){
		if(m_generalCatalog == null){
			if(m_context == null ) throw new CatalogNotInitializedException();	
			m_generalCatalog =  new Catalog(CategoryLoaderFactory.createCategoryLoader(m_context), ProductLoaderFactory.createProductLoader(m_context));
		}
		
		return m_generalCatalog;
	}
	
	public static ICatalogNavigator getSearchableCatalog(){
		return m_searchableCatalog;
	}
	
	public static void setSearchableCatalog(ICatalogNavigator searchableCatalog){
		 m_searchableCatalog = searchableCatalog;
	}
}
