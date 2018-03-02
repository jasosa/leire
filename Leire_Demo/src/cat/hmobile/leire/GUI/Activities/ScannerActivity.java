package cat.hmobile.leire.GUI.Activities;

import android.graphics.Camera;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.FrameLayout;

import com.biggu.barcodescanner.client.android.CaptureActivity;

public class ScannerActivity extends CaptureActivity { 

    private static final String LOG_TAG = "ScannerActivity";

	@Override
	public void onCreate(Bundle icicle) {
			super.onCreate(icicle);
	};
	
	@Override
	public int get_R_id_preview_view() {
		return R.id.preview_view;
	}

	@Override
	public int get_R_id_viewfinder_view() {
		return R.id.viewfinder_view;
	}

	@Override
	public int get_R_layout_scanner() {
		return R.layout.scanner;
	}

	@Override
	public int get_R_raw_beep() {
		return R.raw.beep; 
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
    	MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_scanner, menu);
		return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	switch(item.getItemId())
    	{
    		case R.id.menu_go_to_manual:
    			setResult(RESULT_FIRST_USER);
    			finish();
    			break;
    	}
    	return super.onOptionsItemSelected(item);
    }
}
