package cat.hmobile.leire.GUI.BackgroundTasks;

import cat.hmobile.leire.GUI.Activities.CatalogActivity;
import cat.hmobile.leire.GUI.Activities.GeneralCatalogActivity;
import cat.hmobile.leire.GUI.Activities.R;
import cat.hmobile.leire.GUI.Dialogs.LeireProgressDialog;
import cat.hmobile.leire.business.Catalog;
import cat.hmobile.leire.business.ICatalogNavigator;
import cat.hmobile.leire.entities.catalog.CatalogHolder;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class RootCategoriesLoaderTask extends AsyncTask<Void, Void, Void> {

	private CatalogActivity m_activity;
	private LeireProgressDialog m_progDialog;
	private ICatalogNavigator m_catalog;
	
	public RootCategoriesLoaderTask(CatalogActivity activity, ICatalogNavigator catalog){
		m_activity = activity;
		m_catalog = catalog;
	}
	
	@Override
	protected void onPreExecute(){

		m_progDialog = LeireProgressDialog.show(m_activity, "", 
				m_activity.getString(R.string.message_loading), true);
		
	}
	
	@Override
	protected Void doInBackground(Void... params) {
			m_catalog.loadRootCategories();
			return null;
	}
	
	@Override
	protected void onPostExecute(Void result) {
		m_activity.addSelectedCategoriesToAdapter();
		m_activity.setTitleBar();
		m_progDialog.dismiss();
	};
}
