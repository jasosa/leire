package cat.hmobile.leire.gui.test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import cat.hmobile.leire.GUI.Helpers.CaptureBarCodeCurrentScreen;
import cat.hmobile.leire.GUI.Helpers.CaptureBarCodeScreenAction;
import cat.hmobile.leire.GUI.Helpers.CaptureBarCodeScreenSelector;

public class BarCodeScreenSelectorTest {
	
	@Test
	public void startBarCodeCaptureInScannerScreen() 
	{
		CaptureBarCodeScreenSelector.startSelector();
		assertEquals(CaptureBarCodeScreenSelector.getNextScreen(),CaptureBarCodeCurrentScreen.AUTOMATIC);
	}
	
	@Test
	public void goBackFromScannerScreenAndExit()
	{
		CaptureBarCodeScreenSelector.startSelector();
		CaptureBarCodeScreenSelector.getNextScreen();
		CaptureBarCodeScreenSelector.setLastAction(CaptureBarCodeScreenAction.GO_BACK);
		assertEquals(CaptureBarCodeScreenSelector.getNextScreen(),CaptureBarCodeCurrentScreen.EXIT);
	}
	
	@Test
	public void goToScannerScreenAndCaptureBarCodeSuccesfully()
	{
		CaptureBarCodeScreenSelector.startSelector();
		CaptureBarCodeScreenSelector.getNextScreen();
		CaptureBarCodeScreenSelector.setLastAction(CaptureBarCodeScreenAction.CAPTURE_OK);
		assertEquals(CaptureBarCodeScreenSelector.getNextScreen(),CaptureBarCodeCurrentScreen.RESULT);
	}
	
	@Test
	public void goToScannerScreenAndCaptureBarCodeThatNotExistsInDB()
	{
		CaptureBarCodeScreenSelector.startSelector();
		CaptureBarCodeScreenSelector.getNextScreen();
		CaptureBarCodeScreenSelector.setLastAction(CaptureBarCodeScreenAction.CAPTURE_WRONG);
		assertEquals(CaptureBarCodeScreenSelector.getNextScreen(),CaptureBarCodeCurrentScreen.RESULT);
	}
	
	@Test
	public void goToScannerScreenAndChangeToManualScreen()
	{
		CaptureBarCodeScreenSelector.startSelector();
		CaptureBarCodeScreenSelector.getNextScreen();
		CaptureBarCodeScreenSelector.setLastAction(CaptureBarCodeScreenAction.CHANGE_MODE);
		assertEquals(CaptureBarCodeScreenSelector.getNextScreen(),CaptureBarCodeCurrentScreen.MANUAL);
	}
	
	@Test
	public void goToManualScreenAndCaptureBarCodeSuccesfully()
	{
		CaptureBarCodeScreenSelector.startSelector();
		CaptureBarCodeScreenSelector.getNextScreen();
		CaptureBarCodeScreenSelector.setLastAction(CaptureBarCodeScreenAction.CAPTURE_OK);
		assertEquals(CaptureBarCodeScreenSelector.getNextScreen(),CaptureBarCodeCurrentScreen.RESULT);
	}
	
	@Test
	public void goBackFromManualScreenAndExit()
	{
		CaptureBarCodeScreenSelector.startSelector();
		CaptureBarCodeScreenSelector.getNextScreen();
		CaptureBarCodeScreenSelector.setLastAction(CaptureBarCodeScreenAction.CHANGE_MODE);
		CaptureBarCodeScreenSelector.getNextScreen();
		CaptureBarCodeScreenSelector.setLastAction(CaptureBarCodeScreenAction.GO_BACK);
		assertEquals(CaptureBarCodeScreenSelector.getNextScreen(),CaptureBarCodeCurrentScreen.EXIT);
	}
	
	
	@Test
	public void goToManualScreenAndCaptureBarCodeThatNotExistsInDB()
	{
		CaptureBarCodeScreenSelector.startSelector();
		CaptureBarCodeScreenSelector.getNextScreen();
		CaptureBarCodeScreenSelector.setLastAction(CaptureBarCodeScreenAction.CHANGE_MODE);
		CaptureBarCodeScreenSelector.getNextScreen();
		CaptureBarCodeScreenSelector.setLastAction(CaptureBarCodeScreenAction.CAPTURE_WRONG);
		assertEquals(CaptureBarCodeScreenSelector.getNextScreen(),CaptureBarCodeCurrentScreen.RESULT);
	}
	
	@Test
	public void goToManualScreenAndChangeToScannerScreen()
	{
		CaptureBarCodeScreenSelector.startSelector();
		CaptureBarCodeScreenSelector.getNextScreen();
		CaptureBarCodeScreenSelector.setLastAction(CaptureBarCodeScreenAction.CHANGE_MODE);
		CaptureBarCodeScreenSelector.getNextScreen();
		CaptureBarCodeScreenSelector.setLastAction(CaptureBarCodeScreenAction.CHANGE_MODE);
		assertEquals(CaptureBarCodeScreenSelector.getNextScreen(),CaptureBarCodeCurrentScreen.AUTOMATIC);
	}
}
