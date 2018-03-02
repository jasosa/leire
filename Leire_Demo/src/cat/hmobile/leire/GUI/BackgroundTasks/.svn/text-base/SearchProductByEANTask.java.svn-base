package cat.hmobile.leire.GUI.BackgroundTasks;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import cat.hmobile.leire.GUI.Activities.CaptureBarCodeLauncherActivity;
import cat.hmobile.leire.GUI.Activities.R;
import cat.hmobile.leire.GUI.Dialogs.DialogMessage;
import cat.hmobile.leire.GUI.Dialogs.LeireProgressDialog;
import cat.hmobile.leire.business.ICatalogNavigator;
import cat.hmobile.leire.entities.ArgumentException;
import cat.hmobile.leire.entities.barcode.BarCodeException;
import cat.hmobile.leire.entities.products.Product;

public class SearchProductByEANTask extends AsyncTask<String, Void, Product> {

	private LeireProgressDialog m_progDialog;
	private CaptureBarCodeLauncherActivity m_activity;
	private int m_exceptionCode;
	private ICatalogNavigator m_catalog;
	
	public SearchProductByEANTask(CaptureBarCodeLauncherActivity activity, ICatalogNavigator catalog) {
		m_activity = activity;
		m_exceptionCode = 0;
		m_catalog = catalog;
	}
	
	@Override
	protected void onPreExecute(){
		m_progDialog = LeireProgressDialog.show(m_activity, "", 
				m_activity.getString(R.string.message_loading), true);
	}
	
	@Override
	protected Product doInBackground(String... paramArrayOfParams) {
		
		Product product = null;
		try{
			product = m_catalog.getProductByEan(paramArrayOfParams[0]);
			if(product == null) m_exceptionCode = R.string.message_barcode_notfound;
		}
		catch(ArgumentException ae)
		{
			m_exceptionCode = R.string.error_ean_illegal_layout_argument;
		}
		catch(BarCodeException be)
		{
			if(be.getErrorMessageCode()==R.string.error_ean_empty)
				m_exceptionCode = R.string.error_ean_empty;
			else
				m_exceptionCode = R.string.error_ean_format;
		}
		
		return product;
	}
	
	@Override
	protected void onPostExecute(Product result) {
		m_progDialog.dismiss();

		if(m_exceptionCode == 0){
			m_activity.showResultOkScreen(result);
		}
		else{
			showBarCodeScannerError(m_exceptionCode);
		}
		//super.onPostExecute(result);
	}
	
	protected void showBarCodeScannerError(int errorMessageId) {
			if(errorMessageId==R.string.error_ean_format)
				m_activity.showDialog(DialogMessage.DIALOG_ILLEGAL_EAN_FORMAT);
			else if (errorMessageId == R.string.error_ean_empty)
				m_activity.showDialog(DialogMessage.DIALOG_ILLEGAL_BARCODE_EMPTY);
			else if (errorMessageId==R.string.error_ean_checkdigit)
				m_activity.showDialog(DialogMessage.DIALOG_ILLEGAL_EAN_CHECK_DIGIT_FORMAT);
			else if (errorMessageId==R.string.error_ean_illegal_layout_argument)
				m_activity.showDialog(DialogMessage.DIALOG_ILLEGAL_ARGUMENT_ON_LAYOUT);
			else if (errorMessageId == R.string.message_barcode_notfound)
				m_activity.showBarCodeNotFoundScreen();
				//m_activity.showDialog(DialogMessage.DIALOG_BARCODE_NOT_FOUND);
	}
}
