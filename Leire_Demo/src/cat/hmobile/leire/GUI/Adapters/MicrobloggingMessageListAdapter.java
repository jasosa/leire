package cat.hmobile.leire.GUI.Adapters;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cat.hmobile.leire.GUI.Activities.R;
import cat.hmobile.leire.GUI.Helpers.TimeAgoDateConverter;
import cat.hmobile.leire.GUI.Helpers.TimeAgoDateFormatter;
import cat.hmobile.leire.business.microblogging.statusnetextension.StatusNetMarkersFormatter;
import cat.hmobile.leire.business.microblogging.statusnetextension.TimeAgoStatus;
import cat.hmobile.leire.entities.menu.MainMenuItem;
import cat.hmobile.leire.entities.microblogging.IMicrobloggingMessage;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MicrobloggingMessageListAdapter extends
		ArrayAdapter<IMicrobloggingMessage> {

	private static final String LOG_TAG = "MicrobloggingMessageListAdapter";

	public MicrobloggingMessageListAdapter(Context context,
			int textViewResourceId, List<IMicrobloggingMessage> items) {
		super(context, textViewResourceId, items);
        Log.d(LOG_TAG, "MicrobloggingMessageListAdapter constructor"); 
     }
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
		MicrobloggingMessageHolder holder;
    	if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.twitt_messagerow, null);
            holder = createHolder(convertView);
		    convertView.setTag(holder);
        }
    	else{
    		holder = (MicrobloggingMessageHolder) convertView.getTag();
    	}

    	fillLayout(position, holder);
        return convertView;
    }
	
	private void fillLayout(int position, MicrobloggingMessageHolder holder) {
		IMicrobloggingMessage o = this.getItem(position);
        if (o != null) {
        	
        	holder.m_messagetext.setText(StatusNetMarkersFormatter.removeMarkers(o.getText()));
        	holder.m_username.setText(o.getUser().getScreenName());
        	holder.m_avatar.setImageBitmap(o.getUser().getImage());
        	Date messageCreatedAt = o.getCreatedAd();
        	TimeAgoStatus timeFromMessage = TimeAgoDateConverter.GetTimeAgo(Calendar.getInstance().getTime(), messageCreatedAt);
        	holder.m_timeago.setText(TimeAgoDateFormatter.formatTimeAgo(getContext(), timeFromMessage));
        }
	}
	
	private MicrobloggingMessageHolder createHolder(View convertView) {
		MicrobloggingMessageHolder holder;
		holder = new MicrobloggingMessageHolder();
		holder.m_messagetext = (TextView) convertView.findViewById(R.id.toptext);
		holder.m_username = (TextView) convertView.findViewById(R.id.bottomtext);
		holder.m_avatar = (ImageView) convertView.findViewById(R.id.useravatar);
		holder.m_timeago = (TextView) convertView.findViewById(R.id.postedtimeago);
		return holder;
	}
	
	private static class MicrobloggingMessageHolder
	{
		 TextView m_messagetext;
		 TextView m_timeago;
		 TextView m_username;
		 ImageView m_avatar;
	}


}
