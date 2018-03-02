package cat.hmobile.leire.GUI.BackgroundTasks;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import cat.hmobile.leire.GUI.Activities.CatalogActivity;
import cat.hmobile.leire.business.ICatalogNavigator;

public class SubCategoriesLoaderTask extends AsyncTask<String, Void, Void>{
	
	private CatalogActivity m_activity;
	private ICatalogNavigator m_catalog;
	private boolean isCurrentCategoryLeaf;
	
	public SubCategoriesLoaderTask(CatalogActivity activity, ICatalogNavigator catalog){
		m_activity = activity;	
		m_catalog = catalog;
	}
	
	@Override
	protected void onPreExecute(){

	}
	
	@Override
	protected Void doInBackground(String... params) {
		m_catalog.navigateToSubCategoriesOf(params[0]);
		isCurrentCategoryLeaf = m_catalog.isCurrentCategoryLeaf();
		return null;
	}
		
	@Override
	protected void onPostExecute(Void result) {
			
	if(isCurrentCategoryLeaf) {
		m_activity.viewAllProducts();
	}
	else
		m_activity.addSelectedCategoriesToAdapter();
		m_activity.setTitleBar();
	};
}
