package cat.hmobile.leire.GUI.Activities.test.tests;

import junit.framework.Assert;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Debug;
import android.widget.ImageView;
import android.widget.ListAdapter;
import cat.hmobile.leire.GUI.Activities.ProductDetailActivity;
import cat.hmobile.leire.GUI.Activities.ProductListActivity;
import cat.hmobile.leire.GUI.Activities.R;
import cat.hmobile.leire.entities.products.Product;

/**
 * TESTS ACEPTACIÓ HISTORIA DIFERENCIAR TIPUS DE PRODUCTE
 * @author sisux
 *
 */
public class CatalogElementImageTest extends CatalogActivityTest {
	
	private static final String CATEGORY_TO_FIND_1 = "Snacks and Dips";
	private static final String SUBCATEGORY_TO_FIND_1 = "Jerky";
	private static final String PRODUCT_SIN_FROM_CATEGORY_1 = "Levadura 250g";
	private static final String PRODUCT_FACE_FROM_CATEGORY_1 = "Albóndigas con patatas";
	
	private static final int LEVADURA_ITEM = 0;
	private static final int ALBONDIGAS_ITEM = 1;
	
	
	public CatalogElementImageTest() {
		super();
	}

	public void testElements()
	{
	    // start tracing to "/sdcard/calc.trace"
	    //Debug.startMethodTracing("calc");

		this.checkElemsAt000054();

	    // stop tracing
	    //Debug.stopMethodTracing();
	}
	
	private void checkElemsAt000054()
	{
		solo.waitForActivity("GeneralCatalogActivity", (int) TIMEOUT_10SEG);
		solo.clickOnText(CATEGORY_TO_FIND_1);
		solo.waitForDialogToClose(TIMEOUT_5SEG);
		solo.clickOnText(SUBCATEGORY_TO_FIND_1);
		solo.waitForDialogToClose(TIMEOUT_10SEG);
		ProductListActivity prodListActivity = (ProductListActivity) solo.getCurrentActivity();
		this.checkItem(prodListActivity, LEVADURA_ITEM, PRODUCT_SIN_FROM_CATEGORY_1, false);
		this.checkItem(prodListActivity, ALBONDIGAS_ITEM, PRODUCT_FACE_FROM_CATEGORY_1, true);
	}
	
	private void checkItem(ProductListActivity prodListActivity, int itemIndex, String itemName, boolean isFace)
	{
		this.checkItemList(prodListActivity, itemIndex, itemName, isFace);
		solo.clickInList(itemIndex+1);
		ProductDetailActivity prodDetailActivity = (ProductDetailActivity) solo.getCurrentActivity();
		this.checkItemDetail(prodDetailActivity, itemName, isFace);
		this.goBack(1);
	}
	
	private void checkItemList(ProductListActivity prodListActivity, int itemIndex, String itemName, boolean isFace)
	{
		ListAdapter prodAdapter = solo.getCurrentListViews().get(0).getAdapter();
		Product product = (Product) prodAdapter.getItem(itemIndex); //element
		ImageView icon = (ImageView) solo.getCurrentListViews().get(0).getChildAt(itemIndex).findViewById(R.id.face_controlled_by);

		Assert.assertEquals(isFace, product.isFaceFitType());
		
		checkBitMap(prodListActivity, isFace, icon);
	}
	
	
	private void checkItemDetail(ProductDetailActivity prodDetailActivity, String itemName, Boolean isFace)
	{
		ImageView icon = (ImageView) prodDetailActivity.findViewById(R.id.product_type_icon);
		checkBitMap(prodDetailActivity, isFace, icon);
	}
	
	private void checkBitMap(Activity prodListActivity,
			boolean isFace, ImageView icon) {
		Bitmap celiacBitMap = BitmapFactory.decodeResource(prodListActivity.getResources(), R.drawable.ic_withoutgluten);
		Bitmap faceBitMap = BitmapFactory.decodeResource(prodListActivity.getResources(), R.drawable.ic_face);
		
		Bitmap detailBitmap = ((BitmapDrawable) icon.getDrawable()).getBitmap();
		if(isFace)
		{
			Assert.assertEquals(faceBitMap.getPixel(22,30),detailBitmap.getPixel(22, 30));
		}
		else
		{
			Assert.assertEquals(celiacBitMap.getPixel(22,30),detailBitmap.getPixel(22, 30));
		}
	}


}
