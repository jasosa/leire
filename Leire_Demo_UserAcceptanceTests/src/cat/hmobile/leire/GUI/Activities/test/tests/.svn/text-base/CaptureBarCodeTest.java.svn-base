package cat.hmobile.leire.GUI.Activities.test.tests;

import cat.hmobile.leire.GUI.Activities.ManualScannerActivity;
import cat.hmobile.leire.GUI.Activities.ProductDetailActivity;
import cat.hmobile.leire.GUI.Activities.ScannerActivity;

public class CaptureBarCodeTest extends LeireActivityTest {

	@Override
	protected void setUp() throws Exception 
	{
		super.setUp();
	}
	
	public void testLauncherScreen(){
		goToScannerScreen();
		checkChangeToManualScreen();
		enterEmptyBarCodeInManualScreen();
		goBack(1);
		enterInvalidLayoutBarCodeInManualScreen();
		goBack(1);
		enterBadFormatBarCodeInManualScreen();
		goBack(1);
		enterCorrectAndExistentInDbBarCodeInManualScreen();
		goBack(1);
		enterCorrectAndNONExistentInDbBarCodeInManualScreen();
		goBack(1);
		checkChangeToAutomaticScreen();
	}

	private void goToScannerScreen() {
		solo.clickInList(LeireActivityTest.SCANNER_ITEMINDEX);		
		solo.waitForActivity("ScannerActivity", (int) TIMEOUT_10SEG);
	}

	private void checkChangeToManualScreen() {
		solo.pressMenuItem(0);
		solo.assertCurrentActivity("La actividad actual deber’a ser la de escaner manual", ManualScannerActivity.class);
	}
	
	private void enterEmptyBarCodeInManualScreen() {
		solo.enterText(0, "");
		solo.clickOnImageButton(0);
		assertTrue(solo.searchText(activity.getString(cat.hmobile.leire.GUI.Activities.R.string.error_ean_illegal_layout_argument)));
	}
	
	private void enterBadFormatBarCodeInManualScreen() {
		solo.clearEditText(0);
		solo.enterText(0, "9788449321947");
		solo.clickOnImageButton(0);
		assertTrue(solo.searchText(activity.getString(cat.hmobile.leire.GUI.Activities.R.string.error_ean_format)));
	}
	
	private void enterCorrectAndExistentInDbBarCodeInManualScreen() {
		solo.clearEditText(0);
		solo.enterText(0, "9788449321948");
		solo.clickOnImageButton(0);
		solo.waitForDialogToClose(TIMEOUT_10SEG);
		solo.searchText(solo.getCurrentActivity().getString(cat.hmobile.leire.GUI.Activities.R.string.scannerresult_product_ok));
	}
	
	private void enterCorrectAndNONExistentInDbBarCodeInManualScreen() {
		solo.clearEditText(0);
		solo.enterText(0, "9780552551861");
		solo.clickOnImageButton(0);
		solo.waitForDialogToClose(TIMEOUT_10SEG);
		solo.searchText(solo.getCurrentActivity().getString(cat.hmobile.leire.GUI.Activities.R.string.scannerresult_product_error));
	}
	
	private void enterInvalidLayoutBarCodeInManualScreen(){
		solo.enterText(0, "1639230234");
		solo.clickOnImageButton(0);
		assertTrue(solo.searchText(activity.getString(cat.hmobile.leire.GUI.Activities.R.string.error_ean_illegal_layout_argument)));
	
	}
	
	private void checkChangeToAutomaticScreen() {
		solo.pressMenuItem(0);
		solo.assertCurrentActivity("La actividad actual deber’a ser la de escaner automatico", ScannerActivity.class);
	}	

}
