package cat.hmobile.leire.data.xml.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cat.hmobile.leire.data.xml.ProductsXmlAccessor;
import cat.hmobile.leire.data.xml.ProductsUpdatesXmlAccesor;

public class ProductsUpdatesXmlAccesorTest {

	private ProductsUpdatesXmlAccesor m_productsUpdates;
	
	@Before
	public void setUp() throws Exception {
		this.m_productsUpdates = new ProductsUpdatesXmlAccesor();
	}
	
	@Test
	public void testLoadLastUpdates()
	{
		List<org.jdom.Element> lastUpdates = m_productsUpdates.loadLastUpdates();
		assertEquals(3, lastUpdates.size());
	}
}
