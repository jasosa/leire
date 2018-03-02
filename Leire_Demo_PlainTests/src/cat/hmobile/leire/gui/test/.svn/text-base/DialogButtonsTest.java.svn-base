package cat.hmobile.leire.gui.test;

import static org.junit.Assert.*;

import org.junit.Test;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

import cat.hmobile.leire.GUI.Activities.R;
import cat.hmobile.leire.GUI.Dialogs.DialogButtons;

public class DialogButtonsTest {

		boolean  listenerClicked  = false;
		boolean negativeListenerClicked = false;
	
		@Test
		public void definePositiveButtonTest(){
			DialogButtons buttons = new DialogButtons();
			buttons.addPositiveButton(R.string.hello, null);
			assertTrue(buttons.isPositiveButtonDefined());
			assertEquals(R.string.hello, buttons.getPositiveButtonTextId());
		}
		
		@Test
		public void definePositiveButtonTestFalse(){
			DialogButtons buttons = new DialogButtons();
			buttons.addPositiveButton(R.string.hello, null);
			assertNotSame(R.string.error_ean_empty, buttons.getPositiveButtonTextId());
			}

		
		@Test
		public void definePositiveButtonTestListener(){
			
			OnClickListener listener = new OnClickListener()
			{
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					 listenerClicked = true;
				}
			};

			DialogButtons buttons = new DialogButtons();
			buttons.addPositiveButton(R.string.hello, listener);
			assertTrue(buttons.isPositiveButtonDefined());
			assertEquals(R.string.hello, buttons.getPositiveButtonTextId());
			buttons.getPositiveButtonListener().onClick(null, 0);
			assertTrue(listenerClicked);
		}
		
		@Test
		public void defineNegativeButtonTest(){
			DialogButtons buttons = new DialogButtons();
			buttons.addNegativeButton(R.string.hello, null);
			assertFalse(buttons.isPositiveButtonDefined());
			assertTrue(buttons.isNegativeButtonDefined());
			assertEquals(0, buttons.getPositiveButtonTextId());
			assertEquals(R.string.hello, buttons.getNegativeButtonTextId());
		}
		
		@Test
		public void defineNegativeButtonTestListener(){
			
			OnClickListener listener = new OnClickListener()
			{
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					 negativeListenerClicked = true;
				}
			};

			DialogButtons buttons = new DialogButtons();
			buttons.addNegativeButton(R.string.hello, listener);
			assertTrue(buttons.isNegativeButtonDefined());
			buttons.getNegativeButtonListener().onClick(null, 0);
			assertTrue(negativeListenerClicked);
		}
}