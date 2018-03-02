package cat.hmobile.leire.entities.products;

import java.util.List;

public interface IProductLoader {
	Product loadByEAN (String ean);
	Product loadById (String productId);
	List<Product> loadAllProducts();
	List<Product> loadByCategory(String categoryId);
	List<Product> loadByBrand(String brand);
	List<Product> loadByAproxTextSearchAndCategory(String text, String categoryId);
	List<Product> loadByAproxTextSearch(String text);
}
