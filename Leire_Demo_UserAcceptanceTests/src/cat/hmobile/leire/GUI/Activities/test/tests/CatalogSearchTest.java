package cat.hmobile.leire.GUI.Activities.test.tests;

import cat.hmobile.leire.GUI.Activities.ProductListActivity;

public class CatalogSearchTest extends CatalogActivityTest {
	
	private static final String CATEGORY_0000002 = "Beverages";
	private static final String CATEGORY_0000005 = "Snacks and Dips";
	private static final String CATEGORY_0000055 = "Puffs";
	private static final String CATEGORY_0000054 = "Jerky";

	private static final int SEARCH_MENU_INDEX = 1;

	
	public CatalogSearchTest(){
		super();
	}
	
	public void testSearchInCatalog()
	{
		searchProductsByNameOnLeafCategory();
		goBack(2);
		searchProductsByManufacturerOnLeafCategory();
		goBack(2);
		searchProductsByManufacturerOnParentContegory();
		goBack(2);
		searchProductsByManufacturerFromRoot();
		goBack(1);
		searchProductsByCategoryFromRoot();
		goBack(1);
		searchProductsByCategoryThatReturnsEmptySet();
		goBack(2);
		searchProductsFromLeafCategoryThatReturnsEmptySet();
	}

	private void searchProductsFromLeafCategoryThatReturnsEmptySet() {
		solo.waitForDialogToClose(TIMEOUT_5SEG);
		solo.clickOnText(CATEGORY_0000005);
		solo.waitForDialogToClose(TIMEOUT_5SEG);
		solo.clickOnText(CATEGORY_0000054);
		solo.waitForDialogToClose(TIMEOUT_5SEG);
		solo.pressMenuItem(1);
		solo.enterText(0, "fluski");
		solo.sendKey(solo.ENTER);
		solo.waitForDialogToClose(TIMEOUT_10SEG);
		assertTrue(solo.searchText(activity.getString(cat.hmobile.leire.GUI.Activities.R.string.message_querywithioutproducts)));	
	}

	private void searchProductsByCategoryThatReturnsEmptySet() {
		solo.waitForDialogToClose(TIMEOUT_5SEG);
		solo.clickOnText(CATEGORY_0000002);
		solo.waitForDialogToClose(TIMEOUT_5SEG);
		solo.pressMenuItem(1);
		solo.enterText(0, CATEGORY_0000055);
		solo.sendKey(solo.ENTER);
		solo.waitForDialogToClose(TIMEOUT_10SEG);
		assertTrue(solo.searchText(activity.getString(cat.hmobile.leire.GUI.Activities.R.string.message_querywithioutproducts)));
	}

	private void searchProductsByCategoryFromRoot() {
		solo.waitForDialogToClose(TIMEOUT_5SEG);
		solo.pressMenuItem(1);
		solo.enterText(0, CATEGORY_0000055);
		solo.sendKey(solo.ENTER);
		solo.waitForDialogToClose(TIMEOUT_10SEG);
		ProductListActivity prodListActivity = (ProductListActivity) solo.getCurrentActivity();
		//assertEquals(10, prodListActivity.getListAdapter().getCount());						
	}

	private void searchProductsByManufacturerFromRoot() {
		solo.waitForDialogToClose(TIMEOUT_5SEG);
		solo.pressMenuItem(1);
		solo.enterText(0, "TEBA");
		solo.sendKey(solo.ENTER);
		solo.waitForDialogToClose(TIMEOUT_10SEG);
		ProductListActivity prodListActivity = (ProductListActivity) solo.getCurrentActivity();
		//assertEquals(21, prodListActivity.getListAdapter().getCount());				
	}

	private void searchProductsByManufacturerOnParentContegory() {
		solo.waitForDialogToClose(TIMEOUT_10SEG);
		solo.clickOnText(CATEGORY_0000005);
		solo.waitForDialogToClose(TIMEOUT_10SEG);
		solo.pressMenuItem(1);
		solo.enterText(0, "TEBA");
		solo.sendKey(solo.ENTER);
		solo.waitForDialogToClose(TIMEOUT_10SEG);
		ProductListActivity prodListActivity = (ProductListActivity) solo.getCurrentActivity();
		//assertEquals(21, prodListActivity.getListAdapter().getCount());		
	}

	private void searchProductsByManufacturerOnLeafCategory() {
		solo.waitForDialogToClose(TIMEOUT_5SEG);
		solo.clickOnText(CATEGORY_0000005);
		solo.waitForDialogToClose(TIMEOUT_5SEG);
		solo.clickOnText(CATEGORY_0000055);
		solo.waitForDialogToClose(TIMEOUT_5SEG);
		solo.pressMenuItem(1);
		solo.enterText(0, "TEBA");
		solo.sendKey(solo.ENTER);
		solo.waitForDialogToClose(TIMEOUT_10SEG);
		ProductListActivity prodListActivity = (ProductListActivity) solo.getCurrentActivity();
		//assertEquals(10, prodListActivity.getListAdapter().getCount());
		
	}

	private void searchProductsByNameOnLeafCategory() {
		solo.waitForDialogToClose(TIMEOUT_5SEG);
		solo.clickOnText(CATEGORY_0000005);
		solo.waitForDialogToClose(TIMEOUT_5SEG);
		solo.clickOnText(CATEGORY_0000055);
		solo.waitForDialogToClose(TIMEOUT_10SEG);
		//solo.sendKey(solo.MENU);
		solo.pressMenuItem(SEARCH_MENU_INDEX);
		solo.enterText(0, "GUI");
		solo.sendKey(solo.ENTER);
		solo.waitForDialogToClose(TIMEOUT_10SEG);
		ProductListActivity prodListActivity = (ProductListActivity) solo.getCurrentActivity();
		//assertEquals(2, prodListActivity.getListAdapter().getCount());
	}
}
