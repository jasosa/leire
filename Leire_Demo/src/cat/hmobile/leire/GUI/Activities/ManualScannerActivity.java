package cat.hmobile.leire.GUI.Activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import cat.hmobile.leire.GUI.Dialogs.DefaultDialogBuilder;
import cat.hmobile.leire.GUI.Helpers.ScanBundleHelper;
import cat.hmobile.leire.entities.ArgumentException;
import cat.hmobile.leire.entities.barcode.BarCodeException;
import cat.hmobile.leire.entities.barcode.BarCodeValidator;
import cat.hmobile.leire.entities.barcode.layouts.BarCodeLayoutFactory;

public class ManualScannerActivity extends Activity implements View.OnClickListener{

    private static final String LOG_TAG = "ManualScannerActivity";
    
	private static final int EMPTY_BARCODE = 2;
	private static final int ILLEGAL_LAYOUT_ARGUMENT = 3;
	private static final int WRONG_BARCODE_FORMAT = 4;
	private static final int WRONG_CHECK_DIGIT = 5;
	
	private EditText m_editText;
	private ImageButton m_imageButton;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.manualscannertab);
		
		if(m_editText == null)
			m_editText = (EditText) this.findViewById(R.id.editReading);
		
		if(m_imageButton == null){
			m_imageButton = (ImageButton) this.findViewById(R.id.button_manualbarcode_search);
			m_imageButton.setOnClickListener(this);
		}
	}
	
	@Override 
	protected android.app.Dialog onCreateDialog(int id) {
		Dialog dialog = null;
		switch(id){
		case EMPTY_BARCODE:
			dialog = DefaultDialogBuilder.create(this, R.string.error_title, R.string.error_ean_empty);
			break;
		case ILLEGAL_LAYOUT_ARGUMENT:
			dialog = DefaultDialogBuilder.create(this, R.string.error_title, R.string.error_ean_illegal_layout_argument);
			break;
		case WRONG_BARCODE_FORMAT:
			dialog = DefaultDialogBuilder.create(this, R.string.error_title, R.string.error_ean_format);
			break;
		case WRONG_CHECK_DIGIT:
			dialog = DefaultDialogBuilder.create(this, R.string.error_title, R.string.error_ean_checkdigit);
			break;
		default:
			break;
		}
		
		return dialog;
		
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
    	MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_manual_capture, menu);
		return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	switch(item.getItemId())
    	{
    		case R.id.menu_go_to_scanner:
    			setResult(RESULT_FIRST_USER);
    			finish();
    			break;
    	}
    	
    	return super.onOptionsItemSelected(item);
    }
    
	private void validateBarCode(String barCode) {
		
		try{
			BarCodeValidator.validate(barCode, BarCodeLayoutFactory.build(barCode));
			Intent intentResult = new Intent();
			intentResult.putExtra(ScanBundleHelper.SCAN_RESULT, barCode);
			this.setResult(RESULT_OK,  intentResult);
			this.finish();
		}
		catch(ArgumentException ae){
			if(ae.getErrorMessageCode() == R.string.error_ean_illegal_layout_argument)
				showDialog(ILLEGAL_LAYOUT_ARGUMENT);
			else if (ae.getErrorMessageCode() == R.string.error_ean_empty)
				showDialog(EMPTY_BARCODE);
		}
		catch(BarCodeException be){
			if(be.getErrorMessageCode() == R.string.error_ean_format)
				showDialog(WRONG_BARCODE_FORMAT);
			else if (be.getErrorMessageCode() == R.string.error_ean_empty)
				showDialog(EMPTY_BARCODE);
			else if (be.getErrorMessageCode() == R.string.error_ean_checkdigit)
				showDialog(WRONG_CHECK_DIGIT);
		}
		
	}

	@Override
	public void onClick(View paramView) {
		this.validateBarCode(m_editText.getText().toString());		
	}

}
