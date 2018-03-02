package cat.hmobile.leire.GUI.Activities;

import cat.hmobile.leire.GUI.BackgroundTasks.SendTweetTask;
import cat.hmobile.leire.GUI.Dialogs.DefaultDialogBuilder;
import cat.hmobile.leire.GUI.Dialogs.DialogButtons;
import cat.hmobile.leire.GUI.Dialogs.DialogMessage;
import cat.hmobile.leire.GUI.Dialogs.LeireAlertDialog;
import cat.hmobile.leire.GUI.Dialogs.LeireDialogBuilder;
import cat.hmobile.leire.GUI.Helpers.ActivityRequests;
import cat.hmobile.leire.business.microblogging.IMicrobloggingService;
import cat.hmobile.leire.business.microblogging.MicrobloggingService;
import cat.hmobile.leire.business.microblogging.statusnetextension.StatusNet;
import cat.hmobile.leire.business.microblogging.statusnetextension.StatusNetMarkersFormatter;
import cat.hmobile.leire.thirdparty.jtwitter.TwitterException;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class SendTweetActivity extends GeneralActivity{
	
	EditText m_statusText;
	TextView m_remainCharacters;
	Button m_sendTweet;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send_tweet);
		initializeCommonLayoutElements();
		m_statusText = (EditText) findViewById(R.id.sendTweet);
		m_statusText.addTextChangedListener(inputTextWatcher);
		m_remainCharacters = (TextView) findViewById(R.id.text_remain_characters);
		m_sendTweet = (Button) findViewById(R.id.sendButton);
		m_sendTweet.setOnClickListener(this); 
		setMessageType(getIntent().getExtras());
	}
	

	@Override 
	public void onClick(View arg0) {
		if(arg0.getId() == R.id.sendButton){
			sendMessage();
		}else
			super.onClick(arg0);
	};
	
	@Override
	protected android.app.Dialog onCreateDialog(int id) {
		
		LeireAlertDialog dialog = null;
		
		switch(id){
		case DialogMessage.DIALOG_NETWORK_ERROR:
			dialog = DefaultDialogBuilder.create(this, R.string.error_title, R.string.network_error);		
			break;
		case DialogMessage.DIALOG_MICROBLOGGING_CREDENTIALS_ERROR:
			dialog = DefaultDialogBuilder.create(this, R.string.error_title, R.string.statusnet_credendtials_error);
			break;
		case DialogMessage.DIALOG_MICROBLOGGING_PARAMS_BLANK:
			dialog = DefaultDialogBuilder.create(this, R.string.error_title, R.string.statusnet_params_blank);
			break;
		case DialogMessage.DIALOG_TWEET_SEND_OK:
			dialog = DefaultDialogBuilder.create(this, R.string.warning_title, R.string.tweet_send_ok);
			break;
		}
		
		//dialog.setOnDismissListener(this);
		return dialog;
	};
	
	private void setMessageType(Bundle extras) {
		if(isReplyMessage(extras)){
			setUserToReplyInText(extras);
		}
		else{
			
		}
	}
	
	private void setUserToReplyInText(Bundle extras) {
		m_statusText.setText("@" + extras.getString(ActivityRequests.REPLY_TWEET_REQUEST));
	}


	private boolean isReplyMessage(Bundle extras) {
		return extras.containsKey(ActivityRequests.REPLY_TWEET_REQUEST);
	}


	private void sendMessage() {
		IMicrobloggingService m_twitterService = MicrobloggingService.getService();
		SendTweetTask task = new SendTweetTask(this, m_twitterService);
		task.execute(m_statusText.getText().toString());
//		try{
//			String marker = StatusNetMarkersFormatter.CELIAC_GENERAL_GROUP_MARKER;
//			String messaeWithMarker = StatusNetMarkersFormatter.addMarkers(m_statusText.getText().toString(), marker) ;
//			m_twitterService.sendMessage(messaeWithMarker);
//		}
//		catch(TwitterException te){
//			DefaultDialogBuilder.create(this, R.string.error_title, te.getMessage()).show();
//		}
	}

	private TextWatcher inputTextWatcher = new TextWatcher() {
		boolean maxExceeded;
		@Override
		public void afterTextChanged(Editable s) 
		{}
		
	    public void beforeTextChanged(CharSequence s, int start, int count, int after){	
	    }
	    
	    public void onTextChanged(CharSequence s, int start, int before, int count) {
	    	int chars = 140 - s.length();
			m_remainCharacters.setText(String.valueOf(chars));
			if(chars < 0){
				m_remainCharacters.setTextColor(getResources().getColor(R.color.red));
				maxExceeded = true;
			}
			else if(maxExceeded){
				m_remainCharacters.setTextColor(getResources().getColor(R.color.green));
				maxExceeded = false;
			}
	    }
	};
}
