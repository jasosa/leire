package cat.hmobile.leire.business.test;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import cat.hmobile.leire.GUI.Helpers.TimeAgoDateConverter;
import cat.hmobile.leire.business.microblogging.statusnetextension.StatusNetMarkersFormatter;

public class StatusNetFormatterMarkersTest {
	@Test
	public void RemoveMarkers1(){
		assertEquals("Hola mi primer mensaje", 
				StatusNetMarkersFormatter.removeMarkers("!celiacgeneral Hola mi primer mensaje"));
	}
	
	@Test
	public void AddMarkers(){
		assertEquals("!celiacgeneral Hola mi primer mensaje", 
				StatusNetMarkersFormatter.addMarkers("Hola mi primer mensaje",
				StatusNetMarkersFormatter.CELIAC_GENERAL_GROUP_MARKER));
	}
}
