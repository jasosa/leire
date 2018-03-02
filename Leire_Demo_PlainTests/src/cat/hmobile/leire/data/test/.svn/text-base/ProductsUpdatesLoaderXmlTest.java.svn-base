package cat.hmobile.leire.data.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cat.hmobile.leire.business.Catalog;
import cat.hmobile.leire.data.CategoryLoaderXml;
import cat.hmobile.leire.data.ProductLoaderXml;
import cat.hmobile.leire.data.ProductUpdateLoaderXml;
import cat.hmobile.leire.data.UpdatesHeaderLoaderXml;
import cat.hmobile.leire.entities.updates.IUpdatesHeaderLoader;
import cat.hmobile.leire.entities.updates.UpdateHeader;

public class ProductsUpdatesLoaderXmlTest {
	
	private static final String DETAIL_FILE_PATH = "updates/20100823_01.xml";
	private static final String ADDED = "A–adidos";
	private static final String REMOVED = "Eliminados";

	
	@Before
	public void setUp() throws Exception {
		
	}
	
	@Test
	public void testLoadLasUpdatesList()
	{
		IUpdatesHeaderLoader updatesXmlLoader = new UpdatesHeaderLoaderXml();
		List<UpdateHeader> updatesList = new ArrayList<UpdateHeader>();
		updatesList = updatesXmlLoader.loadLastUpdates();
		assertEquals(3, updatesList.size());
		assertEquals(4, updatesList.get(0).getNumberOfProductsIn());
		assertEquals(DETAIL_FILE_PATH, updatesList.get(0).getDetailFilePath());
	}
	
	@Test
	public void testLoadProductsUpdateDetail(){
		
		IUpdatesHeaderLoader updatesXmlLoader = new UpdatesHeaderLoaderXml();
		List<UpdateHeader> updatesList = new ArrayList<UpdateHeader>();
		updatesList = updatesXmlLoader.loadLastUpdates();
		
		for(int i = 0;i<updatesList.size(); i++){
			testProducsUpdate(updatesList.get(i));
		}
	}
	
	public void testProducsUpdate(UpdateHeader update){
		
		String filePath = update.getDetailFilePath();
		int addedProducts = update.getNumberOfProductsIn();
		int removedProducts = update.getNumberProductsOut();
		
		Catalog updateCatalog = new Catalog(new CategoryLoaderXml(filePath), new ProductUpdateLoaderXml(filePath));
		updateCatalog.loadRootCategories();
		//CHECK MAIN CATEGORIES
		assertEquals(2, updateCatalog.getCurrentSubCategories().size());
		assertEquals( ADDED , updateCatalog.getCurrentSubCategories().get(0).getName());
		assertEquals( REMOVED , updateCatalog.getCurrentSubCategories().get(1).getName());
		//CHECK NUMBER OF PRODUCTS IN 
		updateCatalog.navigateToSubCategoriesOf(updateCatalog.getCurrentSubCategories().get(0).getId());
		assertEquals(addedProducts, updateCatalog.getProductsOfCurrentCategory().size());
		updateCatalog.navigateToParentCategory();
		//CHECK NUMBER OF PRODUCTS OUT
		updateCatalog.navigateToSubCategoriesOf(updateCatalog.getCurrentSubCategories().get(1).getId());
		assertEquals(removedProducts, updateCatalog.getProductsOfCurrentCategory().size());
	}
}
