package cat.hmobile.leire.GUI.Activities.test.tests;

import cat.hmobile.leire.GUI.Activities.ProductDetailActivity;
import cat.hmobile.leire.GUI.Activities.ProductListActivity;
import cat.hmobile.leire.GUI.Activities.R;

public class CatalogNavigationTest extends CatalogActivityTest {

	private static final int TOTAL_NUM_PRODUCTS = 366;
	private static final String CATEGORY_WITHOUT_PRODUCTS_AND_SUBCATEGORIES = "Baking";
	private static final String CATEGORY_TO_FIND_1 = "Beverages";
	private static final String SUBCATEGORY_TO_FIND_1 = "Coffe";
	private static final String CATEGORY_TO_FIND_2 = "Snacks and Dips";
	private static final String CATEGORY_TO_FIND_3 = "Breakfast";
	private static final String SUBCATEGORY_TO_FIND_3 = "Cold Breakfast";
	private static final String SUBSUBCATEGORY_TO_FIND_3 = "Categ_0000311";
	private static final String PRODUCT_FROM_CATEGORY_3 = "Base de pizza";

	public CatalogNavigationTest() {
		super();
	}

	public void testCatalog()
	{
		this.loadProductsFromTwoLevelDeepSubcategory();
		this.goBack(2);
		this.loadProductsFromEmptyCategory();
		this.goBack(1);
		this.viewProductDetail();
		this.goBack(3);
	}
	
	private void loadProductsFromTwoLevelDeepSubcategory()
	{
		solo.waitForDialogToClose(TIMEOUT_5SEG);
		solo.clickOnText(CATEGORY_TO_FIND_1);
		solo.clickOnText(SUBCATEGORY_TO_FIND_1);
		solo.waitForDialogToClose(TIMEOUT_5SEG);
		ProductListActivity prodListActivity = (ProductListActivity) solo.getCurrentActivity();
		//assertEquals(11, prodListActivity.getListAdapter().getCount());
	}

	
	private void loadProductsFromEmptyCategory()
	{
		solo.clickOnText(CATEGORY_WITHOUT_PRODUCTS_AND_SUBCATEGORIES);
		assertTrue(solo.searchText(activity.getString(R.string.message_categorywithoutproducts)));
	}
	
	private void viewProductDetail()
	{
		solo.clickOnText(CATEGORY_TO_FIND_3);
		solo.clickOnText(SUBCATEGORY_TO_FIND_3);
		solo.clickOnText(SUBSUBCATEGORY_TO_FIND_3);
		solo.clickOnText(PRODUCT_FROM_CATEGORY_3);
		solo.assertCurrentActivity("Activity incorrecta", ProductDetailActivity.class);
		solo.searchText(PRODUCT_FROM_CATEGORY_3);
		solo.searchText("Aserceli");
		solo.searchText(SUBSUBCATEGORY_TO_FIND_3);
		solo.searchText("Aserceli, S.L");
		solo.searchText("0150000000024");
		solo.searchText(String.format(solo.getString(R.string.text_catalog_in_date), "10/10/2010"));
	}
}
