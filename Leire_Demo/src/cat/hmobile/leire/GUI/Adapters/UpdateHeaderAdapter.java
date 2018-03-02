package cat.hmobile.leire.GUI.Adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import cat.hmobile.leire.GUI.Activities.R;
import cat.hmobile.leire.entities.updates.UpdateHeader;

public class UpdateHeaderAdapter extends ArrayAdapter<UpdateHeader> {

    private ArrayList<UpdateHeader> m_items;

    public UpdateHeaderAdapter(Context context, int textViewResourceId, ArrayList<UpdateHeader> items) {
            super(context, textViewResourceId, items);
            this.m_items = items;
    }

    /**
     * see http://www.google.com/events/io/2010/sessions/world-of-listview-android.html (11:41)
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
    	ViewHolder holder;
    	if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.updatesrow, null);
            
            holder = new ViewHolder();
            holder.m_datetext = (TextView) convertView.findViewById(R.id.update_date);
            holder.m_productsintext = (TextView) convertView.findViewById(R.id.products_in);
            holder.m_productsouttext = (TextView) convertView.findViewById(R.id.products_out);
		    convertView.setTag(holder);
        }
    	else
    	{
    		holder = (ViewHolder) convertView.getTag();
    	}
        
    	UpdateHeader o = m_items.get(position);
        if (o != null) {
        	holder.m_datetext.setText(o.getDate());
        	holder.m_productsintext.setText(String.valueOf(o.getNumberOfProductsIn()));  
        	holder.m_productsouttext.setText(String.valueOf(o.getNumberProductsOut()));
        }
        return convertView;
    }

	private static class ViewHolder
	{
		 TextView m_datetext;
		 TextView m_productsintext;
		 TextView m_productsouttext;
	}
}
