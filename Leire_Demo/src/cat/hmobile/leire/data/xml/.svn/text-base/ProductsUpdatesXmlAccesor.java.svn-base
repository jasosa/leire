package cat.hmobile.leire.data.xml;

import java.util.List;

import org.jdom.Element;

import android.content.Context;

public class ProductsUpdatesXmlAccesor extends XmlAccessor{

	private static final String XML_FILE = "updatesList.xml";
	private static final String QUERY_SELECT_UPDATES = "//*/update";
	
	public ProductsUpdatesXmlAccesor(Context ctx) {
		super(ctx, XML_FILE);
	}
	
	//TODO: Constructor creat pel testeig
	public ProductsUpdatesXmlAccesor()
	{
		super(XML_FILE);
	}
	
	public List<Element> loadLastUpdates() {
		
		String xpath = QUERY_SELECT_UPDATES;
		return this.getNodeList(xpath);
	}
}
