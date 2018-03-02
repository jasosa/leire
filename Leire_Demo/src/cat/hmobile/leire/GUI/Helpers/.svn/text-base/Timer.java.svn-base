package cat.hmobile.leire.GUI.Helpers;

import android.os.Handler;

public class Timer implements Runnable {

	Handler m_target;
	int m_milisecondsStartDelay;
	int m_milisecondsBetweenExecutions;
	boolean m_delayTimeFinished;
	
	public Timer(Handler target, int milisecondsToStart, int milisecondsBetweenExecutions){
		m_target = target;
		m_milisecondsStartDelay = milisecondsToStart;
		m_milisecondsBetweenExecutions = milisecondsBetweenExecutions;
		m_delayTimeFinished = false;
	}
	
	@Override
	public void run() {
		
		if(!m_delayTimeFinished && m_milisecondsStartDelay > 0){
			m_target.postDelayed(this, m_milisecondsStartDelay);
			m_delayTimeFinished = true;
		}
		else{
			m_target.sendEmptyMessage(0);
			m_target.postDelayed(this, m_milisecondsBetweenExecutions);
		}
	}
	
	public void stop(){
		m_target.removeCallbacks(this);
	}
	
	public void resume(){
		m_target.postDelayed(this, m_milisecondsBetweenExecutions);
	}
	
}
