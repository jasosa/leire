package cat.hmobile.leire.GUI.Activities.test.tests;

import cat.hmobile.leire.GUI.Helpers.MainMenuHelper;
import cat.hmobile.leire.entities.menu.MainMenuItem;
import android.net.ConnectivityManager;
import android.widget.EditText;
import android.widget.ListView;

public class TimeLineActivityTest extends LeireActivityTest {

	@Override
	protected void setUp() throws Exception 
	{
		super.setUp();
	}
	
	public void testTimeLineActivity(){
		changeUserPreference("jasosa", "t8sq1rF4");
		goBack(1);
		goToTimeLineActivity();
		waitForReceiveTweets();
		goBack(1);
		changeUserPreference("jasos", "asdkjhksa");
		goBack(1);
		goToTimeLineActivity();
		waitForException(cat.hmobile.leire.GUI.Activities.R.string.statusnet_credendtials_error);
		goBack(1);
		changeUserPreference("", "");
		goBack(1);
		goToTimeLineActivity();
		waitForException(cat.hmobile.leire.GUI.Activities.R.string.statusnet_params_blank);
	}

	private void goToTimeLineActivity() {
		solo.clickInList(LeireActivityTest.PARTNERCHANNEL_ITEMINDEX);		
		solo.waitForActivity("TimeLineActivity", (int) TIMEOUT_10SEG);
	}
	
	private void waitForException(int resMessageErrorId) {
		solo.waitForDialogToClose(TIMEOUT_20SEG);
		solo.waitForText(activity.getString(resMessageErrorId));
	}
	
	private void waitForReceiveTweets() {
		solo.waitForDialogToClose(TIMEOUT_20SEG);
		ListView tweetsListView = solo.getCurrentListViews().get(0);
		int tweets = tweetsListView.getAdapter().getCount();
		assertTrue(tweets > 0); 
	}
	

	private void changeUserPreference(String user, String password) {
		goToPreferenceActivity();	
		changeUserName(user, password);
	}

	private void goToPreferenceActivity() {
		solo.clickInList(LeireActivityTest.SETTINGS_ITEMINDEX);			
		solo.waitForActivity("MainPreferencesActivity", (int) TIMEOUT_10SEG);
	}
	
	private void changeUserName(String user, String password) {
		solo.clickOnText(activity.getString(cat.hmobile.leire.GUI.Activities.R.string.credentials_user));
		solo.clearEditText(0);
		solo.enterText(0,user);
		solo.clickOnButton(0);
		
		solo.clickOnText(activity.getString(cat.hmobile.leire.GUI.Activities.R.string.credentials_pass));
		solo.clearEditText(0);
		solo.enterText(0,password);
		solo.clickOnButton(0);
	}
}
