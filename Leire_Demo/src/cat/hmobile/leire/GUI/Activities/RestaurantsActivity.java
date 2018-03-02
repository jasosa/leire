package cat.hmobile.leire.GUI.Activities;

import java.util.ArrayList;
import java.util.List;

import cat.hmobile.leire.GUI.Adapters.MicrobloggingMessageListAdapter;
import cat.hmobile.leire.GUI.Adapters.RestaurantListAdapter;
import cat.hmobile.leire.GUI.BackgroundTasks.RestaurantsLoaderTask;
import cat.hmobile.leire.GUI.Helpers.ActivityRequests;
import cat.hmobile.leire.GUI.Helpers.ActivityResults;
import cat.hmobile.leire.entities.microblogging.IMicrobloggingMessage;
import cat.hmobile.leire.entities.restaurants.Restaurant;
import cat.hmobile.leire.entities.restaurants.RestaurantLoaderFactory;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class RestaurantsActivity extends GeneralActivity implements OnItemClickListener {
	
	ListView m_restaurantsLineList;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{  
		super.onCreate(savedInstanceState);	
		this.setContentView(R.layout.restaurantslist);
		initializeCommonLayoutElements();
		initializeAdapter();
		loadRestaurants();
	}
	
	

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_restaurants_list, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId())
		{
		case R.id.menuitem_viewInMap:
			goToMapActivity();
			break;
		}

		return super.onOptionsItemSelected(item);
	}
	
	private void goToMapActivity() {
		Intent i = new Intent(this, RestaurantsMapActivity.class);
	    this.startActivity(i);
	}

	private void loadRestaurants() {
		RestaurantsLoaderTask task = new RestaurantsLoaderTask(this, RestaurantLoaderFactory.createRestaurantLoader());
		task.execute();
	}
	
	private void initializeAdapter() {
		m_restaurantsLineList = (ListView) findViewById(R.id.restaurantsListView);
		RestaurantListAdapter adapter = new RestaurantListAdapter(this, R.layout.restaurantrow, new ArrayList<Restaurant>());
		m_restaurantsLineList.setOnItemClickListener(this);
		m_restaurantsLineList.setAdapter(adapter);
	}

	public void viewRestaurants(List<Restaurant> result) {
		RestaurantListAdapter adapter = (RestaurantListAdapter) m_restaurantsLineList.getAdapter();
		adapter.clear();
		for(Restaurant rest : result){
			adapter.add(rest);
		}
	}
	
}
