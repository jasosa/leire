package cat.hmobile.leire.GUI.Activities;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class GeneralActivity extends Activity implements OnClickListener {

	protected void initializeCommonLayoutElements(){
		ImageButton backButton = (ImageButton) findViewById(R.id.button_back);
		backButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		this.finish();
	}
	
}
