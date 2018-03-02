package cat.hmobile.leire.entities.barcode.layouts;

import cat.hmobile.leire.GUI.Activities.R;
import cat.hmobile.leire.entities.ArgumentException;

public class BarCodeLayoutFactory {

	public static IBarCodeLayout build(String eanBarCode)
	{	
		if(eanBarCode==null)
			throw new ArgumentException(R.string.error_ean_empty);
		
		switch(eanBarCode.length())
		{
			case Ean13Layout.SPECIFIED_LENGTH:
				return new Ean13Layout();
			case Ean8Layout.SPECIFIED_LENGTH:
				return new Ean8Layout();
			case UpcALayout.SPECIFIED_LENGTH:
				return new UpcALayout();
			default:
				throw new ArgumentException(R.string.error_ean_illegal_layout_argument);
		}
	}
}
