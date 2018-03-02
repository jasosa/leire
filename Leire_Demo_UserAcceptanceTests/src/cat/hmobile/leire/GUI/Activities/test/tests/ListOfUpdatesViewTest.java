package cat.hmobile.leire.GUI.Activities.test.tests;

import android.widget.TextView;
import cat.hmobile.leire.GUI.Activities.ProductListActivity;
import cat.hmobile.leire.GUI.Activities.R;
import cat.hmobile.leire.GUI.Activities.UpdatesListActivity;

public class ListOfUpdatesViewTest extends LeireActivityTest {

	private static final int NUMBER_OF_UPDATES = 3;
	private static final String UPDATE_DATE = "23/08/2010";
	private static final String UPDATE_DATE_3 = "06/12/2010";

	private static final String ADDED_EN = "Added";
	private static final String REMOVED_EN = "Removed";
	private static final String ADDED_ES = "A–adidos";
	private static final String REMOVED_ES = "Eliminados";
	private static final int SEARCH_MENU_ITEM_INDEX = 1;
	
	private static final int EXPECTED_UPDATE_REMOVED_NUM = 5;
	private static final int EXPECTED_A_SEARCH_NUM = 9;

	public ListOfUpdatesViewTest() {
		super();
	}
	
	@Override
	protected void setUp() throws Exception 
	{
		super.setUp();
		this.solo.clickInList(LeireActivityTest.UPDATES_ITEMINDEX);
	}
	
	public void testLoadLastUpdates(){
		goToListOfUpdatesScreen();
		checkUpdatesList();
		selectUpdateToViewDetail();
		searchInUpdateRootScreen();
		goBack(1);
		searchInRemovedProductsScreen();
		goBack(2);
		goToCategoryWithoutProducts();
		goBack(2);
		checkNumberInTitle(EXPECTED_UPDATE_REMOVED_NUM);
		goBack(2);
		checkNumberInSeach(EXPECTED_A_SEARCH_NUM);
	}
	
	private void checkNumberInSeach(int expectedElements)
	{
		solo.clickOnText(UPDATE_DATE);		
		solo.waitForDialogToClose(TIMEOUT_5SEG);	
		solo.pressMenuItem(SEARCH_MENU_ITEM_INDEX);
		solo.enterText(0, "a");
		solo.sendKey(solo.ENTER);
		solo.waitForDialogToClose(TIMEOUT_10SEG);
		TextView textCurrentProducts = (TextView) solo.getCurrentActivity().findViewById(R.id.text_currentproducts);
		int currentNum = parseFirstNums(textCurrentProducts.getText().toString());
		assertEquals(expectedElements, currentNum);
	}
	
	private void checkNumberInTitle(int expectedElements)
	{
		solo.clickOnText(UPDATE_DATE);		
		solo.waitForDialogToClose(TIMEOUT_5SEG);	
		
		if(solo.searchText(REMOVED_EN))
			solo.clickOnText(REMOVED_EN);
		else
			solo.clickOnText(REMOVED_ES);
		
		solo.waitForDialogToClose(TIMEOUT_5SEG);
		TextView textCurrentProducts = (TextView) solo.getCurrentActivity().findViewById(R.id.text_currentproducts);
		int currentNum = parseFirstNums(textCurrentProducts.getText().toString());
		assertEquals(expectedElements, currentNum);
	}

	private void goToCategoryWithoutProducts() {
		solo.clickOnText(UPDATE_DATE_3);		
		solo.waitForDialogToClose(TIMEOUT_5SEG);	
		if(solo.searchText(REMOVED_EN))
			solo.clickOnText(REMOVED_EN);
		else
			solo.clickOnText(REMOVED_ES);
		
		assertTrue(solo.waitForText(activity.getString(cat.hmobile.leire.GUI.Activities.R.string.message_categorywithoutproducts)));
	}

	private void checkUpdatesList() {
		UpdatesListActivity prodUpdatesActivity = (UpdatesListActivity) solo.getCurrentActivity();
		//TODO: Arreglar
		//assertEquals(NUMBER_OF_UPDATES, prodUpdatesActivity.getListAdapter().getCount());	
		}

	private void goToListOfUpdatesScreen() {
		solo.clickOnMenuItem(activity.getString(cat.hmobile.leire.GUI.Activities.R.string.menu_view_updates));
		solo.assertCurrentActivity("Activity wrong!",UpdatesListActivity.class);
	}
	
	private void selectUpdateToViewDetail(){
		solo.clickOnText(UPDATE_DATE);
		solo.waitForDialogToClose(TIMEOUT_5SEG);
		//Check Categories Added & Removed
		assertTrue(solo.searchText(ADDED_EN));
		assertTrue(solo.searchText(REMOVED_EN));
	}
	
	private void searchInUpdateRootScreen(){
		solo.pressMenuItem(SEARCH_MENU_ITEM_INDEX);
		solo.enterText(0, "Flasfan");
		solo.sendKey(solo.ENTER);
		solo.waitForDialogToClose(TIMEOUT_10SEG);
		ProductListActivity prodListActivity = (ProductListActivity) solo.getCurrentActivity();
		
		assertEquals(1, solo.getCurrentListViews().size());
	}
	
	private void searchInRemovedProductsScreen(){
		solo.clickOnText(REMOVED_EN);
		solo.waitForDialogToClose(5000);
		solo.pressMenuItem(SEARCH_MENU_ITEM_INDEX);
		solo.enterText(0, "Butty Creaks");
		solo.sendKey(solo.ENTER);
		solo.waitForDialogToClose(TIMEOUT_10SEG);
		ProductListActivity prodListActivity = (ProductListActivity) solo.getCurrentActivity();
		assertEquals(2, solo.getCurrentListViews().get(0).getAdapter().getCount());
	}

	private int parseFirstNums(String text)
	{
		int firstSpaceIdx = text.indexOf(" ");
		return Integer.parseInt(text.substring(0, firstSpaceIdx));
	}
}
