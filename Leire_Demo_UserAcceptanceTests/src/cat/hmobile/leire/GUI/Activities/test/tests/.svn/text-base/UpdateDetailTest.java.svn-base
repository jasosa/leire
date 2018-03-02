package cat.hmobile.leire.GUI.Activities.test.tests;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;
import android.widget.TextView;
import cat.hmobile.leire.GUI.Activities.ProductDetailActivity;
import cat.hmobile.leire.GUI.Activities.ProductListActivity;
import cat.hmobile.leire.GUI.Activities.R;

public class UpdateDetailTest extends LeireActivityTest {
	
	private static final String ADDED = "A–adidos";
	private static final String REMOVED = "Eliminados";

	public UpdateDetailTest() {
		super();
	}
	
	@Override
	protected void setUp() throws Exception 
	{
		super.setUp();
		this.solo.clickInList(LeireActivityTest.UPDATES_ITEMINDEX);
	}
	
	public void testLoadLastUpdates(){
		goToUpdateDetailScreen();
		goToAddProductsList();
		checkListIcons(true);
		checkDetailIcon(1, true);
		goBack(2);
		goToRemovedProductsList();
		checkListIcons(false);
		checkDetailIcon(1, false);
	}
	
	private void checkDetailIcon(int productIndex, boolean productAdded) {
		solo.waitForDialogToClose(TIMEOUT_5SEG);
		this.solo.clickInList(productIndex);
		solo.waitForDialogToClose(TIMEOUT_5SEG);
		ProductDetailActivity prodDetailActivity = (ProductDetailActivity) solo.getCurrentActivity();
		TextView text = (TextView) prodDetailActivity.findViewById(R.id.productnamevalue);
		checkTextColor(productAdded, text);
	}

	private void goToRemovedProductsList() {
		solo.waitForDialogToClose(TIMEOUT_5SEG);
		this.solo.clickOnText(REMOVED);		
	}

	private void checkListIcons(boolean added) {
		solo.waitForDialogToClose(TIMEOUT_5SEG);
		for(int i = 0;i<solo.getCurrentListViews().get(0).getChildCount();i++){
		
			TextView text = (TextView) solo.getCurrentListViews().get(0).getChildAt(i).findViewById(R.id.toptext);
			checkTextColor(added, text);
		}
	}

	private boolean checkTextColor(boolean added, TextView icon) {
		int color = icon.getCurrentTextColor();
		return (isAddedAndGreen(added, color) || isRemovedAndRed(added, color)); 
	}

	private boolean isRemovedAndRed(boolean added, int color) {
		return !added && color==R.color.red;
	}

	private boolean isAddedAndGreen(boolean added, int color) {
		return added && color == R.color.green;
	}

	private void goToAddProductsList() {
		solo.waitForDialogToClose(TIMEOUT_5SEG);
		this.solo.clickOnText(ADDED);
		
	}

	private void goToUpdateDetailScreen() {
		this.solo.clickOnText("17/11/2010");
	}
}
