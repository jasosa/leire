package cat.hmobile.leire.GUI.Helpers;

public class CaptureBarCodeScreenSelector {
	
	private static int m_captureCurrentScreen = CaptureBarCodeCurrentScreen.EXIT;
	private static int m_captureMode = CaptureBarCodeMode.AUTO;
	private static int m_lastAction = CaptureBarCodeScreenAction.CAPTURE_OK;
	
	public static void startSelector()
	{
		m_captureCurrentScreen = CaptureBarCodeCurrentScreen.RESULT;
		m_captureMode = CaptureBarCodeMode.AUTO;
		m_lastAction = CaptureBarCodeScreenAction.CAPTURE_OK;
	}
	
	/**
	 * @param CaptureBarCodeScreenAction
	 */
	public static void setLastAction(int captureBarCodeScreenAction)
	{
		m_lastAction = captureBarCodeScreenAction;
	}
	
	public static int getNextScreen()
	{
		if(lastActionIsGoBack())
		{
			m_captureCurrentScreen = CaptureBarCodeCurrentScreen.EXIT;
		}
		else if(lastActionIsCaptureFromScannerScreen())
		{
			m_captureCurrentScreen = CaptureBarCodeCurrentScreen.RESULT;
		}
		else if (lastActionIsChangeModeFromScannerScreen())
		{
			m_captureCurrentScreen = CaptureBarCodeCurrentScreen.MANUAL;
			m_captureMode = CaptureBarCodeMode.MANUAL;
		}
		else if(lastActionIsComeBackFromScannerScreenResult())
		{
			m_captureCurrentScreen = CaptureBarCodeCurrentScreen.AUTOMATIC;
		}
		else if (lastActionIsComeBackFromManualScreenResult())
		{
			m_captureCurrentScreen = CaptureBarCodeCurrentScreen.MANUAL;
		}
		else if(lastActionIsCaptureFromManualScreen())
		{
			m_captureCurrentScreen = CaptureBarCodeCurrentScreen.RESULT;
		}
		else if (lastActionIsChangeModeFromManualScreen())
		{
			m_captureCurrentScreen = CaptureBarCodeCurrentScreen.AUTOMATIC;
			m_captureMode = CaptureBarCodeMode.AUTO;
		}
		
		
		return m_captureCurrentScreen;
	}

	private static boolean lastActionIsChangeModeFromManualScreen() {
		return (weAreInManualScreen(m_captureCurrentScreen) && weAreChangingMode());
	}

	private static boolean lastActionIsCaptureFromManualScreen() {
		return (weAreInManualScreen(m_captureCurrentScreen) && weCaptured());
	}

	private static boolean lastActionIsComeBackFromManualScreenResult() {
		return (weAreInResultScreen(m_captureCurrentScreen) && weAreInManualMode());
	}

	private static boolean lastActionIsComeBackFromScannerScreenResult() {
		return (weAreInResultScreen(m_captureCurrentScreen) && weAreInAutoMode());
	}
	

	private static boolean lastActionIsChangeModeFromScannerScreen() {
		return (weAreInScannerScreen(m_captureCurrentScreen) && weAreChangingMode());
	}

	private static boolean lastActionIsCaptureFromScannerScreen() {
		 return (weAreInScannerScreen(m_captureCurrentScreen) && weCaptured());
	}


	private static boolean lastActionIsGoBack() {
		return  (m_lastAction == CaptureBarCodeScreenAction.GO_BACK);
	}

	private static boolean weAreInManualScreen(int currentScreen) {
		return (currentScreen == CaptureBarCodeCurrentScreen.MANUAL);
		}

	private static boolean weAreInScannerScreen(int currentScreen) {
		return (currentScreen == CaptureBarCodeCurrentScreen.AUTOMATIC);
	}
	
	private static boolean weAreInResultScreen(int currentScreen) {
		return (currentScreen == CaptureBarCodeCurrentScreen.RESULT);
	}
	
	private static boolean weAreChangingMode() {
		return (m_lastAction == CaptureBarCodeScreenAction.CHANGE_MODE);
	}
	
	private static boolean weAreInManualMode() {
		return (m_captureMode == CaptureBarCodeMode.MANUAL);
	}
	
	private static boolean weAreInAutoMode() {
		return (m_captureMode == CaptureBarCodeMode.AUTO);
	}

	private static boolean weCaptured() {
		return (m_lastAction == CaptureBarCodeScreenAction.CAPTURE_OK || 
				m_lastAction == CaptureBarCodeScreenAction.CAPTURE_WRONG);
	}
}
