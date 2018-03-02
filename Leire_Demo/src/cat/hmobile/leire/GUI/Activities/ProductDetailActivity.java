package cat.hmobile.leire.GUI.Activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cat.hmobile.leire.GUI.ActivityStarters.ProductsListActivityStarter;
import cat.hmobile.leire.GUI.ActivityStarters.ProductsUpdateListActivityStarter;
import cat.hmobile.leire.GUI.Dialogs.DefaultDialogBuilder;
import cat.hmobile.leire.GUI.Dialogs.LeireAlertDialog;
import cat.hmobile.leire.GUI.Helpers.ActivityResults;
import cat.hmobile.leire.GUI.Helpers.ProductBundleHelper;

public class ProductDetailActivity extends GeneralActivity implements android.view.View.OnClickListener {

    private static final String LOG_TAG = "ProductDetailActivity";
    
	private String m_productId;
	private TextView m_prodName;
	private TextView m_prodBrand;
	private TextView m_prodCategory;
	private TextView m_prodCatalogDate;
	private ImageButton m_brandNavigator;
	private ImageButton m_categoryNavigator;
	private String m_categoryId;
	private boolean m_productFromUpdateCatalog;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.productview);
		Bundle b = getIntent().getExtras();
		setProductInfoFromBundle(b);
		initializeCommonLayoutElements();
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
    	MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_product_detail, menu);
		return true;
    }
    

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.option_rollover_brand:
			loadProductsByBrand();
			break;
		case R.id.option_rollover_category:
			loadProductsByCategory();
			break;
		default:
			super.onClick(v);
		}
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
			if(resultCode == ActivityResults.GO_TO_CATALOG_BEGINNING){
				setResult(resultCode);
				this.finish();
			}
	}
    
	private void setProductInfoFromBundle(Bundle b) {
		loadViews();
		setTextsInViews(b);
		setProductType(b);
		setProductState(b, m_prodName, m_prodCatalogDate);
		saveProductAndCategoryId(b);
		setProductFromUpdateCatalog(b);
		
		if(isProductFromUpdateCatalog(b)){
			setNavigatorsInvisible();
		}else{
			setClickListenersOnSelectors();
		}
	}

	private void setProductFromUpdateCatalog(Bundle b) {
		m_productFromUpdateCatalog = isProductFromUpdateCatalog(b);
	}

	private void setTextsInViews(Bundle b) {
		if(m_prodName !=null) 		m_prodName.setText(b.getString(ProductBundleHelper.KEY_PRODUCT_NAME));
		if(m_prodBrand !=null) 		m_prodBrand.setText(b.getString(ProductBundleHelper.KEY_PRODUCT_BRAND));
		if(m_prodCategory !=null) 	m_prodCategory.setText(b.getString(ProductBundleHelper.KEY_PRODUCT_CATEGORYNAME));
		if(m_prodCatalogDate !=null)	m_prodCatalogDate.setText(String.format(getString(R.string.text_catalog_in_date) ,b.getString(ProductBundleHelper.KEY_PRODUCT_CATALOGDATE)));
	}
	
	private  void setClickListenersOnSelectors(){
			m_brandNavigator.setOnClickListener(this);
			m_categoryNavigator.setOnClickListener(this);
	}

	private void setNavigatorsInvisible() {
			m_brandNavigator.setVisibility(View.GONE);
			m_categoryNavigator.setVisibility(View.GONE);

	}

	private void loadViews() {
		m_prodName 			= (TextView) this.findViewById(R.id.productnamevalue);
		m_prodBrand 		= (TextView) this.findViewById(R.id.productbrandvalue);
		m_prodCategory 		= (TextView) this.findViewById(R.id.productcategoryvalue);
		m_prodCatalogDate 	= (TextView) this.findViewById(R.id.text_catalog_date);
		m_brandNavigator = (ImageButton) this.findViewById(R.id.option_rollover_brand);
		m_categoryNavigator = (ImageButton) this.findViewById(R.id.option_rollover_category);
	}

	private void saveProductAndCategoryId(Bundle b) {
		m_productId = b.getString(ProductBundleHelper.KEY_PRODUCT_ID);
		m_categoryId = b.getString(ProductBundleHelper.KEY_PRODUCT_CATEGORYID);
	}

	private void setProductState(Bundle b, TextView prodName,
			TextView prodCatalogDate) {
		if(isDeletedProduct(b)){
			 setDeleteProductState(prodName);
		 }
		 else{
			 makeVisibleDateOfAdditionToCatalog(prodCatalogDate);
		 }
	}

	private void setProductType(Bundle b) {
		if(!b.getBoolean(ProductBundleHelper.KEY_PRODUCT_FACE_FILL_TYPE))
		 {
			 ImageView fillType = (ImageView) this.findViewById(R.id.product_type_icon);
			 Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_withoutgluten);
			 fillType.setImageBitmap(bMap);
		 }
	}

	private void makeVisibleDateOfAdditionToCatalog(TextView prodCatalogDate) {
			prodCatalogDate.setVisibility(View.VISIBLE);
	}

	private boolean isDeletedProduct(Bundle b) {
		return isProductFromUpdateCatalog(b) &&
				 !b.getBoolean(ProductBundleHelper.KEY_PRODUCT_UPDATE_STATE);
	}

	private boolean isProductFromUpdateCatalog(Bundle b) {
		return b.containsKey(ProductBundleHelper.KEY_PRODUCT_UPDATE_STATE);
	}
	
	
	//TODO: Eliminar duplicidades con funci—n similar en ProductUpdateListAdapter
	private void setDeleteProductState(TextView prodNameText) {
		prodNameText.setTextColor(getResources().getColor(R.color.red));
		TextView deletedText = (TextView) this.findViewById(R.id.text_product_deleted);
		deletedText.setVisibility(View.VISIBLE);
	}


	private void loadProductsByCategory() {
		
		ProductsListActivityStarter m_starter;
		m_starter = ProductsListActivityStarter.getInstance();
		m_starter.startProductListActivityForViewCategoryProducts(
				this,
				m_prodCategory.getText().toString(),
				m_categoryId);
	}

	private void loadProductsByBrand() {
		
		ProductsListActivityStarter m_starter;
		m_starter = ProductsListActivityStarter.getInstance();
		m_starter.startProductListActivityForViewBrandProducts(
				this,
				m_prodCategory.getText().toString(),
				m_prodBrand.getText().toString());	
		}

}
