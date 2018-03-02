package cat.hmobile.leire.business;

import java.util.List;

import cat.hmobile.leire.entities.categories.Category;
import cat.hmobile.leire.entities.products.Product;

public interface ICatalogNavigator {
	void loadRootCategories();
	void navigateToSubCategoriesOf(String parentCategoryId);
	void navigateToParentCategory();
	List<Category> getCurrentSubCategories();
	List<Product> getProductsOfCurrentCategory();
	List<Product> getProductsByAproxText(String searchText);
	List<Product> getProductsByCategory(String categoryId);
	List<Product> getProductsByBrand(String brand);
	boolean isCurrentCategoryLeaf();
	boolean isCurrentCategoryRoot();
	String getCurrentCategoryId();
	String getCurrentCategoryName();
	Product getProductByEan(String eanCode);
	
}
