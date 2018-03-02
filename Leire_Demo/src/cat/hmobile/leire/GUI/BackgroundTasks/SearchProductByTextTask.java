package cat.hmobile.leire.GUI.BackgroundTasks;

import java.util.List;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import cat.hmobile.leire.GUI.Activities.ProductListActivity;
import cat.hmobile.leire.GUI.Activities.R;
import cat.hmobile.leire.GUI.Dialogs.LeireProgressDialog;
import cat.hmobile.leire.business.ICatalogNavigator;
import cat.hmobile.leire.entities.products.Product;

public class SearchProductByTextTask extends AsyncTask<String, Void, List<Product>>{

	private LeireProgressDialog m_progDialog;
	private ProductListActivity m_activity;
	private ICatalogNavigator m_catalog;

	public SearchProductByTextTask(ProductListActivity productListActivity, ICatalogNavigator catalog) {
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
		List<Product> products = m_catalog.getProductsByAproxText(paramArrayOfParams[0]);
		return products;
	}
	
	@Override
	protected void onPostExecute(List<Product> result) {
		m_activity.viewProducts(result); 
		m_progDialog.dismiss();
		super.onPostExecute(result);
	}
	
}
