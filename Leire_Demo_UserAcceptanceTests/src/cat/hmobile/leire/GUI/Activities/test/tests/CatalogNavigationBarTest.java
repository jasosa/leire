package cat.hmobile.leire.GUI.Activities.test.tests;

import android.widget.TextView;
import cat.hmobile.leire.GUI.Activities.R;

public class CatalogNavigationBarTest extends CatalogActivityTest {
	
	private static final String CATEGORY_2 = "Beverages";
	private static final String CATEGORY_0004 = "Condiments";
	private static final String CATEGORY_0041 = "Barbecue Sauce";
	private static final String CATEGORY_0411 = "Categ_0000411";
	
	TextView m_CurrentCategoryText;
	
	public CatalogNavigationBarTest()
	{
		super();
	}
	
	public void testNavigationBarInfo() throws InterruptedException
	{
		this.checkTheTitleBarAtBeginning();
		this.goToSubCategoryAndCheck();
		this.goBack(1);
		this.goTwoLevelsDownAndCheck();
		this.goBack(2);
		this.viewProductsFromOneCategoryAndCheck();
		this.goBack(3);
	}

	private void viewProductsFromOneCategoryAndCheck() {
		solo.clickOnText(CATEGORY_0004);
		//solo.waitForDialogToClose(TIMEOUT_10SEG);
		solo.clickOnText(CATEGORY_0041);
//		solo.waitForDialogToClose(TIMEOUT_10SEG);
		solo.clickOnText(CATEGORY_0411);
		solo.waitForDialogToClose(TIMEOUT_5SEG);
		m_CurrentCategoryText = (TextView) solo.getView(R.id.text_currentproducts);
		assertEquals(m_CurrentCategoryText.getText()
				,String.format(solo.getString(R.string.productlist_title_productofcategory) , 2, CATEGORY_0411));
	}

	private void goTwoLevelsDownAndCheck(){
		solo.clickOnText(CATEGORY_0004);
		solo.waitForDialogToClose(TIMEOUT_5SEG);
		solo.clickOnText(CATEGORY_0041);
		this.solo.waitForDialogToClose(TIMEOUT_5SEG);
		m_CurrentCategoryText = (TextView) solo.getView(R.id.text_currentcategory);
		assertEquals(CATEGORY_0041, m_CurrentCategoryText.getText());
	}

	private void goToSubCategoryAndCheck() throws InterruptedException {
		solo.clickOnText(CATEGORY_2);
		this.solo.waitForDialogToClose(TIMEOUT_10SEG);
		m_CurrentCategoryText = (TextView) solo.getView(R.id.text_currentcategory);
		assertEquals(m_CurrentCategoryText.getText(), CATEGORY_2);
	}

	private void checkTheTitleBarAtBeginning() {
		this.solo.waitForActivity("GeneralCatalogActivity", 10000);
		this.solo.waitForDialogToClose(TIMEOUT_5SEG);
		m_CurrentCategoryText = (TextView) solo.getCurrentActivity().findViewById(R.id.text_currentcategory);
		assertEquals(activity.getString(cat.hmobile.leire.GUI.Activities.R.string.leireCatalog), m_CurrentCategoryText.getText() );
	}
}
