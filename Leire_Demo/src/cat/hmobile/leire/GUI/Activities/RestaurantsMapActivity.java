package cat.hmobile.leire.GUI.Activities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import cat.hmobile.leire.GUI.Dialogs.DefaultDialogBuilder;
import cat.hmobile.leire.GUI.Dialogs.LeireAlertDialog;
import cat.hmobile.leire.entities.restaurants.IRestaurantLoader;
import cat.hmobile.leire.entities.restaurants.Restaurant;
import cat.hmobile.leire.entities.restaurants.RestaurantLoaderFactory;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class RestaurantsMapActivity extends MapActivity {

	MapView m_mapView;
	
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restauransmap);
        m_mapView = (MapView) findViewById(R.id.mapView);
        m_mapView.setBuiltInZoomControls(true);
        addRestaurantsToMap();
        m_mapView.getController().setCenter(getGeoPointFromAddress ("Barcelona"));
        m_mapView.getController().setZoom(12);
	}

	private void addRestaurantsToMap() {
		List<Overlay> mapOverlays = m_mapView.getOverlays();
        Drawable drawable = this.getResources().getDrawable(R.drawable.ic_overlay_restaurants_32);
        RestaurantsOverlay restaurantsOverlay = new RestaurantsOverlay(drawable, this);
        IRestaurantLoader loader = RestaurantLoaderFactory.createRestaurantLoader();
        List<Restaurant> restaurants = loader.loadAllRestaurants();
        for(Restaurant rest : restaurants){
        	GeoPoint point = getGeoPointFromAddress(rest.getAddress());
        	if(point == null) continue;
        	OverlayItem overlayitem = new OverlayItem(point, rest.getName() , rest.getAddress());
        	restaurantsOverlay.addOverlay(overlayitem);
        }
       
        mapOverlays.add(restaurantsOverlay);
	}
	
	private GeoPoint getGeoPointFromAddress (String address){
		Geocoder geoCoder = new Geocoder(this, Locale.getDefault());   
		try {
			List<Address> addresses = geoCoder.getFromLocationName(address, 1);
			if(addresses.size() > 0){
				GeoPoint geoPoint = new GeoPoint((int) (addresses.get(0).getLatitude() * 1E6), 
                        (int) (addresses.get(0).getLongitude() * 1E6));
				return geoPoint;
			}
		} catch (IOException e) {
			
		}
		
		return null;
	}
	
}

class RestaurantsOverlay extends ItemizedOverlay{

	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	private Context mContext;
	
	public RestaurantsOverlay(Drawable defaultMarker, Context context) {
		super(boundCenterBottom(defaultMarker));
		mContext = context;
	}

	@Override
	protected OverlayItem createItem(int arg0) {
		return mOverlays.get(arg0);
	}

	@Override
	public int size() {
		return mOverlays.size();
	}
	
	@Override
	protected boolean onTap(int index) {
	
	  OverlayItem item = mOverlays.get(index);
	  LeireAlertDialog dialog = DefaultDialogBuilder.create(mContext, item.getTitle(), item.getSnippet());	
	  dialog.show();
	  return true;
	}
	
	public void addOverlay(OverlayItem overlay) {
	    mOverlays.add(overlay);
	    populate();
	}
	
}
