package cat.hmobile.leire.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom.Element;

import android.content.Context;

import cat.hmobile.leire.data.xml.CategoriesXmlAccessor;
import cat.hmobile.leire.data.xml.ProductsUpdatesXmlAccesor;
import cat.hmobile.leire.entities.categories.Category;
import cat.hmobile.leire.entities.categories.CategoryLeaf;
import cat.hmobile.leire.entities.updates.IUpdatesHeaderLoader;
import cat.hmobile.leire.entities.updates.UpdateHeader;

public class UpdatesHeaderLoaderXml implements IUpdatesHeaderLoader {
	
	private static final String CHILD_DETAIL_FILE_PATH ="detailFilePath"; 
	private static final String CHILD_DATE ="date";
	private static final String CHILD_PRODUCTS_IN = "productsIn";
	private static final String CHILD_PRODUCTS_OUT ="productsOut";
	
	ProductsUpdatesXmlAccesor m_LastUpdates;
	
	public UpdatesHeaderLoaderXml(Context ctx) {
		m_LastUpdates = new ProductsUpdatesXmlAccesor(ctx);
	}
	
	public UpdatesHeaderLoaderXml() {
		m_LastUpdates = new ProductsUpdatesXmlAccesor();
	}

	/* (non-Javadoc)
	 * @see cat.hmobile.leire.data.IProductsUpdateLoader#loadLastUpdates()
	 */
	@Override
	public  List<UpdateHeader> loadLastUpdates() {
		
		List<Element> xmlProductsUpdates = this.m_LastUpdates.loadLastUpdates();
		return this.buildProductsUpdatesList(xmlProductsUpdates);
	}

	private List<UpdateHeader> buildProductsUpdatesList(
			List<Element> xmlProductsUpdates) {
		
		List<UpdateHeader> result = new ArrayList<UpdateHeader>();
		
		Element currentElement;
		for (Iterator<Element> i = xmlProductsUpdates.iterator(); i.hasNext();)
		{
			currentElement = i.next();
			result.add(this.buildProductsUpdate(currentElement));
		}
		
		return result;
	}

	private UpdateHeader buildProductsUpdate(Element currentElement) {
		// TODO Auto-generated method stub
		UpdateHeader result = null;
		
		if(currentElement != null)
		{
			String filePath = currentElement.getChild(CHILD_DETAIL_FILE_PATH).getText();
			String date = currentElement.getChild(CHILD_DATE).getText();
			String productsIn = currentElement.getChild(CHILD_PRODUCTS_IN).getText();
			String productsOut = currentElement.getChild(CHILD_PRODUCTS_OUT).getText();
			
			result = new UpdateHeader(date, Integer.valueOf(productsIn), Integer.valueOf(productsOut), filePath);
		}
	
		return result;
		
	}
		
}
