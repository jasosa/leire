package cat.hmobile.leire.GUI.Activities;

import org.jaxen.Context;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import cat.hmobile.leire.GUI.ActivityStarters.ProductDetailActivityStarter;
import cat.hmobile.leire.GUI.BackgroundTasks.SearchProductByEANTask;
import cat.hmobile.leire.GUI.Dialogs.DefaultDialogBuilder;
import cat.hmobile.leire.GUI.Dialogs.DialogMessage;
import cat.hmobile.leire.GUI.Dialogs.LeireDialogBuilder;
import cat.hmobile.leire.GUI.Helpers.CaptureBarCodeCurrentScreen;
import cat.hmobile.leire.GUI.Helpers.CaptureBarCodeScreenAction;
import cat.hmobile.leire.GUI.Helpers.CaptureBarCodeScreenSelector;
import cat.hmobile.leire.GUI.Helpers.ScanBundleHelper;
import cat.hmobile.leire.entities.catalog.CatalogHolder;
import cat.hmobile.leire.entities.products.Product;

import com.biggu.barcodescanner.client.android.CaptureActivity;
import com.biggu.barcodescanner.client.android.Intents;

public class CaptureBarCodeLauncherActivity extends Activity implements OnDismissListener, OnCancelListener, OnClickListener {
	
    private static final String LOG_TAG = "CaptureBarCodeLauncherActivity";
    private static final int SCANNER_REQUEST_CODE = 0;
	private CaptureBarCodeScreenSelector m_selector;
	private String m_lastBarCodeScannerResult;
	private Product m_lastProductFounded;
	private Dialog m_ScannerResult;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		CaptureBarCodeScreenSelector.startSelector();
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
	    switch(id) {
	    case DialogMessage.DIALOG_CATEGORY_WITHOUT_PRODUCTS:
	    	return DefaultDialogBuilder.create(this, R.string.warning_title,R.string.message_categorywithoutproducts);
	    case DialogMessage.DIALOG_QUERY_WITHOUT_PRODUCTS:
	    	return DefaultDialogBuilder.create(this, R.string.warning_title,R.string.message_querywithioutproducts);
	    	//break;
	    case DialogMessage.DIALOG_ILLEGAL_ARGUMENT_ON_LAYOUT:
	    	return DefaultDialogBuilder.create(this, R.string.warning_title, R.string.error_ean_illegal_layout_argument);
	    case DialogMessage.DIALOG_ILLEGAL_BARCODE_EMPTY:
	    	return DefaultDialogBuilder.create(this, R.string.warning_title, R.string.error_ean_empty);
	    case DialogMessage.DIALOG_ILLEGAL_EAN_FORMAT:
	    	return DefaultDialogBuilder.create(this, R.string.warning_title, R.string.error_ean_format);
	    case DialogMessage.DIALOG_BARCODE_NOT_FOUND:
	    	Dialog dialog = DefaultDialogBuilder.create(this, R.string.warning_title, R.string.message_barcode_notfound);
	    	dialog.setOnDismissListener(this);
	    	return dialog;
	    default:
	    return null;
	    }
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		if(resultCode == RESULT_CANCELED)
		{
			CaptureBarCodeScreenSelector.setLastAction(CaptureBarCodeScreenAction.GO_BACK);
		}
		else if (resultCode == RESULT_OK && requestCode == SCANNER_REQUEST_CODE)
		{
			CaptureBarCodeScreenSelector.setLastAction(CaptureBarCodeScreenAction.CAPTURE_OK);
			m_lastBarCodeScannerResult = data.getExtras().getString(ScanBundleHelper.SCAN_RESULT);
			doTheSearchInDb(m_lastBarCodeScannerResult);
		}
		else if (resultCode == RESULT_FIRST_USER && requestCode == SCANNER_REQUEST_CODE)
		{
			CaptureBarCodeScreenSelector.setLastAction(CaptureBarCodeScreenAction.CHANGE_MODE);
		}
	}
	
	@Override
	public void onDismiss(DialogInterface paramDialogInterface) {
		onResume();	
	}
	
	@Override
	public void onCancel(DialogInterface arg0) {
		onResume();
	}

	@Override
	public void onClick(View arg0) {
		if(m_lastProductFounded != null){
			m_ScannerResult.hide();
			loadProduct(m_lastProductFounded);
		}
	}

	@Override
	protected void onResume() 
	{
		loadCurrentScreen();
		super.onResume();
	}
	
	//Public Methods
	public void loadProduct(Product extractProductFrom) {
		ProductDetailActivityStarter.startActivity(this, 
				extractProductFrom);
	}
	
	public void showResultOkScreen(Product product){
		m_lastProductFounded = product;
		View layout = inflateDialogLayout(R.layout.fast_result_ok, R.id.fast_result_ok_root);
		TextView productName = (TextView) layout.findViewById(R.id.productnamevalue);
		TextView productBrand = (TextView) layout.findViewById(R.id.productbrandvalue);
		ImageButton detailButton = (ImageButton) layout.findViewById(R.id.button_view_detail);
		detailButton.setOnClickListener(this);
		productName.setText(product.getName());
		productBrand.setText(product.getBrand());
		buildResultDialog(layout).show();
	}
	
	public void showBarCodeNotFoundScreen(){
		View layout = inflateDialogLayout(R.layout.fast_result_error, R.id.fast_result_error_root);
		buildResultDialog(layout).show();
	}
	
	//Private Methods
	private Dialog buildResultDialog(View layout){
		
		LeireDialogBuilder builder = new LeireDialogBuilder(this);
		m_ScannerResult = builder.createCustom(layout);
		m_ScannerResult.setCancelable(true);
		m_ScannerResult.setOnCancelListener(this);
		return m_ScannerResult;
	}

	private View inflateDialogLayout(int resultDialogLayout, int rootViewId) {
		LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(resultDialogLayout, (ViewGroup) findViewById(rootViewId));
		return layout;
	}
	
	private void startAutomaticCapture()
	{
		Intent scannerCaptureIntent = new Intent(this, ScannerActivity.class);
		scannerCaptureIntent.putExtra(Intents.Preferences.ENABLE_BEEP, true);
		scannerCaptureIntent.putExtra(Intents.Preferences.ENABLE_VIBRATE, true);
		startActivityForResult(scannerCaptureIntent,SCANNER_REQUEST_CODE);
	}
	
	private void startManualCapture()
	{
		Intent manualCaptureIntent = new Intent(this, ManualScannerActivity.class);
		startActivityForResult(manualCaptureIntent, SCANNER_REQUEST_CODE);
	}	
	
	private void doTheSearchInDb(String barcode) {
		new SearchProductByEANTask(this, CatalogHolder.getGeneralCatalog()).execute(barcode);
	}
	
	protected void loadCurrentScreen() {
		
		int nextScreen = CaptureBarCodeScreenSelector.getNextScreen();
		
		if(nextScreen == CaptureBarCodeCurrentScreen.MANUAL)
			startManualCapture();
		else if (nextScreen == CaptureBarCodeCurrentScreen.AUTOMATIC)
			startAutomaticCapture();
		else if (nextScreen == CaptureBarCodeCurrentScreen.RESULT){
			//Wait to search events to  response
		}
		else if (nextScreen == CaptureBarCodeCurrentScreen.EXIT)
			this.finish();
	}

}
