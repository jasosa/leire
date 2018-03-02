package cat.hmobile.leire.GUI.ActivityStarters;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import cat.hmobile.leire.GUI.Activities.ProductListActivity;
import cat.hmobile.leire.GUI.Helpers.ProductBundleHelper;

public class ProductsListActivityStarter {
	
	private static final int VIEW_PRODUCTS_OF_CATEGORY_REQUEST = 0;
	private static final int VIEW_PRODUCTS_FROM_QUERY_REQUEST = 1;
	private static final int VIEW_PRODUCTS_OF_BRAND_REQUEST = 2;
	
	private static ProductsListActivityStarter m_productsListStarter;
	
	public static ProductsListActivityStarter getInstance(){
		if(m_productsListStarter == null)
			m_productsListStarter = new ProductsListActivityStarter();
		
		return m_productsListStarter;
	}
	
	public void startProductListActivityForSearch(Activity activity, String categoryName, String categoryId, String query) {
		
		Bundle b = createBaseBundle(categoryName, categoryId, null);
		int requestCode = VIEW_PRODUCTS_FROM_QUERY_REQUEST;
		b.putString(ProductBundleHelper.KEY_PRODUCT_QUERY, query);	
		createIntentAndStartActivity(activity, b, requestCode);
	}
	
	public void startProductListActivityForViewCategoryProducts(Activity activity, String categoryName, 
			String categoryId) {
		Bundle b = createBaseBundle(categoryName, categoryId, null);
		int requestCode = VIEW_PRODUCTS_OF_CATEGORY_REQUEST;
		this.createIntentAndStartActivity(activity, b, requestCode);
	}
	
	
	public void startProductListActivityForViewBrandProducts(
			Activity activity, String categoryName,
			String brand) {
		
		Bundle b = createBaseBundle(categoryName, null, brand);
		int requestCode = VIEW_PRODUCTS_OF_BRAND_REQUEST;
		this.createIntentAndStartActivity(activity, b, requestCode);
		
	}
	
	protected void createIntentAndStartActivity(Activity activity, Bundle b, int requestCode) {
		Intent i = new Intent(activity, ProductListActivity.class);
		i.putExtras(b);
		activity.startActivityForResult(i, requestCode);
	}
	
	protected Bundle createBaseBundle(String categoryName, String categoryId, String brand) {
		Bundle b = new Bundle();
		if(categoryName != null)b.putString(ProductBundleHelper.KEY_CURRENT_CATEGORY_NAME, categoryName);
		if(categoryId != null) 	b.putString(ProductBundleHelper.KEY_CURRENT_CATEGORY_ID, categoryId);
		if(brand != null)		b.putString(ProductBundleHelper.KEY_PRODUCT_BRAND, brand);
		return b;
	}

	
	
}
