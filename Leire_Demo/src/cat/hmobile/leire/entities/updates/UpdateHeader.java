package cat.hmobile.leire.entities.updates;

public class UpdateHeader {
	
	private String m_date;
	private int m_productsIn;
	private int m_productsOut;
	private String m_detailfilePath;
	
	public UpdateHeader(String date, int productsIn, int productsOut, String detailFilePath){
		m_date = date;
		m_productsIn = productsIn;
		m_productsOut = productsOut;
		m_detailfilePath = detailFilePath;
	}	
	
	public String getDate(){
		return m_date;
	}
	
	public int getNumberOfProductsIn(){
		return m_productsIn;
	}
	public int getNumberProductsOut(){
		return m_productsOut;
	}
	
	@Override
	public String toString() {
		return m_date;
	}

	public String getDetailFilePath() {
		return m_detailfilePath;
	}
}
