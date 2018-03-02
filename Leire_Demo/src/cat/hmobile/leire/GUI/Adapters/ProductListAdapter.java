package cat.hmobile.leire.GUI.Adapters;

import java.util.List;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cat.hmobile.leire.GUI.Activities.R;
import cat.hmobile.leire.entities.products.Product;

public class ProductListAdapter extends ArrayAdapter<Product> {

    private static final int ALPHA_VISIBLE = 255;
    private static final int ALPHA_INVISIBLE = 0;

	private static final String LOG_TAG = "ProductListAdapter";

    public ProductListAdapter(Context context, int textViewResourceId, List<Product> items) {
            super(context, textViewResourceId, items);
            Log.d(LOG_TAG, "ProductListAdapter constructor"); 
    }

    /**
     * see http://www.google.com/events/io/2010/sessions/world-of-listview-android.html (11:41)
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
    	ViewHolder holder;
    	if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.productrow, null);
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
		Product o = getItem(position);
        if (o != null) {
        	holder.m_toptext.setText(o.getName());
        	holder.m_bottomtext.setText(o.getBrand());  
        	setIconType(holder, o);
        }
	}

	private ViewHolder createHolder(View convertView) {
		ViewHolder holder;
		holder = new ViewHolder();
		holder.m_toptext = (TextView) convertView.findViewById(R.id.toptext);
		holder.m_bottomtext = (TextView) convertView.findViewById(R.id.bottomtext);
		holder.m_iconType = (ImageView) convertView.findViewById(R.id.face_controlled_by);
		return holder;
	}

	private void setIconType(ViewHolder holder, Product o) {
		if(o.isFaceFitType())
        	holder.m_iconType.setImageBitmap(BitmapFactory.decodeResource(getContext().getResources(), R.drawable.ic_face));
		else
        	holder.m_iconType.setImageBitmap(BitmapFactory.decodeResource(getContext().getResources(), R.drawable.ic_withoutgluten));
	}

	private static class ViewHolder
	{
		 TextView m_toptext;
		 TextView m_bottomtext;
		 ImageView m_iconType;
	}
}