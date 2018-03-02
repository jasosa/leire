package cat.hmobile.leire.GUI.Helpers;

import android.content.Context;
import cat.hmobile.leire.GUI.Activities.R;
import cat.hmobile.leire.business.microblogging.statusnetextension.TimeAgoStatus;

	public class TimeAgoDateFormatter {
		public static String formatTimeAgo(Context context, TimeAgoStatus timeAgoStatus) {
			
			long days = timeAgoStatus.getDays();
			long hours = timeAgoStatus.getHours();
			long minutes = timeAgoStatus.getMinutes();
			
			if(days > 1)
				return String.format(context.getString(R.string.days_ago), days);
			else if (days == 1)
				return context.getString(R.string.one_day_ago);
			else if (hours > 1)
				return String.format(context.getString(R.string.hours_ago), hours);
			else if (hours == 1)
				return context.getString(R.string.one_hour_ago);
			else if (minutes > 1)
				return String.format(context.getString(R.string.minutes_ago), minutes);
			else if (minutes == 1)
				return context.getString(R.string.one_minute_ago);
			else
				return context.getString(R.string.now);
		}
}
