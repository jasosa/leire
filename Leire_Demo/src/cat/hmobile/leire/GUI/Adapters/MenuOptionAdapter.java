package cat.hmobile.leire.GUI.Adapters;

import java.util.List;

import cat.hmobile.leire.GUI.Activities.R;
import cat.hmobile.leire.entities.menu.MainMenuItem;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.Visibility;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuOptionAdapter extends ArrayAdapter<MainMenuItem> {
	
	private static final String LOG_TAG = "MenuOptionAdapter";
	
	public MenuOptionAdapter(Context context, int textViewResourceId,List<MainMenuItem> items) {
        super(context, textViewResourceId, items);
        Log.d(LOG_TAG, "MenuOptionAdapter constructor"); 
	}
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
    	ViewHolder holder;
    	if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.mainmenurow, null);
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
		MainMenuItem o = getItem(position);
        if (o != null) {
        	
        	holder.m_toptext.setText(this.getContext().getString(o.get_itemDescriptionId()));
        	holder.m_icon.setImageBitmap(BitmapFactory.decodeResource(getContext().getResources(), o.get_itemDrawableIconId()));
    	
        	if(o.is_itemEnabled()){
        		enableRow(holder);
        	}
        	else{
        		disableRow(holder);
        	}
        }
	}

	private void disableRow(ViewHolder holder) {
		setImageVisibility(holder.m_rollIcon, ImageView.GONE);
		setTextColor(holder.m_toptext,R.color.light_gray);
	}

	private void enableRow(ViewHolder holder) {
		setImageVisibility(holder.m_rollIcon, ImageView.VISIBLE);
		setTextColor(holder.m_toptext,R.color.dark_gray);		
	}

	private void setTextColor(TextView text, int color) {
		 text.setTextColor(getContext().getResources().getColor(color));
	}

	private void setImageVisibility(ImageView image, int visibility) {
		image.setVisibility(visibility);
	}

	private ViewHolder createHolder(View convertView) {
		ViewHolder holder;
		holder = new ViewHolder();
		holder.m_toptext = (TextView) convertView.findViewById(R.id.option_title);
		holder.m_icon = (ImageView) convertView.findViewById(R.id.option_icon);
		holder.m_rollIcon = (ImageView) convertView.findViewById(R.id.option_rollover);
		return holder;
	}
	
	private static class ViewHolder
	{
		 TextView m_toptext;
		 ImageView m_icon;
		 ImageView m_rollIcon;
	}

}
