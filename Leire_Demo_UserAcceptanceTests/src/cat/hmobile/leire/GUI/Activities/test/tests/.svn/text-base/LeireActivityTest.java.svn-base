package cat.hmobile.leire.GUI.Activities.test.tests;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import cat.hmobile.leire.GUI.Activities.MainMenuActivity;

import com.jayway.android.robotium.solo.Solo;

public abstract class LeireActivityTest extends ActivityInstrumentationTestCase2<MainMenuActivity> {
	
	public static final int CATALOG_ITEMINDEX = 1;
	public static final int SCANNER_ITEMINDEX = 2;
	public static final int UPDATES_ITEMINDEX = 3;
	public static final int PARTNERCHANNEL_ITEMINDEX = 4;
	public static final int INFO_ITEMINDEX = 5;
	public static final int SETTINGS_ITEMINDEX = 6;
	public static final int MICROBLOGGING_TIMELINE_ITEMINDEX = 7;


	protected Solo solo;
	protected Activity activity;
	protected static final long TIMEOUT_1SEG = 1000;
	protected static final long TIMEOUT_5SEG = 5000;
	protected static final long TIMEOUT_10SEG = 10000;
	protected static final long TIMEOUT_15SEG = 15000;
	protected static final long TIMEOUT_20SEG = 20000;
	protected static final long TIMEOUT_25SEG = 25000;

	public LeireActivityTest() {
		super("cat.hmobile.leire.GUI.Activities.test", MainMenuActivity.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		this.activity = this.getActivity();
		this.solo = new Solo(getInstrumentation(), this.activity);
	}
	
	@Override
	public void tearDown() throws Exception {
		try {
		solo.finalize();
		} catch (Throwable e) {
		e.printStackTrace();
		}
		getActivity().finish();
		super.tearDown();
	}
	
	protected void goBack(int times) {
		for(int i = 0;i<times;i++)
			solo.goBack();
	}
}
