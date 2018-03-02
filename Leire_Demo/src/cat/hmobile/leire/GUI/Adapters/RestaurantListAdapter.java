package cat.hmobile.leire.GUI.Adapters;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cat.hmobile.leire.GUI.Activities.R;
import cat.hmobile.leire.entities.restaurants.Restaurant;

public class RestaurantListAdapter extends ArrayAdapter<Restaurant> {

	private static final String LOG_TAG = "RestaurantListAdapter";

	
	public RestaurantListAdapter(Context context, int resource, List<Restaurant> objects) {
		super(context, resource, objects);
         Log.d(LOG_TAG, "RestaurantListAdapter constructor"); 
	}
	
	 @Override
	    public View getView(int position, View convertView, ViewGroup parent)
	    {
	    	ViewHolder holder;
	    	if (convertView == null) {
	            LayoutInflater vi = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            convertView = vi.inflate(R.layout.restaurantrow, null);
	            holder = createHolder(convertView);
			    convertView.setTag(holder);
	        }
	    	else{
	    		holder = (ViewHolder) convertView.getTag();
	    	}

	    	fillLayout(position, holder);
	        return convertView;
	    }

		private void fillLayout(int position, ViewHolder holder) {
			Restaurant o = getItem(position);
	        if (o != null) {
	        	holder.m_toptext.setText(o.getName());
	        	holder.m_bottomtext.setText(o.getAddress());  
	        }
		}

		private ViewHolder createHolder(View convertView) {
			ViewHolder holder;
			holder = new ViewHolder();
			holder.m_toptext = (TextView) convertView.findViewById(R.id.toptext);
			holder.m_bottomtext = (TextView) convertView.findViewById(R.id.bottomtext);
			return holder;
		}

		private static class ViewHolder
		{
			 TextView m_toptext;
			 TextView m_bottomtext;
			 ImageView m_iconType;
		}

}
