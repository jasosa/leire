package cat.hmobile.leire.entities.barcode.layouts;

public class Ean13Layout implements IBarCodeLayout {
	
	public static final int SPECIFIED_LENGTH = 13;
	
	private static final int COUNTRY_STARTINDEX = 0;
	private static final int COUNTRY_LENGTH = 3;
	
	private static final int COMPANY_STARTINDEX = 3;
	private static final int COMPANY_LENGTH = 4;
	
	private static final int PRODUCT_STARTINDEX = 7;
	private static final int PRODUCT_LENGTH = 5;
	
	private static final int CHECKDIGIT_STARTINDEX = 12;
	private static final int CHECKDIGIT_LENGTH = 1;
	
	private static final int EAN13UCC13_EVENTVALUE = 0;
	
	@Override
	public int getCheckDigit(String barCode) {
		return Integer.parseInt(barCode.substring(CHECKDIGIT_STARTINDEX, CHECKDIGIT_STARTINDEX + CHECKDIGIT_LENGTH));
	}
	
	@Override
	public String getContryCode(String barCode) {
		return barCode.substring(COUNTRY_STARTINDEX, COUNTRY_STARTINDEX + COUNTRY_LENGTH);
	}
	
	@Override
	public String getProductPart(String barCode) {
		return barCode.substring(PRODUCT_STARTINDEX, PRODUCT_STARTINDEX + PRODUCT_LENGTH);
	}

	@Override
	public String getProductCode(String barCode) {
		return barCode.substring(0, PRODUCT_STARTINDEX + PRODUCT_LENGTH);
	}	
	
	@Override
	public int getLength() {
		return SPECIFIED_LENGTH;
	}

	@Override
	public int getModulo10Parity() {
		return EAN13UCC13_EVENTVALUE;
	}
	
	public String getCompanyPart(String barCode) {
		return barCode.substring(COMPANY_STARTINDEX, COMPANY_STARTINDEX + COMPANY_LENGTH);
	}
	
	public String getCompanyCode(String barCode) {
		return barCode.substring(0, COMPANY_STARTINDEX + COMPANY_LENGTH);
	}

}
