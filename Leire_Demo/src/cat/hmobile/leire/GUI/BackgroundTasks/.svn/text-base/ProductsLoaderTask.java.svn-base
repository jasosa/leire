package cat.hmobile.leire.GUI.BackgroundTasks;

import java.util.List;

import cat.hmobile.leire.GUI.Activities.CatalogActivity;
import cat.hmobile.leire.GUI.Activities.GeneralCatalogActivity;
import cat.hmobile.leire.GUI.Activities.ProductListActivity;
import cat.hmobile.leire.GUI.Activities.R;
import cat.hmobile.leire.GUI.Activities.UpdateCatalogActivity;
import cat.hmobile.leire.GUI.Dialogs.DialogMessage;
import cat.hmobile.leire.GUI.Dialogs.LeireProgressDialog;
import cat.hmobile.leire.business.ICatalogNavigator;
import cat.hmobile.leire.entities.products.Product;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

public class ProductsLoaderTask extends AsyncTask<String, Void, List<Product>> {

	private LeireProgressDialog m_progDialog;
	private ProductListActivity m_activity;
	private ICatalogNavigator m_catalog;
	
	public ProductsLoaderTask(ProductListActivity productListActivity, ICatalogNavigator catalog){
		m_activity = productListActivity;	
		m_catalog = catalog;
	}
	

	@Override
	protected void onPreExecute(){
		m_progDialog = LeireProgressDialog.show(m_activity, "", 
				m_activity.getString(R.string.message_loading), true);
	}
	
	@Override
	protected List<Product> doInBackground(String... paramArrayOfParams) {
		String categoryId = paramArrayOfParams[0];
		
		if(askingForCurrentCategory(categoryId)){
			return m_catalog.getProductsOfCurrentCategory();
		}
		else
		{
			return m_catalog.getProductsByCategory(categoryId);
		}
	}


	private boolean askingForCurrentCategory(String categoryId) {
		return categoryId.equals(m_catalog.getCurrentCategoryId());
	}

	@Override
	protected void onPostExecute(List<Product> result) {
		this.m_activity.viewProducts(result);
		m_progDialog.dismiss();
		super.onPostExecute(result);
	}
}
