package cat.hmobile.leire.data.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

//import org.jdom.Document;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;

import android.content.Context;
import android.content.res.AssetManager;
import cat.hmobile.leire.GUI.Activities.R;
import cat.hmobile.leire.entities.DataAccessException;

public abstract class XmlAccessor {

	private static final String XML_DATAFILE = "demoData.xml";	
	
	protected Document m_xmlDocument;
	
	public XmlAccessor(Context ctx) {
		loadFileAndBuildXmlDocument(XML_DATAFILE, ctx, true);
	}
	
	public XmlAccessor(Context ctx, String file){
		loadFileAndBuildXmlDocument(file, ctx, true);
	}
	
	//TODO: test constructor
	public XmlAccessor() {
		loadFileAndBuildXmlDocument(XML_DATAFILE, null, false);
	}
	
	//TODO: test constructor
	public XmlAccessor(String file) {
		loadFileAndBuildXmlDocument(file, null, false);
	}
	
	private void loadFileAndBuildXmlDocument (String xmlFile, Context ctx, boolean isFileInAssets)
	{	
	    try
	    {   
	    	InputStream ins = loadFile(xmlFile, ctx, isFileInAssets);
	    	buildXmlDocument(ins);
	    }
	    catch(JDOMException jdomEx)
	    {
	    	throw new DataAccessException(R.string.error_data_xml);
	    }
	    catch(IOException ioEx)
	    {
	    	throw new DataAccessException(R.string.error_data_access);
	    }
	}

	protected List<Element> getNodeList(String xpathQuery)
	{
		try
		{
			XPath xPath = XPath.newInstance(xpathQuery);
	        return xPath.selectNodes(this.m_xmlDocument);
		}
		catch(JDOMException jdomEx)
	    {
	    	throw new DataAccessException(R.string.error_data_xml);
	    }
	}
	
	protected Element getNode(String xpathQuery)
	{
		try
		{
			XPath xPath = XPath.newInstance(xpathQuery);
	        return (Element) xPath.selectSingleNode(this.m_xmlDocument);
		}
		catch(JDOMException jdomEx)
	    {
	    	throw new DataAccessException(R.string.error_data_xml);
	    }
	}

	protected String addParameterToXpath(String xpath, Object ... params)
	{ 
		return String.format(xpath, params);
	}
	
	private void buildXmlDocument(InputStream ins) throws JDOMException,
	IOException {
		SAXBuilder builder = new SAXBuilder();
		this.m_xmlDocument = builder.build(ins);
	}

	private InputStream loadFile(String xmlFile, Context ctx,
		boolean isFileInAssets) throws IOException, FileNotFoundException {
		InputStream ins;
		if(isFileInAssets){
			ins = getInputStreamFromAssets(xmlFile, ctx);
		}
		else{
			ins = getInputStreamFromFile(xmlFile);
		}
		return ins;
	}
	
	private InputStream getInputStreamFromFile(String xmlFile)
		throws FileNotFoundException {
		InputStream ins;
		ins = new FileInputStream(new File(xmlFile));
		return ins;
	}

	private InputStream getInputStreamFromAssets(String xmlFile, Context ctx)
		throws IOException {
		InputStream ins;
		AssetManager mgr = ctx.getResources().getAssets();
		ins = mgr.open(xmlFile);
		return ins;
	}
}