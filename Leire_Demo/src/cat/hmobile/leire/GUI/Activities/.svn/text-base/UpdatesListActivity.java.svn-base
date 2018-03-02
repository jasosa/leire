package cat.hmobile.leire.GUI.Activities;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import cat.hmobile.leire.GUI.Adapters.UpdateHeaderAdapter;
import cat.hmobile.leire.GUI.Dialogs.DefaultDialogBuilder;
import cat.hmobile.leire.GUI.Helpers.UpdateBundleHelper;
import cat.hmobile.leire.entities.categories.Category;
import cat.hmobile.leire.entities.updates.IUpdatesHeaderLoader;
import cat.hmobile.leire.entities.updates.ProductsUpdateLoaderFactory;
import cat.hmobile.leire.entities.updates.UpdateHeader;

public class UpdatesListActivity extends GeneralActivity implements OnItemClickListener, OnClickListener {

    private static final String LOG_TAG = "UpdatesListActivity";

	private List<UpdateHeader> m_lastUpdates;
	UpdateHeaderAdapter m_adapter;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.updatelist);
		loadUpdates();
		initializeAdapter();
		initializeCommonLayoutElements();
	}
		

	private void startCatalogActivity(UpdateHeader update) {

		try
		{
			Intent catalogIntent = new Intent(this, UpdateCatalogActivity.class);
			catalogIntent.putExtra(UpdateBundleHelper.CATALOG_SOURCE, update.getDetailFilePath());
			catalogIntent.putExtra(UpdateBundleHelper.UPDATE_DATE, update.getDate());
			startActivity(catalogIntent);
		}
		catch(Exception e){
			//TODO: intentar utilitzat el DefaultDialogBuilder.create
			AlertDialog.Builder builderDialog = new AlertDialog.Builder(this);
		    AlertDialog dialog;
		    builderDialog.setTitle(this.getString(R.string.error_title));
		    builderDialog.setCancelable(true);
		    builderDialog.setMessage(e.getMessage());
			dialog = builderDialog.create();
			dialog.show();		
		}
	}


	private void initializeAdapter() {
		m_adapter  = new UpdateHeaderAdapter(this,R.layout.updatesrow, (ArrayList<UpdateHeader>) m_lastUpdates);
		ListView v = (ListView) findViewById(R.id.updatesListView);
		v.setAdapter(m_adapter);
		v.setOnItemClickListener(this);
	}

	private void loadUpdates() {
		IUpdatesHeaderLoader updatesLoader = ProductsUpdateLoaderFactory.createProductsUpdateLoader(this.getApplicationContext());
		m_lastUpdates = updatesLoader.loadLastUpdates();
	}
	
	protected void initializeCommonLayoutElements(){
		ImageButton backButton = (ImageButton) findViewById(R.id.button_back);
		backButton.setOnClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		UpdateHeader update = (UpdateHeader) m_adapter.getItem(arg2);
		startCatalogActivity (update);
	}
	

	@Override
	public void onClick(View arg0) {
		this.finish();
		
	}
	
	
}
