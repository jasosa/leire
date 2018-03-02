package cat.hmobile.leire.GUI.BackgroundTasks;

import java.util.List;

import cat.hmobile.leire.GUI.Activities.ProductListActivity;
import cat.hmobile.leire.GUI.Activities.R;
import cat.hmobile.leire.GUI.Activities.RestaurantsActivity;
import cat.hmobile.leire.GUI.Dialogs.LeireProgressDialog;
import cat.hmobile.leire.business.ICatalogNavigator;
import cat.hmobile.leire.entities.products.Product;
import cat.hmobile.leire.entities.restaurants.IRestaurantLoader;
import cat.hmobile.leire.entities.restaurants.Restaurant;
import android.os.AsyncTask;

public class RestaurantsLoaderTask extends
		AsyncTask<Void, Void, List<Restaurant>> {

	private LeireProgressDialog m_progDialog;
	private RestaurantsActivity m_activity;
	private IRestaurantLoader m_loader;
	
	public RestaurantsLoaderTask(RestaurantsActivity restaurantsListActivity, IRestaurantLoader loader){
		m_activity = restaurantsListActivity;
		m_loader = loader;
	}
	

	@Override
	protected void onPreExecute(){
		m_progDialog = LeireProgressDialog.show(m_activity, "", 
				m_activity.getString(R.string.message_loading), true);
	}
	
	@Override 
	protected List<Restaurant> doInBackground(Void[] arg0) {
		return m_loader.loadAllRestaurants();
	};


	@Override
	protected void onPostExecute(List<Restaurant> result) {
		this.m_activity.viewRestaurants(result);
		m_progDialog.dismiss();
		super.onPostExecute(result);
	}
	
}
