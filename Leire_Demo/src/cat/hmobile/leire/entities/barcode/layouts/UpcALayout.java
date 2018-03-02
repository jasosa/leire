package cat.hmobile.leire.entities.barcode.layouts;

/*
 * http://electronics.howstuffworks.com/gadgets/high-tech-gadgets/upc.htm
 */
public class UpcALayout implements IBarCodeLayout {
	
	public static final int SPECIFIED_LENGTH = 12;
	
	private static final int NUMBERSYSTEM_STARTINDEX = 0;
	private static final int NUMBERSYSTEM_LENGTH = 1;
	
	private static final int MANUFACTURER_STARTINDEX = 1;
	private static final int MANUFACTURER_LENGTH = 5;
	
	private static final int PRODUCT_STARTINDEX = 6;
	private static final int PRODUCT_LENGTH = 5;
	
	private static final int CHECKDIGIT_STARTINDEX = 11;
	private static final int CHECKDIGIT_LENGTH = 1;
	
	private static final int UPC12UCC12_EVENTVALUE = 1;
	
	@Override
	public String getContryCode(String barCode) {
		return barCode.substring(NUMBERSYSTEM_STARTINDEX, NUMBERSYSTEM_STARTINDEX + NUMBERSYSTEM_LENGTH + MANUFACTURER_LENGTH);
	}

	@Override
	public String getProductPart(String barCode) {
		return barCode.substring(PRODUCT_STARTINDEX, PRODUCT_STARTINDEX + PRODUCT_LENGTH);
	}

	@Override
	public String getProductCode(String barCode) {
		return barCode.substring(NUMBERSYSTEM_STARTINDEX, NUMBERSYSTEM_STARTINDEX + NUMBERSYSTEM_LENGTH + MANUFACTURER_LENGTH + PRODUCT_LENGTH);
	}

	@Override
	public int getCheckDigit(String barCode) {
		return Integer.parseInt(barCode.substring(CHECKDIGIT_STARTINDEX, CHECKDIGIT_STARTINDEX + CHECKDIGIT_LENGTH));
	}

	@Override
	public int getLength() {
		return SPECIFIED_LENGTH;
	}
	
	@Override
	public int getModulo10Parity() {
		return UPC12UCC12_EVENTVALUE;
	}
}
