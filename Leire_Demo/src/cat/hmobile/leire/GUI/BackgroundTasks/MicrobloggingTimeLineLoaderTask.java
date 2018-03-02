package cat.hmobile.leire.GUI.BackgroundTasks;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import cat.hmobile.leire.GUI.Activities.R;
import cat.hmobile.leire.GUI.Activities.TimeLineActivity;
import cat.hmobile.leire.GUI.Dialogs.DefaultDialogBuilder;
import cat.hmobile.leire.GUI.Dialogs.DialogMessage;
import cat.hmobile.leire.GUI.Dialogs.LeireAlertDialog;
import cat.hmobile.leire.GUI.Dialogs.LeireProgressDialog;
import cat.hmobile.leire.business.microblogging.IMicrobloggingService;
import cat.hmobile.leire.business.microblogging.MicrobloggingException;
import cat.hmobile.leire.business.microblogging.MicrobloggingService;
import cat.hmobile.leire.entities.LeireException;
import cat.hmobile.leire.entities.microblogging.IMicrobloggingMessage;
import cat.hmobile.leire.entities.microblogging.MicrobloggingAuthenticationException;
import cat.hmobile.leire.entities.microblogging.MicrobloggingNetworkException;
import cat.hmobile.leire.entities.products.Product;

import android.app.Activity;
import android.os.AsyncTask;

public class MicrobloggingTimeLineLoaderTask extends
		AsyncTask<Void, Void, List<IMicrobloggingMessage>> {

	IMicrobloggingService m_twitterService;
	TimeLineActivity m_callingActivity;
	LeireProgressDialog m_progDialog;
	LeireException m_exception;
	boolean m_showDialog;
	
	public MicrobloggingTimeLineLoaderTask (TimeLineActivity callingActivity, IMicrobloggingService service, boolean showDialog){
		m_callingActivity = callingActivity;
		m_twitterService = service;
		m_exception = null;
		m_showDialog = showDialog;
	}
	
	@Override
	protected void onPreExecute(){
		if(m_showDialog){
		m_progDialog = LeireProgressDialog.show(m_callingActivity, "", 
				m_callingActivity.getString(R.string.message_loading), true);
		}
	}
	
	@Override
	protected List<IMicrobloggingMessage> doInBackground(Void... arg0) {
		
		if(m_showDialog){ 
			m_progDialog.show();
		}
		List<IMicrobloggingMessage> messages = new ArrayList<IMicrobloggingMessage>();
		try{
			messages = m_twitterService.getMessagesFromGroup(MicrobloggingService.GENERAL_GROUP_NAME);
			//messages = m_twitterService.getHomeTimeLine();
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
		
		return messages;
	}
	

	@Override
	protected void onPostExecute(List<IMicrobloggingMessage> result) {
		
		if(m_exception==null){
			showResult(result);
		}
		else{
			showError();
		}
		

		if(m_showDialog){
			m_progDialog.dismiss();
		}
	}

	private void showResult(List<IMicrobloggingMessage> result) {
		m_callingActivity.showHomeTimeLine(result);
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
