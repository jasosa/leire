package cat.hmobile.leire.GUI.Activities;

import cat.hmobile.leire.GUI.Helpers.ActivityRequests;
import cat.hmobile.leire.GUI.Helpers.ActivityResults;
import cat.hmobile.leire.GUI.Helpers.MicrobloggingBundleHelper;
import cat.hmobile.leire.business.microblogging.MicrobloggingService;
import cat.hmobile.leire.entities.microblogging.IMicrobloggingMessage;
import cat.hmobile.leire.entities.microblogging.IMicrobloggingUser;
import cat.hmobile.leire.entities.microblogging.MicrobloggingUser;
import cat.hmobile.leire.entities.microblogging.MicrobloggingUsersHolder;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TweetDetailActivity extends GeneralActivity{

		IMicrobloggingUser m_user; 
	
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.tweet_detail);
			initializeCommonLayoutElements();
			fillLayoutWithMessageInfo(getIntent().getExtras());
			initializeButtons();
		}
		
		@Override
		public void onClick(android.view.View v) {
			if(v.getId()==R.id.replyButton){
				goToSendTweetActivity();
			}
			else{
				super.onClick(v);
			}
		};
		
		private void goToSendTweetActivity() {
			Intent i = new Intent(this, SendTweetActivity.class);
			i.putExtra(ActivityRequests.REPLY_TWEET_REQUEST, m_user.getScreenName());
		    this.startActivityForResult(i, ActivityResults.REPLY_TWEET_REQUEST);
		}

		private void initializeButtons() {
			Button replyButton = (Button) findViewById(R.id.replyButton);
			if(m_user.isCurrentUser()){
				replyButton.setVisibility(View.GONE);
			}
			else{
				replyButton.setOnClickListener(this);
			}
		}

		private void fillLayoutWithMessageInfo(Bundle extras) {
			TextView textMessage = (TextView) findViewById(R.id.text_tweetmessage);
			TextView textUser = (TextView) findViewById(R.id.text_user);
			TextView textTimeAgo = (TextView) findViewById(R.id.text_timeAgo);
			ImageView userAvatar = (ImageView) findViewById(R.id.useravatar);
			
			textMessage.setText(extras.getString(MicrobloggingBundleHelper.KEY_MESSAGE_TEXT));
			textTimeAgo.setText(extras.getString(MicrobloggingBundleHelper.KEY_MESSAGE_CREATED_AT));
			Long userId = extras.getLong(MicrobloggingBundleHelper.KEY_MESSAGE_USER_ID);
			m_user = MicrobloggingUsersHolder.getHolder().getUser(userId);
			textUser.setText(m_user.getScreenName());
			userAvatar.setImageBitmap(m_user.getImage());
		}
}
