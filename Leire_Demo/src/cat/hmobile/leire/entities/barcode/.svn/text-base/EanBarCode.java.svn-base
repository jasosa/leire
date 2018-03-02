package cat.hmobile.leire.entities.barcode;

import cat.hmobile.leire.entities.barcode.layouts.IBarCodeLayout;

public class EanBarCode {
	
	private String m_barCode;
	private IBarCodeLayout m_layout;
	
	public EanBarCode(String barCode, IBarCodeLayout layout) throws Exception
	{
		BarCodeValidator.validate(barCode, layout);
		
		this.m_barCode = barCode;	
		this.m_layout = layout;	
	}

	public String getBarCode() {
		return m_barCode;
	}
	
	public String getContryCode() {
		return this.m_layout.getContryCode(this.m_barCode);
	}
	
	public String getProductPart() {
		return this.m_layout.getProductPart(this.m_barCode);
	}
	
	public String getProductCode() {
		return this.m_layout.getProductCode(this.m_barCode);
	}
	
	public int getCheckDigit() {
		return this.m_layout.getCheckDigit(this.m_barCode);
	}
	
	public int getSpecifiedLength() {
		return this.m_layout.getLength();
	}
}
