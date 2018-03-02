package cat.hmobile.leire.GUI.Activities;

import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import cat.hmobile.leire.GUI.ActivityStarters.ProductDetailActivityStarter;
import cat.hmobile.leire.GUI.Adapters.ProductListAdapter;
import cat.hmobile.leire.GUI.BackgroundTasks.ProductsLoaderByBrandTask;
import cat.hmobile.leire.GUI.BackgroundTasks.ProductsLoaderTask;
import cat.hmobile.leire.GUI.BackgroundTasks.SearchProductByTextTask;
import cat.hmobile.leire.GUI.Dialogs.DefaultDialogBuilder;
import cat.hmobile.leire.GUI.Dialogs.DialogMessage;
import cat.hmobile.leire.GUI.Helpers.ActivityResults;
import cat.hmobile.leire.GUI.Helpers.ProductBundleHelper;
import cat.hmobile.leire.business.Catalog;
import cat.hmobile.leire.entities.catalog.CatalogHolder;
import cat.hmobile.leire.entities.products.Product;

public class ProductListActivity extends Activity implements OnDismissListener, OnClickListener, OnItemClickListener{
	
    private static final String LOG_TAG = "ProductListActivity";
    
    protected ArrayAdapter<? extends Product> m_adapter;
	
	protected List<? extends Product> m_productsList;
	
	protected TextView m_currentTitle;
	
	private String m_query;
	private boolean m_internalQuery;
	
	private String m_currentCategoryName;
	private String m_categoryIdRequest;
	private String m_brandRequest;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{        
		super.onCreate(savedInstanceState);	
		this.setContentView(R.layout.productslist);
		this.handleIntent(getIntent());
		this.initializeLayoutElements();
		this.getExtrasFromCatalogBundle();
		this.performIntent();
	}
	
	@Override
	public void onItemClick(AdapterView<?> paramAdapterView, View paramView,
			int paramInt, long paramLong)
	{
		Product p  = this.getProductFromAdapter(paramInt);
		this.loadProductDetailScreen(p);
	}
	
	@Override
	public void onDismiss(DialogInterface paramDialogInterface) {
		if(m_internalQuery == false){
			finish();
		}
	}

	@Override
	public void onClick(View paramView) {
		if(paramView.getId()==R.id.button_back)
			onKeyDown(KeyEvent.KEYCODE_BACK, null);
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
	    Dialog dialog;
	    switch(id) {
	    case DialogMessage.DIALOG_QUERY_WITHOUT_PRODUCTS:
			dialog = DefaultDialogBuilder.create(this, R.string.warning_title, R.string.message_querywithioutproducts);
	    	break;	
	    case DialogMessage.DIALOG_CATEGORY_WITHOUT_PRODUCTS: 
			dialog = DefaultDialogBuilder.create(this, R.string.warning_title, R.string.message_categorywithoutproducts);
			break;
	    case DialogMessage.DIALOG_BRAND_NOT_FOUND:
			dialog = DefaultDialogBuilder.create(this, R.string.warning_title, R.string.message_brandnotfound);
			break;
	    case DialogMessage.DIALOG_WRONG_PARAMS_IN_BRAND_REQUEST:
			dialog = DefaultDialogBuilder.create(this, R.string.warning_title, R.string.message_wrongparams_brandrequest);
			break;
	    default:
	        dialog = null;
	    }
	    
	    dialog.setOnDismissListener(this);
	    return dialog;
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
    	MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_product_list, menu);
		return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	switch(item.getItemId())
    	{
    		case R.id.menuitem_categories:
    			this.setResult(ActivityResults.GO_TO_CATALOG_BEGINNING);
    			this.finish();
    			break;
    		case R.id.menuitem_search:
    			onSearchRequested();
    			break;
    	}
    	
		return super.onOptionsItemSelected(item);
    }
    
    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }  
    
    
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
			if(resultCode == ActivityResults.GO_TO_CATALOG_BEGINNING){
				setResult(resultCode);
				this.finish();
			}
	}
    
    protected void initializeAdapter() {
        this.m_adapter = new ProductListAdapter(this, R.layout.productrow, (List<Product>) m_productsList);
		ListView v = (ListView) findViewById(R.id.productsListView);
		v.setAdapter(m_adapter);
		v.setOnItemClickListener(this);
	}
    
    protected void initializeLayoutElements(){
		ImageButton backButton = (ImageButton) findViewById(R.id.button_back);
		backButton.setOnClickListener(this);
		m_currentTitle = (TextView) findViewById(R.id.text_currentproducts);
	}

    
  //Public methods
    public void viewProducts(List<Product> products) {
		
		if(checkThereAreProducts(products))
		{
			 m_productsList = products;
	        initializeAdapter();
	        setTitleBar();
		}
	}
    
	private boolean checkThereAreProducts(List<Product> products) {
		if(products==null || products.size()==0)
		{
			int dialogToShow = isQuery() ? DialogMessage.DIALOG_QUERY_WITHOUT_PRODUCTS : DialogMessage.DIALOG_CATEGORY_WITHOUT_PRODUCTS;
			setContentView(R.layout.main);
			showDialog(dialogToShow);
			return false;
		}

		return true;
	}
	
    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
          m_internalQuery = true;
          m_query = intent.getStringExtra(SearchManager.QUERY);
          doTheSearch(m_query);
        }
    }

	private void getExtrasFromCatalogBundle() {
		Bundle b = getIntent().getExtras(); //Get the intent's extras

        if(b.containsKey(ProductBundleHelper.KEY_CURRENT_CATEGORY_NAME))
        	m_currentCategoryName = b.getString(ProductBundleHelper.KEY_CURRENT_CATEGORY_NAME);
        
        if(b.containsKey(ProductBundleHelper.KEY_CURRENT_CATEGORY_ID))
        	m_categoryIdRequest = b.getString(ProductBundleHelper.KEY_CURRENT_CATEGORY_ID);
        
        if(b.containsKey(ProductBundleHelper.KEY_PRODUCT_BRAND))
        	m_brandRequest = b.getString(ProductBundleHelper.KEY_PRODUCT_BRAND);
		
        if(b.containsKey(ProductBundleHelper.KEY_PRODUCT_QUERY)){
        		m_internalQuery = false;
        		m_query = b.getString(ProductBundleHelper.KEY_PRODUCT_QUERY);
        }
	}
	
	private void performIntent(){
		
		if(isQuery())
		{
			this.doTheSearch(m_query); 
		}
		else
		{	
			this.doTheProductsLoading();
		}
	}


  

	private void doTheSearch(String query) {
		new SearchProductByTextTask(this, CatalogHolder.getSearchableCatalog()).execute(query);
    }
    
	private void doTheProductsLoading() {
		 if (isBrandRequest())
			 new ProductsLoaderByBrandTask(this, CatalogHolder.getSearchableCatalog()).execute(m_brandRequest);
		 else if (isCategoryRequest())
			 new ProductsLoaderTask(this, CatalogHolder.getSearchableCatalog()).execute(m_categoryIdRequest);
		
	}
    

	private boolean isQuery() {
		return m_query != null;
	}
	
	private boolean isBrandRequest() {
			return m_brandRequest != null;
	}
	
	private boolean isCategoryRequest() {
		return m_categoryIdRequest != null;
}

	
	private void setTitleBar()
	{
		String message = null;
		if(isQuery())
		{
			message = String.format(getString(R.string.product_query_results_title), this.m_productsList.size(), this.m_query);
		}
		else if (isCategoryRequest())
		{
			message = String.format(getString(R.string.productlist_title_productofcategory), this.m_productsList.size(), this.m_currentCategoryName);
		}
		else if (isBrandRequest()){
			message = String.format(getString(R.string.productlist_title_productofcategory), this.m_productsList.size(), this.m_brandRequest);
		}
	
		this.m_currentTitle.setText(message);
	}
	
	private boolean weAreInRootCategory() {
		
		return (m_currentCategoryName.equals(Catalog.ROOT_CATEGORY_NAME));
	}

	private Product getProductFromAdapter(int position) {
		return this.m_adapter.getItem(position);
	}
	
	
	//Load Product Screen
	private void loadProductDetailScreen(Product p) {
		ProductDetailActivityStarter.startActivity(this, p);
	}


}
