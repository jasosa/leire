package cat.hmobile.leire.entities.products;

import cat.hmobile.leire.GUI.Activities.R;
import cat.hmobile.leire.entities.ProductUpdateCategoryException;

public class ProductUpdate extends Product {
	
	
	public static final String ADDED_CATEGORY_ID = "998";
	public static final String REMOVED_CATEGORY_ID = "999";

	public boolean isAdded(){
		return ADDED_CATEGORY_ID.equals(this.m_categoryId);
	}
	
	public boolean isRemoved(){
		return REMOVED_CATEGORY_ID.equals(this.m_categoryId);
	}
	
	@Override
	public void setCategoryId(String categoryId) {
		
		if(!isUpdateCategory(categoryId))
			throw new ProductUpdateCategoryException(R.string.error_product_update_category);
		super.setCategoryId(categoryId);
	}

	private boolean isUpdateCategory(String categoryId) {
		return ADDED_CATEGORY_ID.equals(categoryId) || REMOVED_CATEGORY_ID.equals(categoryId);
	}
}
