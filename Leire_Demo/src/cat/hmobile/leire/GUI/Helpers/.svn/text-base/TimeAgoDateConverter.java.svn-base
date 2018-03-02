package cat.hmobile.leire.GUI.Helpers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cat.hmobile.leire.GUI.Activities.R;
import cat.hmobile.leire.business.microblogging.statusnetextension.TimeAgoStatus;

import android.content.Context;
import android.text.format.DateFormat;

public class TimeAgoDateConverter {
	
	private static final int MILISECONDS_TO_SECONDS_FACTOR = 1000;
	private static final int SECONDS_TO_MINUTES_FACTOR = 60;
	private static final long MINUTES_TO_HOUR_FACTOR = 60;
	private static final long HOUR_TO_DAYS_FACTOR = 24;

	public static TimeAgoStatus GetTimeAgo (Date currentDate, Date oldDate){
		
		long dateDiff =  subtractDateFromCurrent(currentDate, oldDate); 
		long minutes = getTotalMinutes(dateDiff);
		long hours = calcTotalHours(minutes);
		minutes = calcFinalMinutes(minutes);
		long days = calcTotalDays(hours);
		hours = calcFinalHours(hours);	
		return new TimeAgoStatus(days, hours, minutes);
	}

	private static long calcFinalHours(long hours) {
		return hours % HOUR_TO_DAYS_FACTOR;
	}

	private static long calcTotalDays(long hours) {
		return hours / HOUR_TO_DAYS_FACTOR;
	}

	private static long calcFinalMinutes(long minutes) {
		return minutes % MINUTES_TO_HOUR_FACTOR;
	}

	private static long calcTotalHours(long minutes) {
		return minutes / MINUTES_TO_HOUR_FACTOR;
	}

	private static long getTotalMinutes(long dateDiff) {
		return dateDiff / (MILISECONDS_TO_SECONDS_FACTOR * SECONDS_TO_MINUTES_FACTOR);
	}

	private static long subtractDateFromCurrent(Date currentDate, Date date) {
		return currentDate.getTime() - date.getTime();
	}
}
