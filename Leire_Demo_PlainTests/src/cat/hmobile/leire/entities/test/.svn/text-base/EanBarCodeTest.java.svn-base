package cat.hmobile.leire.entities.test;

import org.junit.Assert;
import org.junit.Test;

import cat.hmobile.leire.entities.ArgumentException;
import cat.hmobile.leire.entities.barcode.BarCodeException;
import cat.hmobile.leire.entities.barcode.BarCodeValidator;
import cat.hmobile.leire.entities.barcode.EanBarCode;
import cat.hmobile.leire.entities.barcode.layouts.BarCodeLayoutFactory;

public class EanBarCodeTest extends Object {

	//TODO: hi ha 2 mètodes de fer el check!
	
	@Test
	public void verifyCode8_OK01()
	{
		try
		{
			this.verifyCode8("55123457", "551", "2345", 7);
		}
		catch(Exception e)
		{
			Assert.fail();
		}
	}
	
	@Test
	public void verifyCode8_OK02()
	{
		try
		{
			this.verifyCode8("20123451", "201", "2345", 1);
		}
		catch(Exception e)
		{
			Assert.fail();
		}
	}
	
	@Test
	public void verifyUpcA_OK01()
	{
		try
		{
			this.verifyUpcA("075678164125", "075678", "16412", 5);
		}
		catch(Exception e)
		{
			Assert.fail();
		}
	}
	
	@Test
	public void verifyUpcA_OK02()
	{
		try
		{
			this.verifyUpcA("639382000393", "639382", "00039", 3);
		}
		catch(Exception e)
		{
			Assert.fail();
		}
	}
	
	@Test
	public void verifyUpcA_OK03()
	{
		try
		{
			this.verifyUpcA("987654321098", "987654", "32109", 8);
		}
		catch(Exception e)
		{
			Assert.fail();
		}
	}
	
	@Test
	public void verifyCode13_OK01()
	{
		try
		{
			this.verifyCode13("9501101020962", "950", "1101", "02096", 2);
		}
		catch(Exception e)
		{
			Assert.fail();
		}
	}
	
	@Test
	public void verifyCode13_OK02()
	{
		try
		{
			this.verifyCode13("8412345678905", "841", "2345", "67890", 5);
		}
		catch(Exception e)
		{
			Assert.fail();
		}
	}
	
	@Test
	public void verifyCode13_KO01()
	{
		try
		{
			this.verifyCode13("9501101020961", "", "", "", 1);
			Assert.fail();
		}
		catch(Exception e)
		{
		}
	}
	
	@Test(expected=ArgumentException.class)
	public void checkEanBarCode_ArgEx01()
	{
		String barCode = null;
		BarCodeValidator.validate(barCode, BarCodeLayoutFactory.build(barCode));
	}
	
	@Test(expected=ArgumentException.class)
	public void checkEanBarCode_ArgEx02()
	{
		String barCode = "";
		BarCodeValidator.validate(barCode, BarCodeLayoutFactory.build(barCode));
	}
	
	@Test(expected=BarCodeException.class)
	public void checkEanBarCode_FormatEx01()
	{
		String barCode = "Manelddd";
		BarCodeValidator.validate(barCode, BarCodeLayoutFactory.build(barCode));
	}
	
	@Test(expected=BarCodeException.class)
	public void checkEanBarCode_FormatEx02()
	{
		String barCode = "00001 0001 33";
		BarCodeValidator.validate(barCode, BarCodeLayoutFactory.build(barCode));
	}
	
	@Test(expected=BarCodeException.class)
	public void checkEanBarCode_checkDigitEx01()
	{
		String barCode = "9501101020961";
		BarCodeValidator.validate(barCode, BarCodeLayoutFactory.build(barCode));
	}
	
	@Test
	public void checkEanBarCode_OKEan13()
	{
		String barCode = "9501101020962";
		BarCodeValidator.validate(barCode, BarCodeLayoutFactory.build(barCode));
	}
	
	@Test
	public void checkEanBarCode_OKEan8()
	{
		String barCode = "20123451";
		BarCodeValidator.validate(barCode, BarCodeLayoutFactory.build(barCode));
	}	
	
	private void verifyCode13(String barCode, String countryCode, String companyCode, String productCode, int checkDigit) throws Exception
	{
		EanBarCode ean13 = 	new EanBarCode(barCode, BarCodeLayoutFactory.build(barCode));
		Assert.assertEquals(barCode, ean13.getBarCode());
		Assert.assertEquals(countryCode, ean13.getContryCode());
		Assert.assertEquals(productCode, ean13.getProductPart());
		Assert.assertEquals(countryCode + companyCode + productCode, ean13.getProductCode());
		Assert.assertEquals(checkDigit, ean13.getCheckDigit());
	}

	private void verifyCode8(String barCode, String countryCode, String productCode, int checkDigit) throws Exception
	{
		EanBarCode ean8 = new EanBarCode(barCode, BarCodeLayoutFactory.build(barCode));
		Assert.assertEquals(barCode, ean8.getBarCode());
		Assert.assertEquals(countryCode, ean8.getContryCode());
		Assert.assertEquals(productCode, ean8.getProductPart());
		Assert.assertEquals(countryCode + productCode, ean8.getProductCode());
		Assert.assertEquals(checkDigit, ean8.getCheckDigit());
	}
	
	private void verifyUpcA(String barCode, String countryCode, String productCode, int checkDigit) throws Exception
	{
		EanBarCode upcA = new EanBarCode(barCode, BarCodeLayoutFactory.build(barCode));
		Assert.assertEquals(barCode, upcA.getBarCode());
		Assert.assertEquals(countryCode, upcA.getContryCode());
		Assert.assertEquals(productCode, upcA.getProductPart());
		Assert.assertEquals(countryCode + productCode, upcA.getProductCode());
		Assert.assertEquals(checkDigit, upcA.getCheckDigit());
	}
}