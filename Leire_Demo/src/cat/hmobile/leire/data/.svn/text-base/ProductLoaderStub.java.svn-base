package cat.hmobile.leire.data;

import java.util.ArrayList;
import java.util.List;

import cat.hmobile.leire.entities.products.IProductLoader;
import cat.hmobile.leire.entities.products.Product;

@Deprecated
public class ProductLoaderStub implements IProductLoader {

	Product product1 = new Product ();
	Product product2 = new Product ();
	Product product3 = new Product ();
	Product product4 = new Product ();
	
	public ProductLoaderStub()
	{
		product1.setId("0001");
		product1.setBarCode("4879056");
		product1.setBrand("ACME");
		product1.setName("Product 0001");
		product1.setCategoryId("3");
		
		product2.setId("0002");
		product2.setBarCode("48810345");
		product2.setBrand("PASCUAL");
		product2.setName("Product 0002");
		product2.setCategoryId("12");
		
		product3.setId("0003");
		product3.setBarCode("48810346");
		product3.setBrand("LA LECHERA");
		product3.setName("Product 0003");
		product3.setCategoryId("12");
		
		product4.setId("0004");
		product4.setBarCode("53900312");
		product4.setBrand("SOLÍS");
		product4.setName("Product 0004");
		product4.setCategoryId("21");
	}
	
	@Override
	public Product loadByEAN(String ean) 
	{	
		if(ean.equals(product1.getBarCode()))
				return product1;
		else if(ean.equals(product2.getBarCode()))
			return product2;
		else if(ean.equals(product3.getBarCode()))
			return product3;
		else if(ean.equals(product4.getBarCode()))
			return product4;
		else return null;
	}

	@Override
	public Product loadById(String productId) 
	{
			if(productId.equals(product1.getId()))
				return product1;
		else if(productId.equals(product2.getId()))
			return product2;
		else if(productId.equals(product3.getId()))
				return product3;
		else if(productId.equals(product4.getId()))
			return product4;
		else return null;
	}

	@Override
	public List<Product> loadByCategory(String categoryId) {
		List<Product> products = new ArrayList<Product>();
		
		if(categoryId.equals(product1.getCategoryId()))
			products.add(product1);
		if(categoryId.equals(product2.getCategoryId()))
			products.add(product2);
		if(categoryId.equals(product3.getCategoryId()))
			products.add(product3);
		if(categoryId.equals(product4.getCategoryId()))
			products.add(product4);
		
		return products;
	}

	@Override
	public List<Product> loadByAproxTextSearchAndCategory(String text, String categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> loadAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> loadByAproxTextSearch(String text) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> loadByBrand(String brand) {
		// TODO Auto-generated method stub
		return null;
	}

}
