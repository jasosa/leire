package cat.hmobile.leire.entities.barcode;

import cat.hmobile.leire.GUI.Activities.R;
import cat.hmobile.leire.entities.ArgumentException;
import cat.hmobile.leire.entities.barcode.layouts.IBarCodeLayout;

public class BarCodeValidator {

	private static final int CHECK_EVEN_MULTIPLIER = 1;
	private static final int CHECK_ODD_MULTIPLIER = 3;
	private static final int CHECK_MOD = 10;
	
	private static final String PATTERN_DIGITS = "\\d+";
	
	public static void validate(String barCode, IBarCodeLayout layout)
	{
		checkEanFormat(barCode);
		verifyCheckDigit(barCode, layout);
	}
	
	private static void checkEanFormat(String eanBarCode)
	{
		//1. Parameter
		if(eanBarCode==null || eanBarCode.length() == 0)
			throw new ArgumentException(R.string.error_ean_empty);
		
		//2. Verify the format of the string
		if(!eanBarCode.matches(PATTERN_DIGITS))
	    	 throw new BarCodeException(R.string.error_ean_format);
	}

	private static void verifyCheckDigit(String barCode, IBarCodeLayout layout)
	{
		int lastDigit = layout.getCheckDigit(barCode);
		if(lastDigit!= getModulo10(layout.getProductCode(barCode), layout.getModulo10Parity()))
			throw new BarCodeException(R.string.error_ean_checkdigit);
	}
	
	/*
	 * see http://www.gs1.org/barcodes/support/check_digit_calculator
	 * see http://www.morovia.com/education/utility/upc-ean.asp
	 */
	private static int getModulo10(String barCode, int eventValue) {
		int eventSum = 0;
		int oddSum = 0;
		int currentDigit;
		
		for(int i = 0; i < barCode.length(); i++)
		{
			currentDigit = Integer.parseInt(barCode.substring(i, i+1));
			if(i%2 == eventValue)
				eventSum += currentDigit;
			else
				oddSum += currentDigit;
		}
		
		int check = (CHECK_ODD_MULTIPLIER * oddSum + eventSum) % CHECK_MOD;
		if(check != 0)
			check = CHECK_MOD - check;
	return check;
	}
}
