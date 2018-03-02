package cat.hmobile.leire.entities.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cat.hmobile.leire.entities.updates.UpdateHeader;

public class UpdatesHeaderTest {
	
	private static final String DATE = "23/12/2010";
	private static final int PRODUCTS_IN = 10;
	private static final int PRODUCTS_OUT = 7;
	private static final String DETAIL_FILE_PATH = "updates/20100823_01.xml";
	
	
	@Before
	public void setUp() throws Exception {
		
	}
	
	@Test
	public void testCreateProductsUpdate(){
		UpdateHeader lastUpdate = new UpdateHeader(DATE, PRODUCTS_IN, PRODUCTS_OUT, DETAIL_FILE_PATH);
		assertEquals(DATE, lastUpdate.getDate());
		assertEquals(PRODUCTS_IN, lastUpdate.getNumberOfProductsIn());
		assertEquals(PRODUCTS_OUT, lastUpdate.getNumberProductsOut());
		assertEquals(DETAIL_FILE_PATH, lastUpdate.getDetailFilePath());
	}
	
}
