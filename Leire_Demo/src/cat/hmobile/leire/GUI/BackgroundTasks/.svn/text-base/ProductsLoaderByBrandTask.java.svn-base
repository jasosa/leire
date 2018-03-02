package cat.hmobile.leire.GUI.BackgroundTasks;

import java.util.List;

import cat.hmobile.leire.GUI.Activities.ProductListActivity;
import cat.hmobile.leire.GUI.Activities.R;
import cat.hmobile.leire.GUI.Dialogs.DialogMessage;
import cat.hmobile.leire.GUI.Dialogs.LeireProgressDialog;
import cat.hmobile.leire.business.ICatalogNavigator;
import cat.hmobile.leire.entities.ArgumentException;
import cat.hmobile.leire.entities.products.BrandNotFoundException;
import cat.hmobile.leire.entities.products.Product;
import android.os.AsyncTask;

public class ProductsLoaderByBrandTask extends
		AsyncTask<String, Void, List<Product>> {

	private LeireProgressDialog m_progDialog;
	private ProductListActivity m_activity;
	private ICatalogNavigator m_catalog;
	private int m_exceptionCode;
	
	public ProductsLoaderByBrandTask(ProductListActivity productListActivity, ICatalogNavigator catalog){
		m_activity = productListActivity;	
		m_catalog = catalog;
		m_exceptionCode = 0;
	}
	

	@Override
	protected void onPreExecute(){
		m_progDialog = LeireProgressDialog.show(m_activity, "", 
				m_activity.getString(R.string.message_loading), true);
	}
	
	@Override
	protected List<Product> doInBackground(String... paramArrayOfParams) {
		List<Product> productsByBrand = null;
		
		if(wrongParams(paramArrayOfParams)){
			m_exceptionCode = R.string.message_wrongparams_brandrequest;
		}
		else
		{
			try{
				productsByBrand = getProducts(paramArrayOfParams[0]);
			}
			catch(BrandNotFoundException bn){
				m_exceptionCode = R.string.message_brandnotfound;
			}
		}
		
		return productsByBrand;
	}


	private List<Product> getProducts(String brand) {
		List<Product> productsByBrand;
		productsByBrand = m_catalog.getProductsByBrand(brand);
		return productsByBrand;
	}	

	

	@Override
	protected void onPostExecute(List<Product> result) {
		m_progDialog.dismiss();

		if(noError()){
			this.m_activity.viewProducts(result);
		}
		else{
			showError(m_exceptionCode);
		}
		
		super.onPostExecute(result);
	}


	private void showError(int m_exceptionCode) {
		if(m_exceptionCode==R.string.message_wrongparams_brandrequest)
			m_activity.showDialog(DialogMessage.DIALOG_WRONG_PARAMS_IN_BRAND_REQUEST);
		else if (m_exceptionCode == R.string.message_brandnotfound)
			m_activity.showDialog(DialogMessage.DIALOG_BRAND_NOT_FOUND);		
	}


	private boolean noError() {
		return m_exceptionCode == 0;
	}
	
	private boolean wrongParams(String... params) {
		return params.length == 0;
	}

	
}
