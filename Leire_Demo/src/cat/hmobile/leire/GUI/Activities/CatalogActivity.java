package cat.hmobile.leire.GUI.Activities;

import android.app.Activity;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import cat.hmobile.leire.GUI.ActivityStarters.ProductsListActivityStarter;
import cat.hmobile.leire.GUI.BackgroundTasks.RootCategoriesLoaderTask;
import cat.hmobile.leire.GUI.BackgroundTasks.SubCategoriesLoaderTask;
import cat.hmobile.leire.GUI.Dialogs.DefaultDialogBuilder;
import cat.hmobile.leire.GUI.Dialogs.DialogMessage;
import cat.hmobile.leire.GUI.Helpers.ActivityResults;
import cat.hmobile.leire.GUI.Helpers.ProductBundleHelper;
import cat.hmobile.leire.business.ICatalogNavigator;
import cat.hmobile.leire.entities.categories.Category;

public abstract class CatalogActivity extends Activity implements OnItemClickListener, OnClickListener {

    private static final String LOG_TAG = "CatalogActivity";
    
	protected static String ALLPRODUCTS_CATEGORY_NAME; //Initialized later
	private static final int VIEW_PRODUCTS_OF_CATEGORY_REQUEST = 0;
	private static final int VIEW_PRODUCTS_FROM_QUERY_REQUEST = 1;

	ICatalogNavigator m_catalog;
	ArrayAdapter<Category> m_adapter;
	Category m_allProductsCategory;
	String m_lastQuery;
	ProgressDialog m_progDialog;
    protected TextView m_currentCategory;


	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
	};
	
	@Override
	public void onItemClick(AdapterView<?> paramAdapterView, View paramView,
			int paramInt, long paramLong)
	{
		loadScreenForSelectedItem(paramInt);
	}	
	
	@Override
	public void onClick(View paramView) {
		if(paramView.getId()==R.id.button_back)
			onKeyDown(KeyEvent.KEYCODE_BACK, null);
	}

	@Override
	public boolean onKeyDown (int keyCode, KeyEvent event)
	{
		if ((keyCode == KeyEvent.KEYCODE_BACK)) 
		{
			if(weAreInRootCategory()){ 
				this.finish();
				return false;
			}
			else
			{
				m_catalog.navigateToParentCategory();
				addSelectedCategoriesToAdapter();
				setTitleBar();
				return true;
			}
		}
		else{
			return false;
		}
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch(id) {
		case DialogMessage.DIALOG_CATEGORY_WITHOUT_PRODUCTS:
			return DefaultDialogBuilder.create(this, R.string.warning_title,R.string.message_categorywithoutproducts);
		case DialogMessage.DIALOG_QUERY_WITHOUT_PRODUCTS:
			return DefaultDialogBuilder.create(this, R.string.warning_title,R.string.message_querywithioutproducts);
		default:
			return null;
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_catalog, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId())
		{
		case R.id.menuitem_categories:
			initializeNavigation();
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

		if (requestCode == VIEW_PRODUCTS_OF_CATEGORY_REQUEST && resultCode == ActivityResults.GO_TO_CATALOG_BEGINNING){
			initializeNavigation();
		}
		else if (requestCode == VIEW_PRODUCTS_OF_CATEGORY_REQUEST && resultCode == Activity.RESULT_CANCELED){
			m_catalog.navigateToParentCategory();
		}
	}
	
	//Public Methods
	public void viewAllProducts() {
		ProductsListActivityStarter.getInstance().startProductListActivityForViewCategoryProducts(
				this, m_catalog.getCurrentCategoryName(), m_catalog.getCurrentCategoryId());
	}

	public void addSelectedCategoriesToAdapter() {

		m_adapter.clear();

		if(m_catalog.getCurrentSubCategories() != null)
		{
			for(int i=0;i<m_catalog.getCurrentSubCategories().size();i++)
			{
				m_adapter.add(m_catalog.getCurrentSubCategories().get(i));
			}
		}

		m_adapter.notifyDataSetChanged();
	}
	
	public abstract void setTitleBar();

	//Private Methods
	protected void handleIntent(Intent intent) {
		if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
			String query = intent.getStringExtra(SearchManager.QUERY);
			doTheSearch(query);
		}
	}

	protected void doTheSearch(String query) {
		ProductsListActivityStarter.getInstance().startProductListActivityForSearch(this, m_catalog.getCurrentCategoryName(),m_catalog.getCurrentCategoryId(), query);
	}
	
	protected void initializeAdapter()
	{
		m_adapter  = new ArrayAdapter<Category>(this,R.layout.categoryrow, R.id.category_name, m_catalog.getCurrentSubCategories());
		ListView v = (ListView) findViewById(R.id.categoriesListView);
		v.setAdapter(m_adapter);
		v.setOnItemClickListener(this);
	}

	protected void initializeNavigation() {
		new RootCategoriesLoaderTask(this, m_catalog).execute();
	}
	
	protected void initializeLayoutElements(){
		ImageButton backButton = (ImageButton) findViewById(R.id.button_back);
		backButton.setOnClickListener(this);
		m_currentCategory = (TextView) findViewById(R.id.text_currentcategory);
	}


	private void loadScreenForSelectedItem(int selectedItemPosition) {
		viewInfoFromCurrentCategory(m_catalog.getCurrentSubCategories().get(selectedItemPosition).getId());
	}

	private void viewInfoFromCurrentCategory(String categoryId) {
	
		new SubCategoriesLoaderTask(this, m_catalog).execute(categoryId);
	}

	protected boolean weAreInRootCategory() {
		return (m_catalog.isCurrentCategoryRoot());
	}
	
//	private void startProductListActivityForSearch(String query) {
//		
//		Bundle b = createBaseBundle();
//		int requestCode = VIEW_PRODUCTS_FROM_QUERY_REQUEST;
//		b.putString(ProductBundleHelper.KEY_PRODUCT_QUERY, query);	
//		createIntentAndStartActivity(b, requestCode);
//	}
//	
//	private void startProductListActivityForViewCategoryProducts() {
//		
//		Bundle b = createBaseBundle();
//		int requestCode = VIEW_PRODUCTS_OF_CATEGORY_REQUEST;
//		createIntentAndStartActivity(b, requestCode);
//	}
//	
//	
//	protected void createIntentAndStartActivity(Bundle b, int requestCode) {
//		Intent i = new Intent(this, ProductListActivity.class);
//		i.putExtras(b);
//		startActivityForResult(i, requestCode);
//	}
//	
//	private Bundle createBaseBundle() {
//		Bundle b = new Bundle();
//		b.putString(ProductBundleHelper.KEY_CURRENT_CATEGORY_NAME, m_catalog.getCurrentCategoryName());
//		return b;
//	}
}
