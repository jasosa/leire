package cat.hmobile.leire.GUI.Helpers;

import android.os.Bundle;
import cat.hmobile.leire.entities.products.Product;
import cat.hmobile.leire.entities.products.ProductUpdate;

public class ProductBundleHelper {

    public static final String KEY_PRODUCT_NAME = "productname";
    public static final String KEY_PRODUCT_BRAND = "productbrand";
    public static final String KEY_PRODUCT_CATEGORYID = "productcategoryid";
    public static final String KEY_PRODUCT_CATEGORYNAME = "productcategoryname";
    public static final String KEY_PRODUCT_EAN = "productean";
    public static final String KEY_PRODUCT_ID = "productid";
    public static final String KEY_PRODUCT_FACE_FILL_TYPE = "productfacefilltype";
    public static final String KEY_PRODUCT_MANUFACTURER = "manufacturer";
	
    public static final String KEY_PRODUCTS = "products";
    public static final String KEY_SINGLE_PRODUCT = "product";
    public static final String KEY_CURRENT_CATEGORY_NAME = "currentCategoryName";
    public static final String KEY_CURRENT_CATEGORY_ID = "currentCategoryId";
    
    public static final String KEY_PRODUCT_QUERY = "productquery";
	public static final String KEY_PRODUCT_UPDATE_STATE = "productupdatestate";
	public static final String KEY_PRODUCT_CATALOGDATE = "productcatalogdate";
	
    
    public static Bundle createBundleFromProduct(Product p) {
		Bundle b = new Bundle();
        b.putString(ProductBundleHelper.KEY_PRODUCT_NAME, p.getName());
        b.putString(ProductBundleHelper.KEY_PRODUCT_BRAND, p.getBrand());
        b.putString(ProductBundleHelper.KEY_PRODUCT_CATEGORYID, p.getCategoryId());
        b.putString(ProductBundleHelper.KEY_PRODUCT_EAN,p.getBarCode());
        b.putString(ProductBundleHelper.KEY_PRODUCT_ID, p.getId());
        b.putString(ProductBundleHelper.KEY_PRODUCT_MANUFACTURER, p.getManufacturer());
        b.putString(ProductBundleHelper.KEY_PRODUCT_CATEGORYNAME, p.getCategoryName());
        b.putBoolean(ProductBundleHelper.KEY_PRODUCT_FACE_FILL_TYPE, p.isFaceFitType());
        b.putString(ProductBundleHelper.KEY_PRODUCT_CATALOGDATE, p.getDischargeDate());
        if(p instanceof ProductUpdate){
        	b.putBoolean(ProductBundleHelper.KEY_PRODUCT_UPDATE_STATE, ((ProductUpdate) p).isAdded());
        }
        return b;
	}
    
    public static Product extractProductFrom(Bundle bundle){
		Product p = new Product();
		p.setName(bundle.getString(KEY_PRODUCT_NAME));
		p.setBrand(bundle.getString(KEY_PRODUCT_BRAND));
		p.setCategoryId(bundle.getString(KEY_PRODUCT_CATEGORYID));
		p.setBarCode(bundle.getString(KEY_PRODUCT_EAN));
		p.setId(bundle.getString(KEY_PRODUCT_ID));
		p.setManufacturer(bundle.getString(KEY_PRODUCT_MANUFACTURER));
		p.setCategoryName(bundle.getString(KEY_PRODUCT_CATEGORYNAME));
		p.setFitType(bundle.getInt(KEY_PRODUCT_FACE_FILL_TYPE));
		p.setDischargeDate(bundle.getString(KEY_PRODUCT_CATALOGDATE));
		return p;
	}
}
