package cat.hmobile.leire.GUI.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import cat.hmobile.leire.entities.catalog.CatalogHolder;

public class GeneralCatalogActivity extends CatalogActivity{

    private static final String LOG_TAG = "GeneralCatalogActivity";

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.categories);
		this.initializeCatalog();
		this.initializeAdapter();
		this.initializeNavigation();
		this.initializeLayoutElements();
		this.handleIntent(getIntent());
	}
	
	protected void initializeCatalog() {
		m_catalog =  CatalogHolder.getGeneralCatalog();
		CatalogHolder.setSearchableCatalog(m_catalog);
		ALLPRODUCTS_CATEGORY_NAME = getString(R.string.general_text_allproducts);
	}
	
	@Override
	public void setTitleBar() {
		
		if(weAreInRootCategory()){
			
			m_currentCategory.setText(R.string.leireCatalog);
		}
		else
			m_currentCategory.setText(m_catalog.getCurrentCategoryName());		
	}
}
