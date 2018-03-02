package cat.hmobile.leire.entities.categories;

import java.util.List;

public interface ICategoryLoader {
	List<Category> loadMain();
	Category load(String categoryId);
	List<Category> loadChilds(String parentCategoryId);
}
