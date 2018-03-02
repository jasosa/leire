package cat.hmobile.leire.GUI.Dialogs;

import cat.hmobile.leire.GUI.Activities.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LeireProgressDialog extends Dialog {

	private static View m_layout;
	private static TextView m_text;
	static AnimationDrawable m_frameAnimation;

	public static LeireProgressDialog show(Context context, CharSequence title,
	        CharSequence message) {
	    return show(context, title, message, false);
	}

	public static LeireProgressDialog show(Context context, CharSequence title,
	        CharSequence message, boolean indeterminate) {
	    return show(context, title, message, indeterminate, false, null);
	}

	public static LeireProgressDialog show(Context context, CharSequence title,
	        CharSequence message, boolean indeterminate, boolean cancelable) {
	    return show(context, title, message, indeterminate, cancelable, null);
	}

	public static LeireProgressDialog show(Context context, CharSequence title,
	        CharSequence message, boolean indeterminate,
	        boolean cancelable, OnCancelListener cancelListener) {
	    LeireProgressDialog dialog = new LeireProgressDialog(context);
	    dialog.setTitle(title);
	    dialog.setCancelable(cancelable);
	    dialog.setOnCancelListener(cancelListener);
	    dialog.setContentView(m_layout);
	    m_text.setText(message);
	    dialog.show();
	   
	    return dialog;
	}

	public LeireProgressDialog(Context context) {
	    super(context, R.style.Leire_ProgressDialog);
	    LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		m_layout = inflater.inflate(R.layout.progressdialog, (ViewGroup) findViewById(R.id.layout_progressDialog));
		 m_text = (TextView) m_layout.findViewById(R.id.text_progress);
	}
}
