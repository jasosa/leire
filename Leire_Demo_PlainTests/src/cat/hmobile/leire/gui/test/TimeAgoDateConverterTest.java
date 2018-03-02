package cat.hmobile.leire.gui.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import android.app.Activity;
import android.content.Context;
import android.test.AndroidTestCase;
import android.test.IsolatedContext;
import android.test.mock.MockContentResolver;
import android.test.mock.MockContext;

import cat.hmobile.leire.GUI.Activities.R;
import cat.hmobile.leire.GUI.Dialogs.DialogButtons;
import cat.hmobile.leire.GUI.Helpers.TimeAgoDateConverter;
import cat.hmobile.leire.business.microblogging.statusnetextension.TimeAgoStatus;

public class TimeAgoDateConverterTest{
		
	@Before
	public void setUp() throws Exception{
	}
	
	@Test
	public void testTimeAgoDays(){
		//Context c = EasyMock.createMock(Context.class);
	    Calendar c1 = Calendar.getInstance();
	    c1.set(2011,2, 5, 15, 0);
		Date d1 = c1.getTime();
		
		Calendar c2 = Calendar.getInstance();
	    c2.set(2011,2, 2, 15, 0);
		Date d2 = c2.getTime();
		TimeAgoStatus timeAgo = TimeAgoDateConverter.GetTimeAgo(d1, d2);
		assertEquals(3, timeAgo.getDays()); 
	}
	
	@Test
	public void TimeAgoHours(){
		Calendar c1 = Calendar.getInstance();
	    c1.set(2011,2, 5, 15, 0);
		Date d1 = c1.getTime();
		
		Calendar c2 = Calendar.getInstance();
	    c2.set(2011,2, 5, 11, 0);
		Date d2 = c2.getTime();
		TimeAgoStatus timeAgo = TimeAgoDateConverter.GetTimeAgo(d1, d2);
		assertEquals(4, timeAgo.getHours());
	}
	
	@Test
	public void TimeAgoMinutes(){
		
	    Calendar c1 = Calendar.getInstance();
	    c1.set(2011,2, 5, 15, 23);
		Date d1 = c1.getTime();
		
		Calendar c2 = Calendar.getInstance();
	    c2.set(2011,2, 5, 15, 20);
		Date d2 = c2.getTime();
		TimeAgoStatus timeAgo = TimeAgoDateConverter.GetTimeAgo(d1, d2);
		assertEquals(3, timeAgo.getMinutes());
	}
	
	@Test
	public void TimeAgoNow(){
		
	    Calendar c1 = Calendar.getInstance();
	    c1.set(2011,2, 5, 15, 20, 10);
		Date d1 = c1.getTime();
		
		Calendar c2 = Calendar.getInstance();
	    c2.set(2011,2, 5, 15, 20, 3);
		Date d2 = c2.getTime();
		TimeAgoStatus timeAgo = TimeAgoDateConverter.GetTimeAgo(d1, d2);
		assertEquals(0, timeAgo.getMinutes());
	}
}
