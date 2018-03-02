package cat.hmobile.leire.GUI.Activities;

import android.content.Intent;
import android.os.Bundle;
import cat.hmobile.leire.GUI.ActivityStarters.ProductsListActivityStarter;
import cat.hmobile.leire.GUI.ActivityStarters.ProductsUpdateListActivityStarter;
import cat.hmobile.leire.GUI.Helpers.UpdateBundleHelper;
import cat.hmobile.leire.business.Catalog;
import cat.hmobile.leire.entities.catalog.CatalogHolder;
import cat.hmobile.leire.entities.categories.CategoryLoaderFactory;
import cat.hmobile.leire.entities.products.ProductLoaderFactory;

public class UpdateCatalogActivity extends CatalogActivity {

    private static final String LOG_TAG = "UpdateCatalogActivity";

	private String m_updateDate;
	private String m_updateFilePath;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.categories);
		getInfoFromBundle();
		this.initializeCatalog(this.m_updateFilePath);
		this.initializeAdapter();
		this.initializeNavigation();
		this.initializeLayoutElements();
		this.handleIntent(getIntent());
	}
	
	@Override
	public void setTitleBar() {
		
		this.setTitle(String.format(getString(R.string.title_update_date), m_updateDate));
	}
	
	protected void initializeCatalog(String catalogSource) {
		m_catalog =  new Catalog(CategoryLoaderFactory.createCategoryLoader(this, catalogSource),
						 		 ProductLoaderFactory.createProductLoader(this, catalogSource, true));
		CatalogHolder.setSearchableCatalog(m_catalog);
		ALLPRODUCTS_CATEGORY_NAME = getString(R.string.general_text_allproducts);
	}
	
	private void getInfoFromBundle() {
		
		if(getIntent().getStringExtra(UpdateBundleHelper.UPDATE_DATE) != null){
			m_updateDate = getIntent().getStringExtra(UpdateBundleHelper.UPDATE_DATE);
		}
		if(getIntent().getStringExtra(UpdateBundleHelper.CATALOG_SOURCE) != null){
			m_updateFilePath = getIntent().getStringExtra(UpdateBundleHelper.CATALOG_SOURCE);
		}
	}
	 
	@Override
	public void viewAllProducts() {
		ProductsUpdateListActivityStarter.getInstance().startProductListActivityForViewCategoryProducts(
				this, m_catalog.getCurrentCategoryName(), m_catalog.getCurrentCategoryId());

	}
	 
	@Override
	protected void doTheSearch(String query) {
		ProductsUpdateListActivityStarter.getInstance().startProductListActivityForSearch(
				this, m_catalog.getCurrentCategoryName(), 
				m_catalog.getCurrentCategoryId(),
				query);

	}
	
}
