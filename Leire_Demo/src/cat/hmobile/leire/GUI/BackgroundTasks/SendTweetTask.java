package cat.hmobile.leire.GUI.BackgroundTasks;

import android.os.AsyncTask;
import cat.hmobile.leire.GUI.Activities.R;
import cat.hmobile.leire.GUI.Activities.SendTweetActivity;
import cat.hmobile.leire.GUI.Dialogs.DefaultDialogBuilder;
import cat.hmobile.leire.GUI.Dialogs.DialogMessage;
import cat.hmobile.leire.GUI.Dialogs.LeireAlertDialog;
import cat.hmobile.leire.GUI.Dialogs.LeireProgressDialog;
import cat.hmobile.leire.business.microblogging.IMicrobloggingService;
import cat.hmobile.leire.business.microblogging.MicrobloggingException;
import cat.hmobile.leire.business.microblogging.statusnetextension.StatusNetMarkersFormatter;
import cat.hmobile.leire.entities.LeireException;
import cat.hmobile.leire.entities.microblogging.MicrobloggingAuthenticationException;
import cat.hmobile.leire.entities.microblogging.MicrobloggingNetworkException;

public class SendTweetTask extends AsyncTask<String, Void, Void> {

	IMicrobloggingService m_twitterService;
	SendTweetActivity m_callingActivity;
	LeireProgressDialog m_progDialog;
	LeireException m_exception;
	
	public SendTweetTask(SendTweetActivity activity, IMicrobloggingService microbloggingService){
		m_twitterService = microbloggingService;
		m_callingActivity = activity;
	}
	
	@Override
	protected void onPreExecute(){
		m_progDialog = LeireProgressDialog.show(m_callingActivity, "", 
				m_callingActivity.getString(R.string.message_sending), true);
	}
	
	@Override
	protected Void doInBackground(String... paramArrayOfParams) {
		
		m_progDialog.show();
		
		try{
			String message = paramArrayOfParams[0];
			String marker = StatusNetMarkersFormatter.CELIAC_GENERAL_GROUP_MARKER;
			String messaeWithMarker = StatusNetMarkersFormatter.addMarkers(message, marker) ;
			m_twitterService.sendMessage(messaeWithMarker);
		}
		catch(MicrobloggingNetworkException me){
			m_exception = me;
		}
		catch(MicrobloggingAuthenticationException ma){
			m_exception = ma;
		}
		catch(MicrobloggingException mde){
			m_exception = mde;
		}
		
		return null;
	}
	
	@Override
	protected void onPostExecute(Void result) {
		m_progDialog.dismiss();
		if(m_exception!=null){
			showError();
		}
		else{
			showSuccedMessage();
		}
	}
	
	private void showSuccedMessage() {
		m_callingActivity.showDialog(DialogMessage.DIALOG_TWEET_SEND_OK);
	}

	private void showError() {
		if(m_exception.getClass() == MicrobloggingNetworkException.class){
			m_callingActivity.showDialog(DialogMessage.DIALOG_NETWORK_ERROR);
		}
		else if (m_exception.getClass() == MicrobloggingAuthenticationException.class){
			m_callingActivity.showDialog(DialogMessage.DIALOG_MICROBLOGGING_CREDENTIALS_ERROR);
		}
		else{
			LeireAlertDialog dialog = DefaultDialogBuilder.create(m_callingActivity, R.string.error_title, m_exception.getMessage());
			dialog.show();
		}
	}	

}
